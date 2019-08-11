package com.yoloho.cache.memcache.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.support.SimpleValueWrapper;

import com.yoloho.cache.memcache.annotation.ClassCacheAbleParse;

import net.spy.memcached.MemcachedClient;

public class UniqueKeyCache extends MemcachedCache {
	protected String realCacheName;
	private static Log log = LogFactory.getLog(UniqueKeyCache.class);

	public UniqueKeyCache(String name, String realCacheName, int expire,
			MemcachedClient memcachedClient) {
		super(name, expire, memcachedClient);
		this.realCacheName = realCacheName;
	}

	@Override
	public ValueWrapper get(Object key) {
		try {
			Object value = getValue(key);
			if (value != null) {
				value = getValue(realCacheName, value);
				if (value == null) {
					return null;
				}
				return new SimpleValueWrapper(value);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void put(Object key, Object value) {
		try {
			if (value != null) {
				String primaryKey = this.getPrimaryKey(value);
				if (primaryKey != null) {
					putValue(key, primaryKey);
					putValue(realCacheName, primaryKey, value);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	/**
	 * 获取主键
	 * 
	 * @param value
	 * @return
	 */
	protected String getPrimaryKey(Object value) {
		ClassCacheInfo cacheInfo = ClassCacheAbleParse
				.getClassCacheAnnotation(realCacheName);
		if (cacheInfo == null) {
			throw new RuntimeException(String.format("没有找到【%s】的类注解",
					realCacheName));
		}
		String primaryKey = CacheUtil.getPrimaryKey(cacheInfo, value);
		return primaryKey;
	}

	/**
	 * 保存引用的key
	 * 
	 * @param key
	 * @param unique
	 */
	public void putKey(String key, String unique) {
		putValue(key, unique);
	}
}
