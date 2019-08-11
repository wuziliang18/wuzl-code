package com.yoloho.datasource.split.utils;

import java.util.Map;

import com.dayima.core.util.BeansUtil;

/**
 * 操作切分键的工具类
 * 
 * @author wuzl
 * 
 */
public class SplitFieldUtil {
	/**
	 * 获取字段值 先尝试从mybatis的请求map中取
	 * 
	 * @param args
	 * @param fieldName
	 * @return
	 */
	public static Long getSplitFieldValueFromMybatis(Object[] args,
			String fieldName) {
		if (args == null || args.length == 0) {
			throw new RuntimeException("参数值不可以为空F");
		}
		Assert.notNull(fieldName, "分表字段不可以为空");
		if (args.length == 1 && args[0] instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, Object> requestMap = (Map<String, Object>) args[0];
			Object value = requestMap.get(fieldName);
			if (value != null) {
				return getFromParam(value, fieldName, Long.class);
			}
			// 只考虑等于的时候
			value = requestMap.get("EQ_" + fieldName);
			if (value != null) {
				return getFromParam(value, fieldName, Long.class);
			}
		}
		return getSplitFieldValue(args, fieldName, Long.class);
	}

	/**
	 * 从参数中找到分表的字段值
	 * 
	 * @param args
	 * @param fieldName
	 * @return
	 */
	public static Long getSplitFieldValue(Object[] args, String fieldName) {
		return getSplitFieldValue(args, fieldName, Long.class);
	}

	/**
	 * 从参数中找到分表的字段值 如果只有一个参数的话，类型对直接返回，否则转换成目标类型，都不可以返回null 多个参数的时候，最好分表的字段放到第一个
	 * 
	 * @param args
	 * @param fieldName
	 * @param clazz
	 *            分表字段的类型
	 * @return
	 */
	public static <T> T getSplitFieldValue(Object[] args, String fieldName,
			Class<T> clazz) {
		if (args == null || args.length == 0) {
			throw new RuntimeException("参数值不可以为空F");
		}
		Assert.notNull(fieldName, "分表字段不可以为空");
		Assert.notNull(clazz, "分表字段的类型不可以为空");
		/* 1.只有1个参数的时候 */
		if (args.length == 1) {
			return getFromParam(args[0], fieldName, clazz);
		}
		/* 2.多个参数的时候 */
		for (Object obj : args) {
			Object value = getFromParam(obj, fieldName, clazz);
			if (value != null) {
				return clazz.cast(value);
			}
		}
		return null;
	}

	/**
	 * 从对象中获取转换值
	 * 
	 * @param param
	 * @param fieldName
	 * @param clazz
	 * @return
	 */
	private static <T> T getFromParam(Object param, String fieldName,
			Class<T> clazz) {
		/* 1参数类型为分表字段类型 */
		if (param.getClass().equals(clazz)
				|| param.getClass().getSimpleName().toLowerCase()
						.equals(clazz.getSimpleName().toLowerCase())) {
			return clazz.cast(param);
		}
		/* 1尝试强转为表字段类型 */
		Object value = cast(param, clazz);
		if (value != null) {
			return clazz.cast(value);
		}
		/* 1 bean类型 */
		return BeansUtil.getValueFromBean(param, fieldName, clazz, true);
	}

	public static final Object cast(Object value, Class<?> clazz) {
		try {
			if (clazz.equals(Long.class) || clazz.equals(long.class)) {
				return Long.parseLong(String.valueOf(value));
			}
			if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
				return Integer.parseInt((String.valueOf(value)));
			}
		} catch (NumberFormatException e) {
		}
		return null;
	}

}
