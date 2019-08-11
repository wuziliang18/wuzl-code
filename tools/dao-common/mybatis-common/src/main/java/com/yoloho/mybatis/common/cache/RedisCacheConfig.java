package com.yoloho.mybatis.common.cache;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
/**
 * Created by liujunshi on 15-5-21.
 */
public class RedisCacheConfig{
	private static JedisConnectionFactory connectionFactory;

	public static String GlobalPrefix = "_j_";
	public static JedisConnectionFactory getConnectionFactory(){
		return connectionFactory;
	}
	public static String cachePrefix = "";
	public static void setConnectionFactory(JedisConnectionFactory connectionFactory) {
		RedisCacheConfig.connectionFactory = connectionFactory;
	}

	public static String getCachePrefix() {
		return cachePrefix;
	}

	public static void setCachePrefix(String cachePrefix) {
		RedisCacheConfig.cachePrefix = cachePrefix;
	}
}
