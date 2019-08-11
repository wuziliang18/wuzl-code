package com.yoloho.cache.memcache.annotation;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.InitializingBean;

import com.yoloho.cache.memcache.support.ClassCacheInfo;
import com.yoloho.cache.memcache.support.ScanPackageAnnotation;

/**
 * 解析
 * 
 * @author wuzl
 * 
 */
public class ClassCacheAbleParse implements InitializingBean {
	private static final ConcurrentMap<String, ClassCacheInfo> cacheInfoMap = new ConcurrentHashMap<String, ClassCacheInfo>();
	private List<String> scanPackageName;

	public void setScanPackageName(List<String> scanPackageName) {
		this.scanPackageName = scanPackageName;
	}

	/**
	 * 解析ClassCacheAble注解
	 * 
	 * @param annotation
	 * @return
	 */
	public static ClassCacheInfo parseClassCacheAnnotation(
			ClassCacheAble annotation) {
		String cacheName = annotation.value();
		ClassCacheInfo info = cacheInfoMap.get(cacheName);
		if (info != null) {
			return info;
		}
		synchronized (ClassCacheAbleParse.class) {
			info = cacheInfoMap.get(cacheName);
			if (info == null) {
				info = new ClassCacheInfo();
				info.setCacheName(annotation.value());
				info.setPrimaryKeys(annotation.primaryKey().split(","));
				info.setUniqueKey(annotation.uniqueKey());
				info.setGroupKeys(annotation.groupKeys());
				info.setRemoveSearchDb(annotation.removeSearchDb());
				info.setGroupCacheNames(annotation.groupCacheName());
				info.setSupportMethods(Arrays.asList(annotation.methods()));
				info.setInsertClearGroupCache(annotation
						.insertClearGroupCache());
				if (info.getGroupKeys().length != info.getGroupCacheNames().length) {
					throw new RuntimeException(
							"groupKeys数组数必须与groupCacheName数组数一致");
				}
				cacheInfoMap.put(cacheName, info);
			}
		}
		return info;
	}

	public static ClassCacheInfo getClassCacheAnnotation(String cacheName) {
		return cacheInfoMap.get(cacheName);
	}

	/**
	 * 加载所有的cache注解信息
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		for (String packageName : scanPackageName) {
			List<ClassCacheAble> result = ScanPackageAnnotation.scanAnnotation(
					packageName, ClassCacheAble.class);
			for (ClassCacheAble annotation : result) {
				parseClassCacheAnnotation(annotation);
			}
		}
	}
}
