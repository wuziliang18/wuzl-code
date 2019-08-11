package com.yoloho.mybatis.common.cache;

import org.springframework.data.redis.connection.jedis.JedisConnection;

/**
 * Created by liujunshi on 15-5-21.
 */
public interface RedisCallback {

	Object doWithRedis(JedisConnection connection);
}