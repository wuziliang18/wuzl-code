package com.yoloho.cache.memcache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yoloho.cache.memcache.support.MemcacheCacheManager;

/**
 * 类开启缓存注解
 * 
 * @author wuzl
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassCacheAble {
	/**
	 * 使用缓存的cacheName
	 */
	String value() default MemcacheCacheManager.DEFAULT_CACHE_NAME;

	/**
	 * 生成主键参数 可多个 逗号分割
	 */
	String primaryKey() default "";

	/**
	 * 非主键的唯一键
	 */
	String uniqueKey() default "";

	/**
	 * 查询列表分组数组 如果每一个分组里使用多个参数 逗号分割
	 */
	String[] groupKeys() default {};

	/**
	 * 分组的cacheName数组 必须与groupKeys 数目一致
	 */
	String[] groupCacheName() default {};

	/**
	 * 删除数据的时候如果有分组配置，是否查询数据库，默认为false只查询get缓存
	 */
	boolean removeSearchDb() default false;
	
	/**
	 * 注解支持方法
	 */
	String[] methods() default { "get", "update", "remove" };
	
	/**
	 * 插入数据时，是否清空分组缓存 默认false
	 */
	boolean insertClearGroupCache() default false;
}
