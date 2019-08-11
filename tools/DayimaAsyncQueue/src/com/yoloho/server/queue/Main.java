package com.yoloho.server.queue;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yoloho.server.queue.exception.CanNotWritePidFileException;
import com.yoloho.server.queue.exception.ConfigErrorException;
import com.yoloho.server.queue.redis.config.DefaultRedisConfiguration;
import com.yoloho.server.queue.utils.IniEditor;
import com.yoloho.server.queue.utils.Utils;

public class Main {
	static Log log = LogFactory.getLog(Main.class);

	/**
	 * 加载配置
	 */
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
			/* 1.加载默认的redis配置 */
			DefaultRedisConfiguration.initConfig();

		} catch (IOException e) {
			Utils.log_error("config file error");
			Runtime.getRuntime().exit(2);
		} catch (ConfigErrorException e) {
			Utils.log_error("config file error");
			Runtime.getRuntime().exit(3);
		}
		log.info("加载配置成功");
	}

	/**
	 * 线程配置
	 */
	private static void tryToDaemon() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				log.info("程序关闭成功");
				super.run();
			}
		});
		try {
			Utils.writePidFile();
		} catch (CanNotWritePidFileException e1) {
			Runtime.getRuntime().exit(1);
		}
		log.info("线程配置成功");
	}

	public static void main(String[] args) {
		loadConfig();
		tryToDaemon();
		log.info("程序启动完毕……");
//		while (true) {
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				
//			}
//		}
	}
}
