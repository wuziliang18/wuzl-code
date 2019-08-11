package com.yoloho.server.queue.redis;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.yoloho.server.queue.redis.bean.RedisConfigBean;
import com.yoloho.server.queue.redis.bean.RedisConnection;
import com.yoloho.server.queue.redis.config.DefaultRedisConfiguration;

/**
 * redis连接的工厂类
 * 
 * @author wuzl
 * 
 */
public class RedisConnectionFactory {
	private static Log log = LogFactory.getLog(RedisConnectionFactory.class);
	private static JedisPool default_redis_pool;// 默认redis 的连接池
	private final static String DEFAULT_REDIS_NAME = "default_redis";
	private final static Map<String, JedisPool> redisPoolMap = new HashMap<String, JedisPool>();// 保存各个redis数据库的连接池
	// 默认加载defaults的redis配置
	static {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(DefaultRedisConfiguration.getRedisMaxActive());
		config.setMaxIdle(DefaultRedisConfiguration.getRedisMaxIdle());
		config.setMaxWait(DefaultRedisConfiguration.getRedisMaxWait());
		config.setTestOnBorrow(DefaultRedisConfiguration.isRedisTestOnBorrow());
		config.setTestOnReturn(DefaultRedisConfiguration.isRedisTestOnReturn());
		default_redis_pool = new JedisPool(config,
				DefaultRedisConfiguration.getRedisHost(),
				DefaultRedisConfiguration.getRedisPort(),
				DefaultRedisConfiguration.getRedisTimeout());
		// 保存起来
		redisPoolMap.put(DEFAULT_REDIS_NAME, default_redis_pool);
		log.info("初始化默认redis连接池成功");
	}

	/**
	 * 获取默认redis的一个连接
	 * 
	 * @return
	 */
	public static synchronized RedisConnection getRedisConnection() {
		return getRedisConnection(DEFAULT_REDIS_NAME);
	}

	/**
	 * 根据redis配置的名称获取一个redis连接
	 * 
	 * @param redisDbName
	 *            +“
	 * @return
	 */
	public static synchronized RedisConnection getRedisConnection(
			String redisDbName) {
		RedisConnection redisConnection = new RedisConnection();
		if (redisPoolMap.keySet().contains(redisDbName)) {
			redisConnection.setRedisDbName(redisDbName);
			Jedis jedis = redisPoolMap.get(redisDbName).getResource();
			redisConnection.setJedis(jedis);
			log.info("获取一个名称为【" + redisDbName + "】的连接成功");
			return redisConnection;
		}
		log.info("没有找到一个名称为【" + redisDbName + "】的连接");
		throw new RuntimeException("没有找到一个名称为【" + redisDbName + "】的连接");
	}

	/**
	 * 添加一个redis数据连接池
	 * 
	 * @param redisClogonfig
	 */
	public static synchronized void addRedisPool(RedisConfigBean redisConfig) {
		String redisDbName = redisConfig.getRedisDbName();
		/* 1.非空判断 */
		if (redisDbName == null || "".equals(redisDbName)) {
			throw new RuntimeException("redisDbName不可以为空");
		}
		/* 2.判断是否存在 */
		if (redisPoolMap.keySet().contains(redisDbName)) {
			throw new RuntimeException("redisDbName为【" + redisDbName
					+ "】的数据连接池已经存在");
		}
		/* 3.生成连接池 */
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxActive(redisConfig.getRedisMaxActive());
			config.setMaxIdle(redisConfig.getRedisMaxIdle());
			config.setMaxWait(redisConfig.getRedisMaxWait());
			config.setTestOnBorrow(redisConfig
					.isRedisTestOnBorrow());
			config.setTestOnReturn(redisConfig
					.isRedisTestOnReturn());
			JedisPool newRedisPool = new JedisPool(config,
					redisConfig.getRedisHost(),
					redisConfig.getRedisPort(),
					redisConfig.getRedisTimeout());
			redisPoolMap.put(redisDbName, newRedisPool);
			log.info("初始化一个新的名称为【" + redisDbName + "】的连接池成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("初始化一个新的名称为【" + redisDbName + "】的连接池失败");
			throw new RuntimeException("redisDbName为【" + redisDbName
					+ "】的数据连接池初始化失败");
		}
	}

	/**
	 * 归还连接
	 * 
	 * @param redisConnection
	 */
	public static void returnResource(RedisConnection redisConnection) {
		if (redisConnection.getJedis() == null) {
			return;
		}
		String redisDbName = redisConnection.getRedisDbName();
		if (redisPoolMap.keySet().contains(redisDbName)) {
			redisPoolMap.get(redisDbName).returnResource(
					redisConnection.getJedis());
			log.info("归还一个名称为【" + redisDbName + "】的连接池成功");
		} else {
			throw new RuntimeException("redisDbName为【" + redisDbName
					+ "】的数据连接池不存在");
		}
	}
}
