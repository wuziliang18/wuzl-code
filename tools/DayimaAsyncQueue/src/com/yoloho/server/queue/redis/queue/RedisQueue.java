package com.yoloho.server.queue.redis.queue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.alibaba.fastjson.JSON;
import com.yoloho.server.queue.core.GeneralWorker;
import com.yoloho.server.queue.redis.RedisConnectionFactory;
import com.yoloho.server.queue.redis.bean.RedisConnection;
import com.yoloho.server.queue.redis.queue.listener.QueueListener;
/**
 * 封装了对redis队列的操作，会不断从监听队列中取值，当前只是从左边出队列
 * 使用方法：
 * 1。实现QueueListener类
 * 2。new RedisQueue后，把QueueListener实现类付给setQueueListener方法，setClazz方法为队列中值的类型，setQueueName方法为队列名称，该三个参数必传
 * 3。setRedisDbName方法选填，值为redis连接池的配置名称
 * 注意：该类依赖RedisConnectionFactory
 * @author wuzl
 *
 * @param <T>
 */
public class RedisQueue<T> {
	private static Log log = LogFactory.getLog(RedisQueue.class);
	private String redisDbName;
	private String queueName;// 队列名称
	private Class<T> clazz;
	private QueueListener<T> queueListener;// 监听者的实际调用方法
	private Lock lock = new ReentrantLock();
	private RedisConnection redisConnection;
	private ListenerThread listenerThread;

	public void setRedisDbName(String redisDbName) {
		this.redisDbName = redisDbName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public void setQueueListener(QueueListener<T> queueListener) {
		this.queueListener = queueListener;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 启动监听
	 */
	public void start() {
		if (queueName == null) {
			throw new RuntimeException("queueName不可以为空");
		}
		if (queueListener == null) {
			throw new RuntimeException("监听者不可以为空");
		}
		if (clazz == null) {
			throw new RuntimeException("出队类型不可以为空");
		}
		// 设置redis连接
		setRedisConnection();
		log.info("启动【" + queueName + "】的队列监听");
		listenerThread = new ListenerThread();
		listenerThread.start();
		log.info("启动【" + queueName + "】的队列监听成功");
	}

	/**
	 * 判断是否是默认redis后设置连接
	 */
	private void setRedisConnection() {
		if (redisDbName == null) {
			redisConnection = RedisConnectionFactory.getRedisConnection();
		} else {
			redisConnection = RedisConnectionFactory
					.getRedisConnection(redisDbName);
		}
	}

	/**
	 * 从左边出队列
	 * 
	 * @return
	 */
	private T outQueue() {
		try {
			lock.lockInterruptibly();
			byte[] bytes = redisConnection.getJedis()
					.lpop(queueName.getBytes());
			if (bytes != null) {
				return JSON.parseObject(bytes, clazz);
			}
		} catch (Exception e) {
			try {
				// 重新获取连接
				RedisConnectionFactory.returnResource(redisConnection);
				setRedisConnection();
				Thread.sleep(1000l);
			} catch (Exception ex) {
				log.info("【" + queueName + "】重新获取连接出错:" + e.getMessage());
			}
		} finally {
			lock.unlock();
		}
		return null;
	}

	/**
	 * 启动监听线程
	 * 
	 * @author wuzl
	 * 
	 */
	private class ListenerThread extends Thread implements GeneralWorker {
		private boolean is_hup = false;
		boolean is_huped = false;

		@Override
		public void run() {
			try {
				while (!is_hup) {
					try {
						T value = outQueue();
						if (value != null) {
							try {
								queueListener.onMessage(value, queueName);
							} catch (Exception e) {
								log.info("调用【" + queueName + "】监听出错："
										+ e.getMessage());
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						log.info("解析出列值出错" + e.getMessage());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				log.info("结束【" + queueName + "】监听");
				try {
					RedisConnectionFactory.returnResource(redisConnection);
				} catch (Exception e) {
					log.info("【" + queueName + "】归还连接池出错:" + e.getMessage());
					e.printStackTrace();
				}
			}
			is_huped = true;
		}

		@Override
		public void hup() {
			is_hup = true;
			interrupt();
			int wait_num = 60;
			while (!is_huped && wait_num > 0) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
				}
				wait_num--;
			}
		}
	}
	/**
	 * 给钩子调用
	 */
	public void hup() {
		if(listenerThread!=null){
			listenerThread.hup();
		}
	}

}
