package com.yoloho.cache.memcache.support;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;

import com.yoloho.cache.memcache.annotation.ClassCacheAble;
import com.yoloho.cache.memcache.annotation.ClassCacheAbleParse;

/*
 * 分析带ClassCacheAble注解的类的缓存
 */
@Aspect
public class ClassCacheAop {
	@Autowired
	private MemcacheCacheManager memcacheCacheManager;

	/**
	 * 拦截get方法
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
//	@Around("execution(* com.yoloho.service.GenericServiceImpl.get(..))")
	@Around("execution(* com.yoloho.mybatis.common.dao.impl.GenericDaoImpl.get(..))")
	public Object checkGetCache(ProceedingJoinPoint pjp) throws Throwable {
		/* 1.获取拦截数据 */
		Object[] args = pjp.getArgs();// 调用方法的参数
		Object target = pjp.getTarget();// 获取目标对象
		String key = CacheUtil.toKey(args);
		Cache cache = null;
		ClassCacheInfo info = null;
		/* 2.判断是否需要查询缓存 */
		ClassCacheAble annotation = target.getClass().getAnnotation(
				ClassCacheAble.class);
		if (annotation != null) {
			info = ClassCacheAbleParse.parseClassCacheAnnotation(annotation);
			if (info.getSupportMethods().contains("get")) {
				if (args.length == info.getPrimaryKeys().length) {// 根据主键获取数据
					/* 2.1查询缓存 */
					cache = memcacheCacheManager.getCache(info.getCacheName());
					Object cacheResult = cache.get(key);
					if (cacheResult != null) {
						return ((ValueWrapper) cacheResult).get();
					}
				}
			}
		}
		/* 3.执行正常逻辑 */
		Object result = pjp.proceed();
		if (result != null && cache != null) {
			cache.put(key, result);// 保存缓存
//			String uniqueKey = CacheUtil.getUniqueKey(info, result);
//			if (uniqueKey != null && !"".equals(uniqueKey)) {
//				UniqueKeyCache uniqueCache = (UniqueKeyCache) memcacheCacheManager
//						.getCache(MemcacheCacheManager.UNIQUE_PREFIX
//								+ info.getCacheName());
//				uniqueCache.putKey(uniqueKey, key);// 保存唯一key到主键的副本
//			}
		}
		return result;
	}

	/**
	 * 修改缓存
	 * 
	 * @param jp
	 */
//	@AfterReturning("execution(* com.yoloho.service.GenericServiceImpl.update(..))")
	@AfterReturning("execution(* com.yoloho.mybatis.common.dao.impl.GenericDaoImpl.update(..))")
	public void updateCache(JoinPoint jp) {
		/* 1.获取拦截数据 */
		Object target = jp.getTarget();
		Object[] params = jp.getArgs();
		ClassCacheInfo info = null;
		if (params.length == 1 && params[0] != null) {
			/* 2.判断是否需要查询缓存 */
			ClassCacheAble annotation = target.getClass().getAnnotation(
					ClassCacheAble.class);
			if (annotation != null) {
				info = ClassCacheAbleParse
						.parseClassCacheAnnotation(annotation);
				if (info.getSupportMethods().contains("update")) {
					Map<String, Object> dto = CacheUtil
							.convertBeanToMap(params[0]);
					/* 2.1主键缓存操作 */
					String key = CacheUtil.getKeyValue(dto,
							info.getPrimaryKeys());
					if (key != null) {
						/* 修改主键缓存 */
						Cache cache = memcacheCacheManager.getCache(info
								.getCacheName());
						cache.put(key, params[0]);
					}
//					/* 2.2清空分组查询缓存操作 */
//					String[] groupKeys = info.getGroupKeys();
//					if (groupKeys.length != 0) {
//						this.removeGroupCache(groupKeys,
//								info.getGroupCacheNames(), dto);
//					}
				}
			}
		}
	}

//	private void removeGroupCache(String[] groupKeys, String[] groupCacheNames,
//			Map<String, Object> dto) {
//		for (int i = 0; i < groupKeys.length; i++) {
//			String groupKey = groupKeys[i];
//			String cacheKey = CacheUtil.getKeyValue(dto, groupKey);
//			Cache cache = memcacheCacheManager.getCache(groupCacheNames[i]);
//			cache.evict(cacheKey);
//		}
//	}

//	@AfterReturning("execution(* com.yoloho.service.GenericServiceImpl.remove(..))")
	@AfterReturning("execution(* com.yoloho.mybatis.common.dao.impl.GenericDaoImpl.remove(..))")
	public void removeCache(JoinPoint jp) {
		/* 1.获取拦截数据 */
		Object target = jp.getTarget();
		Object[] params = jp.getArgs();
		ClassCacheInfo info = null;
		if (params.length == 1) {
			/* 2.判断是否需要删除缓存 */
			ClassCacheAble annotation = target.getClass().getAnnotation(
					ClassCacheAble.class);
			if (annotation != null) {
				info = ClassCacheAbleParse
						.parseClassCacheAnnotation(annotation);
				if (info.getSupportMethods().contains("remove")) {
					if (params.length == info.getPrimaryKeys().length) {
						/* 2.1清空分组查询缓存操作 */
						Cache cache = memcacheCacheManager.getCache(info
								.getCacheName());
						String key = CacheUtil.toKey(params);
//						Object cacheResult = cache.get(key);// 查询缓存
//						String[] groupKeys = info.getGroupKeys();
//						if (groupKeys.length != 0) {
//							if (cacheResult == null && info.isRemoveSearchDb()) {
//								// TODO 此处未完成 如果每次删除都是先查询 然后取出主键 此处不用处理
//							}
//							if (cacheResult != null) {
//								Map<String, Object> dto = CacheUtil
//										.convertBeanToMap(((ValueWrapper) cacheResult).get());
//								this.removeGroupCache(groupKeys,
//										info.getGroupCacheNames(), dto);
//							}
//						}
						/* 2.2主键缓存删除操作 */
						cache.evict(key);
					}
				}
			}
		}
	}

//	@AfterReturning("execution(* com.yoloho.service.GenericServiceImpl.insert(..))")
//	public void insertCache(JoinPoint jp) {
//		/* 1.获取拦截数据 */
//		Object target = jp.getTarget();
//		Object[] params = jp.getArgs();
//		ClassCacheInfo info = null;
//		if (params.length == 1 && params[0] != null) {
//			/* 2.判断是否需要删除缓存 */
//			ClassCacheAble annotation = target.getClass().getAnnotation(
//					ClassCacheAble.class);
//			if (annotation != null) {
//				info = ClassCacheAbleParse
//						.parseClassCacheAnnotation(annotation);
//				if (info.isInsertClearGroupCache()) {
//					/* 清空分组查询缓存操作 */
//					String[] groupKeys = info.getGroupKeys();
//					if (groupKeys.length != 0) {
//						Map<String, Object> dto = CacheUtil
//								.convertBeanToMap(params[0]);
//						this.removeGroupCache(groupKeys,
//								info.getGroupCacheNames(), dto);
//					}
//				}
//			}
//		}
//	}
}
