package com.yoloho.mybatis.common.cache;

import com.dayima.core.util.MD5Util;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by liujunshi on 15-5-21.
 */
public class RedisCache implements Cache{
	Logger logger = LoggerFactory.getLogger(RedisCache.class);
	private final ReadWriteLock readWriteLock = new DummyReadWriteLock();

	private String id;

	public RedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		logger.debug("Init Cache for" + id);
		this.id = id;
	}

	private Object execute(RedisCallback callback) {
		JedisConnection connection = null;
		try {
			connection = RedisCacheConfig.getConnectionFactory().getConnection();
			return callback.doWithRedis(connection);
		} finally {
			if(connection != null){
				try {
					connection.close();
				}catch(Exception e){}
			}
		}
	}

	public String getId() {
		return this.id;
	}

	public int getSize() {
		return (Integer) execute(new RedisCallback() {
			public Object doWithRedis(JedisConnection connection) {
				Map<byte[], byte[]> result = connection.hGetAll(id.toString().getBytes());
				return result.size();
			}
		});
	}

	public void putObject(final Object key, final Object value) {
		execute(new RedisCallback() {
			public Object doWithRedis(JedisConnection connection) {
				String keyStr = makeKey(key);
				logger.debug("PutObject:Key=" + keyStr +";Object:" + value);
				connection.hSet(id.toString().getBytes(), keyStr.toString().getBytes(), SerializeUtil.serialize(value));
				return null;
			}
		});
	}

	public Object getObject(final Object key) {
		return execute(new RedisCallback() {
			public Object doWithRedis(JedisConnection connection) {
				String keyStr = makeKey(key);
				logger.debug("GetObject:Key=" + keyStr);
				return SerializeUtil.unserialize(connection.hGet(id.toString().getBytes(), keyStr.toString().getBytes()));
			}
		});
	}

	public Object removeObject(final Object key) {
		return execute(new RedisCallback() {
			public Object doWithRedis(JedisConnection connection) {
				String keyStr = makeKey(key);
				logger.debug("RemoveObject:Key=" + keyStr);
				return connection.hDel(id.toString().getBytes(), keyStr.toString().getBytes());
			}
		});
	}

	public void clear() {
		execute(new RedisCallback() {
			public Object doWithRedis(JedisConnection connection) {
				connection.del(id.toString().getBytes());
				logger.debug("Clear:id=" + id);
				return null;
			}
		});
	}

	private String makeKey(Object obj){
		return RedisCacheConfig.GlobalPrefix + RedisCacheConfig.getCachePrefix() + MD5Util.MD5(obj.toString());
	}
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	@Override
	public String toString() {
		return "Redis {" + id + "}";
	}

}
