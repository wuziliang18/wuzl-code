package com.yoloho.server.queue.redis.queue;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yoloho.server.queue.core.GeneralWorker;
import com.yoloho.server.queue.exception.ConfigErrorException;
import com.yoloho.server.queue.redis.RedisConnectionFactory;
import com.yoloho.server.queue.redis.bean.RedisConfigBean;
import com.yoloho.server.queue.redis.queue.constants.RedisPoolDefaultsValueConstants;
import com.yoloho.server.queue.redis.queue.constants.RedisQueueConstants;
import com.yoloho.server.queue.redis.queue.listener.QueueListener;
import com.yoloho.server.queue.redis.util.InitRedisPool;
import com.yoloho.server.queue.utils.IniEditor;
import com.yoloho.server.queue.utils.StringUtil;

/**
 * 使用redis队列的模板类
 * @author wuzl
 * 
 */
public  class RedisQueueTemplate<T> implements GeneralWorker {
	private static Log log = LogFactory.getLog(RedisQueueTemplate.class);
	private String redisDbName;
	private String queueName;// 队列名称
	private Class<T> clazz;
	private QueueListener<T> queueListener;// 监听者的实际调用方法
	private RedisQueue<T> redisQueue;
	private String confFilePath;// 配置文件的路径
	private IniEditor ini;//缓存一份配置

	public IniEditor getIni() {
		return ini;
	}

	public void setIni(IniEditor ini) {
		this.ini = ini;
	}

	public void setConfFilePath(String confFilePath) {
		this.confFilePath = confFilePath;
	}

	public void setRedisDbName(String redisDbName) {
		this.redisDbName = redisDbName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void setQueueListener(QueueListener<T> queueListener) {
		this.queueListener = queueListener;
	}

	/**
	 * 初始化配置
	 */
	public final void initConfig() {
		if (confFilePath == null) {
			throw new RuntimeException("必须配置confFilePath");
		}
		try {
			IniEditor ini = new IniEditor();
			ini.load(confFilePath);
			this.setIni(ini);//缓存给调用者具体去配置其他选项
			try {
				/* 1.加载redis的配置 */
				/* 1.1判断是否有redis的配置 */
				if (!ini.hasSection(RedisQueueConstants.REDIS_CONFIG)) {
					throw new RuntimeException("配置文件【" + confFilePath + "】中没有【"
							+ RedisQueueConstants.REDIS_CONFIG + "】的配置");
				}
				/* 1.2判断是否有redis的队列名称配置 */
				String queueName = ini.get(RedisQueueConstants.REDIS_CONFIG,
						RedisQueueConstants.REDIS_CONFIG_QUEUE);
				if (queueName == null || "".equals(queueName)) {
					throw new RuntimeException("配置文件【" + confFilePath + "】中没有【"
							+ RedisQueueConstants.REDIS_CONFIG_QUEUE + "】的配置");
				}
				this.setQueueName(queueName);
				/* 1.3判断是否有redis的数据库配置，如果且正确加入到连接池中 */
				String redisDbName = ini.get(RedisQueueConstants.REDIS_CONFIG,
						RedisQueueConstants.REDIS_DB_NAME);
				if (redisDbName != null && !"".equals(redisDbName)) {// 只有当有dbname的时候才会加载redis配置
					InitRedisPool.initRedisPoolFullConfig(redisDbName, ini);
					log.info("初始化redis队列【" + queueName + "】时，插入一个新的数据库连接");
				}
				log.info("初始化redis队列【" + queueName + "】成功");
			} catch (Exception e) {
				e.printStackTrace();
				throw new ConfigErrorException();
			}
		} catch (IOException e) {
			log.error("初始化一个redis队列时，【" + confFilePath + "】路径不存在");
			Runtime.getRuntime().exit(2);
		} catch (ConfigErrorException e) {
			log.error("初始化一个redis队列时，【" + confFilePath + "】的配置有问题:");
			Runtime.getRuntime().exit(3);
		}
	}

	/**
	 * 启动队列监听
	 */
	public final void startRedisQueue() {
		if (clazz == null) {
			throw new RuntimeException("clazz不可以为空");
		}
		if (queueListener == null) {
			throw new RuntimeException("queueListener不可以为空");
		}
		this.initConfig();
		redisQueue = new RedisQueue<T>();
		redisQueue.setQueueName(queueName);
		redisQueue.setClazz(clazz);
		redisQueue.setQueueListener(queueListener);
		if (redisDbName != null && !"".equals(redisDbName)) {
			redisQueue.setRedisDbName(redisDbName);
		}
		redisQueue.start();
	}

	/**
	 * 可覆盖
	 */
	@Override
	public void hup() {
		if(redisQueue!=null){
			redisQueue.hup();
		}
	}


}
