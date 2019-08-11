package com.yoloho.server.queue.redis.util;

import com.yoloho.server.queue.redis.RedisConnectionFactory;
import com.yoloho.server.queue.redis.bean.RedisConfigBean;
import com.yoloho.server.queue.redis.queue.constants.RedisPoolDefaultsValueConstants;
import com.yoloho.server.queue.redis.queue.constants.RedisQueueConstants;
import com.yoloho.server.queue.utils.IniEditor;
import com.yoloho.server.queue.utils.StringUtil;

/**
 * 初始化连接池的一些数据从配置文件中 要求配置文件的格式正确
 * 
 * @author wuzl
 * 
 */
public class InitRedisPool {
	/**
	 * 配置完整连接池并添加入环境中
	 * @param redisDbName
	 * @param ini
	 */
	public static void initRedisPoolFullConfig(String redisDbName,IniEditor ini) {
		RedisConfigBean redisConfigBean = new RedisConfigBean();
		redisConfigBean.setRedisDbName(redisDbName);
		/* 1配置必需的属性 */
		InitRedisPool.initRedisPoolBaseConfig(redisConfigBean, ini);
		/* 2配置一些非必需的属性 */
		InitRedisPool.initRedisPoolHavaDefaultValueConfig(redisConfigBean, ini);
		/* 3添加到连接池 */
		RedisConnectionFactory.addRedisPool(redisConfigBean);
	}

	/**
	 * 初始化基本的redis配置
	 * 
	 * @param redisConfigBean
	 * @param ini
	 */
	public static void initRedisPoolBaseConfig(RedisConfigBean redisConfigBean,
			IniEditor ini) {
		/* 1host为必需参数 */
		String host = ini.get(RedisQueueConstants.REDIS_CONFIG,
				RedisQueueConstants.REDIS_DB_HOST);
		if (StringUtil.isEmpty(host)) {
			throw new RuntimeException("配置文件中没有【"
					+ RedisQueueConstants.REDIS_DB_HOST + "】的配置");
		}
		/* 2port为必需参数 */
		String port = ini.get(RedisQueueConstants.REDIS_CONFIG,
				RedisQueueConstants.REDIS_DB_PORT);
		if (StringUtil.isEmpty(port)) {
			throw new RuntimeException("配置文件中没有【"
					+ RedisQueueConstants.REDIS_DB_PORT + "】的配置");
		}
		int port_int = 0;
		try {
			port_int = Integer.parseInt(port);
		} catch (Exception e) {
			throw new RuntimeException("配置文件中【"
					+ RedisQueueConstants.REDIS_DB_PORT + "】的配置有误，不是一个正确的数字");
		}
		redisConfigBean.setRedisHost(host);
		redisConfigBean.setRedisPort(port_int);
	}

