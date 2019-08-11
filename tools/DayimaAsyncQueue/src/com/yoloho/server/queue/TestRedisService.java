package com.yoloho.server.queue;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yoloho.server.queue.exception.CanNotWritePidFileException;
import com.yoloho.server.queue.exception.ConfigErrorException;
import com.yoloho.server.queue.module.email.bean.EmailBean;
import com.yoloho.server.queue.module.email.service.SendEmailFromRedisQueueService;
import com.yoloho.server.queue.redis.RedisConnectionFactory;
import com.yoloho.server.queue.redis.bean.RedisConnection;
import com.yoloho.server.queue.redis.config.DefaultRedisConfiguration;
import com.yoloho.server.queue.redis.queue.RedisQueue;
import com.yoloho.server.queue.redis.queue.listener.QueueListener;
import com.yoloho.server.queue.utils.IniEditor;
import com.yoloho.server.queue.utils.Utils;

public class TestRedisService {
	static Log _log = LogFactory.getLog(TestRedisService.class); 
	private static SendEmailFromRedisQueueService sendEmailFromRedisQueueService;
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
			sendEmailFromRedisQueueService.initConfig();
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
				sendEmailFromRedisQueueService.hup();
			}
		});
		try {
			Utils.writePidFile();
		} catch (CanNotWritePidFileException e1) {
			Runtime.getRuntime().exit(1);
		}
	}
	public static void main(String[] args) {
		sendEmailFromRedisQueueService=new SendEmailFromRedisQueueService();
		loadConfig();
		tryToDaemon();
		sendEmailFromRedisQueueService.startSendMailFromRedisQueueService();
	}
}
