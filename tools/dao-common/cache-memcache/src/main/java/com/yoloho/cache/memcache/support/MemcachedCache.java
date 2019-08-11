package com.yoloho.cache.memcache.support;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;


public class MemcachedCache implements Cache {
	private static Log log = LogFactory.getLog(MemcachedCache.class);
	protected final String name;
	protected int expire;
	protected MemcachedClient memcachedClient;
	protected String realCacheName = null;

	public MemcachedCache(String name, int expire,
	                      MemcachedClient memcachedClient) {
		this.name = name;
		this.expire = expire;
		this.memcachedClient = memcachedClient;
	}

	@Override
	public void clear() {
		log.error("当前版本不支持clear");
	}

	@Override
	public void evict(Object key) {
		try {
			String realKey = name + "_" + key;
			memcachedClient.delete(realKey);
		} catch (Exception ex) {
			log.error(ex);
		}
	}

	protected Object getValue(Object key) {
		try {
			String realKey = name + "_" + key;
			return memcachedClient.get(realKey);
		} catch (Exception ex) {
			log.error(ex);
		}
		return null;
	}

	protected Object getValue(String cacheName, Object key) {
		try {
			String realKey = cacheName + "_" + key;
			return memcachedClient.get(realKey);
		} catch (Exception ex) {
			log.error(ex);
		}
		return null;
	}

	@Override
	public ValueWrapper get(Object key) {
		ValueWrapper wrapper = null;
		Object value = getValue(key);
		if (value != null) {
			wrapper = new SimpleValueWrapper(value);
			log.debug(String.format("缓存【%s】命中【%s】", name, key));
		}
		return wrapper;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void put(Object key, Object value) {
		putValue(key, value);
	}

	protected void putValue(Object key, Object value) {
		try {
			String realKey = name + "_" + key;
			memcachedClient.set(realKey, expire, value);
		} catch (Exception ex) {
			log.error(ex);
		}
	}

	protected void putValue(String cacheName, Object key, Object value) {
		try {
			String realKey = cacheName + "_" + key;
			memcachedClient.set(realKey, expire, value);
		} catch (Exception ex) {
			log.error(ex);
		}
	}

	@Override
	public Object getNativeCache() {
		return this.memcachedClient;
	}

}
