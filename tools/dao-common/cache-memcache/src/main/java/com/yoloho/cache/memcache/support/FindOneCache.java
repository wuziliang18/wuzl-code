package com.yoloho.cache.memcache.support;

import net.spy.memcached.MemcachedClient;

public class FindOneCache extends UniqueKeyCache {

	public FindOneCache(String name, String realCacheName, int expire,
			MemcachedClient memcachedClient) {
		super(name, realCacheName, expire, memcachedClient);
	}
}
