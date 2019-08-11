package com.yoloho.cache.memcache.support;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheUtil {
	/**
	 * 生成键的值拼接
	 * 
	 * @param array
	 * @return
	 */
	public static String toKey(Object[] array) {
		int iMax = array.length - 1;
		if (iMax == 0) {
			return array[0].toString();
		}
		StringBuilder b = new StringBuilder();
		for (int i = 0;; i++) {
			b.append(String.valueOf(array[i]));
			if (i == iMax)
				return b.toString();
			b.append(", ");
		}
	}

	/**
	 * 获取主键
	 * 
	 * @param info
	 * @param obj
	 * @return
	 */
	public static String getPrimaryKey(ClassCacheInfo info, Object obj) {
		/* 如果是null直接返回 */
		if (obj == null) {
			return null;
		}
		String[] keys = info.getPrimaryKeys();
		return getKeyFromObject(obj, keys);
	}

	/**
	 * 获取一组key
	 * 
	 * @param groupKeys
	 * @param obj
	 * @return
	 */
	public static String getGroupKey(String groupKeys, Object obj) {
		/* 如果是null直接返回 */
		if (obj == null || groupKeys == null || groupKeys.equals("")) {
			return null;
		}
		String[] keys = groupKeys.split(",");
		return getKeyFromObject(obj, keys);
	}

	private static String getKeyFromObject(Object obj, String[] keys) {
		if (keys == null || keys.length == 0) {
			return null;
		}
		Map<String, String> keyMap = new HashMap<String, String>();
		try {
			/* 1.同过内省获取bean信息 */
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			/* 2.获取bean属性的描述器 */
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			/* 3.遍历bean的属性 */
			for (PropertyDescriptor property : propertyDescriptors) {
				/* 3.1 获取属性名称 */
				String key = property.getName();
				for (String keyName : keys) {
					if (key.equals(keyName)) {
						/* 3.2.1 获取get方法 */
						Method getter = property.getReadMethod();
						/* 3.2.3 获取值 */
						Object value = getter.invoke(obj);
						keyMap.put(keyName, value.toString());
						if (keyMap.keySet().size() == keys.length) {
							continue;
						}
					}
				}

			}
			if (keys.length == 1) {
				return keyMap.get(keys[0]);
			} else {
				StringBuilder b = new StringBuilder();
				for (int i = 0; i < keys.length; i++) {
					b.append(keyMap.get(keys[i]));
					if (i == keys.length - 1)
						return b.toString();
					b.append(", ");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 获取唯一key的值
	 * 
	 * @param info
	 * @param obj
	 * @return
	 */
	public static String getUniqueKey(ClassCacheInfo info, Object obj) {
		/* 1.如果是null直接返回 */
		if (obj == null) {
			return null;
		}
		String uniqueKey = info.getUniqueKey();
		if (uniqueKey != null && !"".equals(uniqueKey)) {
			try {
				/* 2.同过内省获取bean信息 */
				BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
				/* 3.获取bean属性的描述器 */
				PropertyDescriptor[] propertyDescriptors = beanInfo
						.getPropertyDescriptors();
				/* 4.遍历bean的属性 */
				for (PropertyDescriptor property : propertyDescriptors) {
					/* 4.1 获取属性名称 */
					String key = property.getName();
					if (key.equals(uniqueKey)) {
						/* 4.2.1 获取get方法 */
						Method getter = property.getReadMethod();
						/* 4.2.3 获取值 */
						Object value = getter.invoke(obj);
						return value.toString();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		}
		return null;
	}

	/**
	 * bean转化成map
	 * 
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> convertBeanToMap(Object bean) {
		/* 1.如果bean是null直接返回null */
		if (bean == null) {
			return null;
		}
		/* 2.定义返回结果map */
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			/* 3.同过内省获取bean信息 */
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
			/* 4.获取bean属性的描述器 */
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			/* 5.遍历bean的属性 */
			for (PropertyDescriptor property : propertyDescriptors) {
				/* 5.1 获取属性名称 */
				String key = property.getName();
				/* 5.2 过滤class方法 */
				if (!key.equals("class")) {
					/* 5.2.1 获取get方法 */
					Method getter = property.getReadMethod();
					/* 5.2.2 获取值 */
					Object value = getter.invoke(bean);
					returnMap.put(key, value);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("bean[" + bean.getClass().getName()
					+ "]转化为map时出错");
		}
		/* 6.返回结果 */
		return returnMap;
	}

	/**
	 * 从map中生成key的字符串
	 * 
	 * @param dto
	 * @param key
	 * @return
	 */
	public static String getKeyValue(Map<String, Object> dto, String key) {
		if (key == null || key.equals("")) {
			return null;
		}
		String[] keys = key.split(",");
		return getKeyValue(dto, keys);
	}
	
	public static String getKeyValue(Map<String, Object> dto, String[] keys) {
		if (keys.length == 1) {
			return dto.get(keys[0]).toString();
		} else {
			StringBuilder b = new StringBuilder();
			for (int i = 0; i < keys.length; i++) {
				b.append(dto.get(keys[i]));
				if (i == keys.length - 1)
					return b.toString();
				b.append(", ");
			}
			return b.toString();
		}
	}
}
