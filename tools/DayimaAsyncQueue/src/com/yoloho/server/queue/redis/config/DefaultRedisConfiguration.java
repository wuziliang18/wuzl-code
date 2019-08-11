package com.yoloho.server.queue.redis.config;

import java.io.IOException;

import com.yoloho.server.queue.exception.ConfigErrorException;
import com.yoloho.server.queue.utils.IniEditor;
import com.yoloho.server.queue.utils.Utils;

/**
 * 默认的redis配置
 * 
 * @author wuzl
 * 
 */
public class DefaultRedisConfiguration {
	private static String redisHost = "192.168.123.3";
	private static int redisPort = 6379;
	private static int redisTimeout = 300;
	private static int redisMaxActive = 200;
	private static int redisMaxIdle = 10;
	private static int redisMaxWait = 10;
	private static boolean redisTestOnBorrow = true;
	private static boolean redisTestOnReturn = true;

	public static void initConfig() {
		try {
			IniEditor ini = new IniEditor();
			ini.load("config/default_redis.conf");
			try {
				// 默认的reader配置
				DefaultRedisConfiguration.setRedisHost(ini.get(
						"default_redis", "host"));
				DefaultRedisConfiguration.setRedisPort(Integer.parseInt(ini
						.get("default_redis", "port")));
				DefaultRedisConfiguration.setRedisMaxActive(Integer
						.parseInt(ini.get("default_redis", "max_active")));
				DefaultRedisConfiguration.setRedisMaxIdle(Integer.parseInt(ini
						.get("default_redis", "max_idle")));
				DefaultRedisConfiguration.setRedisMaxWait(Integer.parseInt(ini
						.get("default_redis", "max_wait")));
				DefaultRedisConfiguration.setRedisTestOnBorrow(Boolean
						.parseBoolean(ini.get("default_redis",
								"test_on_borrow")));
				DefaultRedisConfiguration.setRedisTestOnReturn(Boolean
						.parseBoolean(ini.get("default_redis",
								"test_on_return")));
				DefaultRedisConfiguration.setRedisTimeout(Integer.parseInt(ini
						.get("default_redis", "timeout")));
			} catch (Exception e) {
				e.printStackTrace();
				throw new ConfigErrorException();
			}
		} catch (IOException e) {
			Utils.log_error("Activity config file error");
			Runtime.getRuntime().exit(2);
		} catch (ConfigErrorException e) {
			Utils.log_error("Activity config file error");
			Runtime.getRuntime().exit(3);
		}
	}

	public static String getRedisHost() {
		return redisHost;
	}

	public static void setRedisHost(String redisHost) {
		DefaultRedisConfiguration.redisHost = redisHost;
	}

	public static int getRedisPort() {
		return redisPort;
	}

	public static void setRedisPort(int redisPort) {
		DefaultRedisConfiguration.redisPort = redisPort;
	}

	public static int getRedisTimeout() {
		return redisTimeout;
	}

	public static void setRedisTimeout(int redisTimeout) {
		DefaultRedisConfiguration.redisTimeout = redisTimeout;
	}

	public static int getRedisMaxActive() {
		return redisMaxActive;
	}

	public static void setRedisMaxActive(int redisMaxActive) {
		DefaultRedisConfiguration.redisMaxActive = redisMaxActive;
	}

	public static int getRedisMaxIdle() {
		return redisMaxIdle;
	}

	public static void setRedisMaxIdle(int redisMaxIdle) {
		DefaultRedisConfiguration.redisMaxIdle = redisMaxIdle;
	}

	public static int getRedisMaxWait() {
		return redisMaxWait;
	}

	public static void setRedisMaxWait(int redisMaxWait) {
		DefaultRedisConfiguration.redisMaxWait = redisMaxWait;
	}

	public static boolean isRedisTestOnBorrow() {
		return redisTestOnBorrow;
	}

	public static void setRedisTestOnBorrow(boolean redisTestOnBorrow) {
		DefaultRedisConfiguration.redisTestOnBorrow = redisTestOnBorrow;
	}

	public static boolean isRedisTestOnReturn() {
		return redisTestOnReturn;
	}

	public static void setRedisTestOnReturn(boolean redisTestOnReturn) {
		DefaultRedisConfiguration.redisTestOnReturn = redisTestOnReturn;
	}
}
