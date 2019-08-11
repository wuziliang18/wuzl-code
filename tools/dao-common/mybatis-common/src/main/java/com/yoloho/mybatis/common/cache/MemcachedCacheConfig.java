package com.yoloho.mybatis.common.cache;

import net.spy.memcached.MemcachedClient;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MemcachedCacheConfig implements ApplicationContextAware {
	private static ApplicationContext ctx;
	public static MemcachedClient memcachedClient;

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		MemcachedCacheConfig.ctx = ctx;
		memcachedClient = (MemcachedClient)ctx.getBean("memcachedClient");
	}
	
	public static int expiration = 60 * 60;
	public static boolean compression = false;
	public static boolean asyncget = false;
	public static String keyprefix = "";
	public static int timeout = 5;

	public static void setExpiration(int expiration) {
		MemcachedCacheConfig.expiration = expiration;
	}

	public static void setCompression(boolean compression) {
		MemcachedCacheConfig.compression = compression;
	}

	public static void setAsyncget(boolean asyncget) {
		MemcachedCacheConfig.asyncget = asyncget;
	}

	public static void setKeyprefix(String keyprefix) {
		MemcachedCacheConfig.keyprefix = keyprefix;
	}

	public static void setTimeout(int timeout) {
		MemcachedCacheConfig.timeout = timeout;
	}
}
