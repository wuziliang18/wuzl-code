package com.yoloho.server.queue;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yoloho.server.queue.exception.CanNotWritePidFileException;
import com.yoloho.server.queue.exception.ConfigErrorException;
import com.yoloho.server.queue.module.email.bean.EmailBean;
import com.yoloho.server.queue.redis.RedisConnectionFactory;
import com.yoloho.server.queue.redis.bean.RedisConnection;
import com.yoloho.server.queue.redis.config.DefaultRedisConfiguration;
import com.yoloho.server.queue.redis.queue.RedisQueue;
import com.yoloho.server.queue.redis.queue.listener.QueueListener;
import com.yoloho.server.queue.utils.IniEditor;
import com.yoloho.server.queue.utils.Utils;

public class TestRedis {
	static Log _log = LogFactory.getLog(TestRedis.class); 
	private static RedisQueue<EmailBean> redisQueue;
	private static void loadConfig() {
		try {
			System.setProperty("org.jboss.logging.provider", "log4j2");
			IniEditor ini = new IniEditor();
			ini.load("config/main.conf");
			try {
				DaemonConfiguration.setPidPath(ini.get("daemon", "pid"));
			} catch (Exception e) {
				throw new ConfigErrorException();
			}
			DefaultRedisConfiguration.initConfig();
			
		} catch (IOException e) {
			Utils.log_error("config file error");
			Runtime.getRuntime().exit(2);
		} catch (ConfigErrorException e) {
			Utils.log_error("config file error");
			Runtime.getRuntime().exit(3);
		}
	}
	/**
	 * 线程配置
	 */
	private static void tryToDaemon() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				_log.info("该结束了呀 亲");
				super.run();
				redisQueue.hup();
			}
		});
		try {
			Utils.writePidFile();
		} catch (CanNotWritePidFileException e1) {
			Runtime.getRuntime().exit(1);
		}
	}
	public static void main(String[] args) {
		loadConfig();
		tryToDaemon();
		redisQueue=new RedisQueue<EmailBean>();
		redisQueue.setQueueName("email_queue");
		redisQueue.setClazz(EmailBean.class);
		redisQueue.setQueueListener(new QueueListener<EmailBean>() {
			@Override
			public void onMessage(EmailBean message, String queueName) {
//				//模拟长时间业务运行
//				long wait=5000000000l;
//				while(wait-->=0){
//					if(wait%1000000000l==0){
//						System.out.println("已经等待了10000000000了");
//					}
//				}
				System.out.println("队列 "+queueName);
				System.out.println("信息 "+message.getText()+":"+message.getTo());
			}
		});
		redisQueue.start();
//		while(true){
//			try {
//				Thread.sleep(2500l);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
}
