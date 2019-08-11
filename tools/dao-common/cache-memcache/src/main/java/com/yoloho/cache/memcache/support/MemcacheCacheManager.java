package com.yoloho.cache.memcache.support;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

public class MemcacheCacheManager extends AbstractCacheManager {
	private static MemcacheCacheManager memcacheCacheManager;
	public static final String DEFAULT_CACHE_NAME = "defaultCache";
	public static final String UNIQUE_PREFIX = "UNIQUE_";
	public static final String FIND_ONE_PREFIX = "ONE_";
	public static final String FIND_LIST_PREFIX = "LIST_";
	private static Log log = LogFactory.getLog(MemcacheCacheManager.class);
	private MemcachedClient memcachedClient; // xmemcached的客户端
	private ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();
	private Map<String, Integer> expireMap = new HashMap<String, Integer>(); // 缓存的时间
	public MemcacheCacheManager() {
		memcacheCacheManager=this;
	}
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	@Override
	protected Collection<? extends Cache> loadCaches() {
		cacheMap.put(DEFAULT_CACHE_NAME, getCache(DEFAULT_CACHE_NAME));// 默认的cache
		Collection<Cache> values = cacheMap.values();
		return values;
	}

	@Override
	public Cache getCache(String name) {
		if (name == null || name.equals("")) {
			throw new RuntimeException("cache名称不可以为空");
		}
		Cache cache = cacheMap.get(name);
		if (cache == null) {
			Integer expire = expireMap.get(name);
			if (expire == null) {
				expire =  expireMap.get("defaultExpire");
				expireMap.put(name, expire);
			}
			synchronized (this) {
				cache = cacheMap.get(name);
				if (cache == null) {
					if (name.startsWith(UNIQUE_PREFIX)) {
						cache = new UniqueKeyCache(name, name.substring(
								UNIQUE_PREFIX.length(), name.length()),
								expire.intValue(), memcachedClient);
						log.info(String.format("新建一个唯一键缓存【%s】", name));
					}else if(name.startsWith(FIND_ONE_PREFIX)){ 
						cache = new FindOneCache(name, name.substring(
								FIND_ONE_PREFIX.length(), name.length()),
								expire.intValue(), memcachedClient);
						log.info(String.format("新建一个查询单个结果的缓存【%s】", name));
					}else if(name.startsWith(FIND_LIST_PREFIX)){
						cache = new MemcachedCache(name, expire.intValue(),
								memcachedClient);
						log.info(String.format("新建一个LIST查询缓存【%s】", name));
					}else {
						cache = new MemcachedCache(name, expire.intValue(),
								memcachedClient);
						log.info(String.format("新建一个普通缓存【%s】", name));
					}
					cacheMap.put(name, cache);
				}
			}
		}
		return cache;
	}

	public void setConfigMap(Map<String, Integer> configMap) {
		this.expireMap = configMap;
	}
	
	public static MemcacheCacheManager getMemcacheCacheManager(){
		return memcacheCacheManager;
	}
}
