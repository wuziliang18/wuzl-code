package com.yoloho.mybatis.common.cache;

import java.io.Serializable;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReadWriteLock;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheException;

import com.dayima.core.util.MD5Util;

/**
 * The Memcached-based Cache implementation.
 *
 * @author Simone Tripodi
 */
public final class MemcachedCache implements Cache {
	private MemcachedClient memcachedClient;

	private static final Log LOG = LogFactory.getLog(MemcachedCache.class);

	/**
	 * The {@link ReadWriteLock}.
	 */
	private final ReadWriteLock readWriteLock = new DummyReadWriteLock();

	/**
	 * The cache id.
	 */
	private final String id;

	/**
	 * Builds a new Memcached-based Cache.
	 *
	 * @param id the Mapper id.
	 */

	public MemcachedCache(String id) {
		this.memcachedClient = MemcachedCacheConfig.memcachedClient;
		this.id = id;
	}

	private String toKeyString(final Object key) {
		// issue #1, key too long
		String keyString = MemcachedCacheConfig.keyprefix + MD5Util.MD5(key.toString());
		if (LOG.isDebugEnabled()) {
			LOG.debug("Object key '" + key + "' converted in '" + keyString + "'");
		}
		return keyString;
	}

	private Object retrieve(final String keyString) {
		Object retrieved = null;
		try {
			if (MemcachedCacheConfig.asyncget) {
				Future<Object> future;
				if (MemcachedCacheConfig.compression) {
					future = memcachedClient.asyncGet(keyString, new CompressorTranscoder());
				} else {
					future = memcachedClient.asyncGet(keyString);
				}

				try {
					retrieved = future.get(MemcachedCacheConfig.timeout, java.util.concurrent.TimeUnit.SECONDS);
				} catch (Exception e) {
					future.cancel(false);
					throw new CacheException(e);
				}
			} else {
				if (MemcachedCacheConfig.compression) {
					retrieved = memcachedClient.get(keyString, new CompressorTranscoder());
				} else {
					retrieved = memcachedClient.get(keyString);
				}
			}
		} catch (Exception ex) {
			LOG.error("Getting object error (" + keyString + ")", ex);
		}
		return retrieved;
	}

	/**
	 * {@inheritDoc}
	 */
	public void clear() {
		//LOG.error("Flush");
		//memcachedClient.flush();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getObject(Object key) {
		String keyString = toKeyString(key);
		Object ret = retrieve(keyString);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Retrived object (" + keyString + ", " + ret + ")");
		}

		return ret;
	}

	/**
	 * {@inheritDoc}
	 */
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getSize() {
		return Integer.MAX_VALUE;
	}

	/**
	 * {@inheritDoc}
	 */
	public void putObject(Object key, Object value) {
		String keyString = toKeyString(key);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Putting object (" + keyString + ", " + value + ")");
		}

		storeInMemcached(keyString, value);
	}

	private void storeInMemcached(String keyString, Object value) {
		if (value != null && !Serializable.class.isAssignableFrom(value.getClass())) {
			throw new CacheException("Object of type '" + value.getClass().getName()
					+ "' that's non-serializable is not supported by Memcached");
		}
		memcachedClient.set(keyString, MemcachedCacheConfig.expiration, value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Object removeObject(Object key) {
		String keyString = toKeyString(key);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Removing object '" + keyString + "'");
		}

		Object result = getObject(key);
		if (result != null) {
			memcachedClient.delete(keyString);
		}
		return result;
	}

}

