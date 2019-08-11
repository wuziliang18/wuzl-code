package com.yoloho.datasource.split.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切库切表注解
 * 
 * @author wuzl
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SplitTable {
	/**
	 * 分表名称
	 */
	String value();

	/**
	 * 
	 * 切分字段 必传
	 * 
	 * @return
	 */
	String splitField();

	/**
	 * 是否强制切分所有方法 默认为是所有方法强制切分，也就是说每个方法必须传切分字段 如果为false 没有的时候 不在走切分逻辑
	 * 
	 * @return
	 */
	boolean forceSplit() default true;

	/**
	 * 是否是调试模式 注意调试模式下 写和读与正常不一致，具体查看com.yoloho.datasource.split.DebugContext
	 * 
	 * @return
	 */
	boolean debug() default false;

	/**
	 * 默认表名称 没有策略的时候 必传
	 * 
	 * @return
	 */
	String defaultTable() default "";

}