	/**
	 * 初始化非必需的连接池配置
	 * 如有且正确会复设置，不正确会报错
	 * 如果没有用默认值
	 * @param redisConfigBean
	 * @param ini
	 */
	public static void initRedisPoolHavaDefaultValueConfig(RedisConfigBean redisConfigBean,
			IniEditor ini) {
		String redisMaxActiveString = ini.get(RedisQueueConstants.REDIS_CONFIG,
				RedisQueueConstants.REDIS_DB_MAX_ACTIVE);
		if (StringUtil.isEmpty(redisMaxActiveString)) {
			redisConfigBean
					.setRedisMaxActive(RedisPoolDefaultsValueConstants.REDIS_DB_MAX_ACTIVE_DEFAULT_VALUE);
		} else {
			try {
				redisConfigBean.setRedisMaxActive(Integer
						.parseInt(redisMaxActiveString));
			} catch (NumberFormatException e) {
				throw new RuntimeException("配置文件中【"
						+ RedisQueueConstants.REDIS_DB_MAX_ACTIVE
						+ "】的配置有误，不是一个正确的数字");
			}
		}
		String redisMaxIdleString = ini.get(RedisQueueConstants.REDIS_CONFIG,
				RedisQueueConstants.REDIS_DB_MAX_IDLE);
		if (StringUtil.isEmpty(redisMaxIdleString)) {
			redisConfigBean
					.setRedisMaxIdle(RedisPoolDefaultsValueConstants.REDIS_DB_MAX_IDLE_DEFAULT_VALUE);
		} else {
			try {
				redisConfigBean.setRedisMaxIdle(Integer
						.parseInt(redisMaxIdleString));
			} catch (NumberFormatException e) {
				throw new RuntimeException("配置文件中【"
						+ RedisQueueConstants.REDIS_DB_MAX_IDLE
						+ "】的配置有误，不是一个正确的数字");
			}
		}
		String redisMaxWaitString = ini.get(RedisQueueConstants.REDIS_CONFIG,
				RedisQueueConstants.REDIS_DB_MAX_WAIT);
		if (StringUtil.isEmpty(redisMaxWaitString)) {
			redisConfigBean
					.setRedisMaxWait(RedisPoolDefaultsValueConstants.REDIS_DB_MAX_WAIT_DEFAULT_VALUE);
		} else {
			try {
				redisConfigBean.setRedisMaxWait(Integer
						.parseInt(redisMaxIdleString));
			} catch (NumberFormatException e) {
				throw new RuntimeException("配置文件中【"
						+ RedisQueueConstants.REDIS_DB_MAX_WAIT
						+ "】的配置有误，不是一个正确的数字");
			}
		}
		String redisTestOnBorrowString = ini.get(
				RedisQueueConstants.REDIS_CONFIG,
				RedisQueueConstants.REDIS_DB_TEST_ON_BORROW);
		if (StringUtil.isEmpty(redisTestOnBorrowString)) {
			redisConfigBean
					.setRedisTestOnBorrow(RedisPoolDefaultsValueConstants.REDIS_DB_TEST_ON_BORROW_DEFAULT_VALUE);
		} else {
			try {
				redisConfigBean.setRedisTestOnBorrow(Boolean
						.parseBoolean(redisTestOnBorrowString));
			} catch (NumberFormatException e) {
				throw new RuntimeException("配置文件中【"
						+ RedisQueueConstants.REDIS_DB_TEST_ON_BORROW
						+ "】的配置有误，不是一个正确的布尔类型");
			}
		}
		String redisTestOnReturnString = ini.get(
				RedisQueueConstants.REDIS_CONFIG,
				RedisQueueConstants.REDIS_DB_TEST_ON_RETURN);
		if (StringUtil.isEmpty(redisTestOnReturnString)) {
			redisConfigBean
					.setRedisTestOnReturn(RedisPoolDefaultsValueConstants.REDIS_DB_TEST_ON_RETURN_DEFAULT_VALUE);
		} else {
			try {
				redisConfigBean.setRedisTestOnReturn(Boolean
						.parseBoolean(redisTestOnReturnString));
			} catch (NumberFormatException e) {
				throw new RuntimeException("配置文件中【"
						+ RedisQueueConstants.REDIS_DB_TEST_ON_RETURN
						+ "】的配置有误，不是一个正确的布尔类型");
			}
		}
		String redisTimeoutString = ini.get(RedisQueueConstants.REDIS_CONFIG,
				RedisQueueConstants.REDIS_DB_TIMEOUT);
		if (StringUtil.isEmpty(redisTimeoutString)) {
			redisConfigBean
					.setRedisTimeout(RedisPoolDefaultsValueConstants.REDIS_DB_TIMEOUT_DEFAULT_VALUE);
		} else {
			try {
				redisConfigBean.setRedisTimeout(Integer
						.parseInt(redisTimeoutString));
			} catch (NumberFormatException e) {
				throw new RuntimeException("配置文件中【"
						+ RedisQueueConstants.REDIS_DB_TIMEOUT
						+ "】的配置有误，不是一个正确的数字");
			}
		}
	}
}
