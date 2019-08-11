package com.yoloho.cache.memcache.support;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;

public class OperatorCacheUtil {

	/**
	 * 根据表名和key删除一个缓存数据
	 * 
	 * @param tableName
	 * @param key
	 */
	public static void removeCacheByNameAndKey(String tableName, Object key) {
		try {
			MemcacheCacheManager memcacheCacheManager = MemcacheCacheManager
					.getMemcacheCacheManager();
			Cache cache = memcacheCacheManager.getCache(tableName);
			cache.evict(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 手动插入一些缓存 
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public static void putCache(String cacheName,Object key,Object value){
		try {
			MemcacheCacheManager memcacheCacheManager = MemcacheCacheManager
					.getMemcacheCacheManager();
			Cache cache = memcacheCacheManager.getCache(cacheName);
			cache.put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public static <T> T get(String tableName,Object key, Class<T> clazz){
		MemcacheCacheManager memcacheCacheManager = MemcacheCacheManager
				.getMemcacheCacheManager();
		Cache cache = memcacheCacheManager.getCache(tableName);
		Object cacheResult = cache.get(key);
		if (cacheResult != null) {
			Object obj= ((ValueWrapper) cacheResult).get();
			if(clazz.isInstance(obj) ){
				return (T) obj;
			}
		}
		return null;
	}
}
