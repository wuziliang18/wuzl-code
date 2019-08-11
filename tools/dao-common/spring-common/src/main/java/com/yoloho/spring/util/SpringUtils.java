package com.yoloho.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 从Spring ApplicationContext中获取Bean的工具方法
 * 
 * @author qianxg
 *
 */
public class SpringUtils implements ApplicationContextAware {
	
	private static ApplicationContext appContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		appContext = applicationContext;
	}
	
	/**
	 * 根据Bean类型获取Bean
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return appContext.getBean(clazz);
	}

	/**
	 * 根据Bean类型和指定的bean id获取Bean
	 * 
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz, String name) {
		return appContext.getBean(name, clazz);
	}
	
	/**
	 * 根据id获取Bean
	 * 
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		return appContext.getBean(name);
	}
}
