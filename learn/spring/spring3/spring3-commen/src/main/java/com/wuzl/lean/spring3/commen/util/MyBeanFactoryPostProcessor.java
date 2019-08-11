package com.wuzl.lean.spring3.commen.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.TypedStringValue;

/**
 * 可以在spring的bean创建之前，修改bean的定义属性。也就是说，
 * Spring允许BeanFactoryPostProcessor在容器实例化任何其它bean之前读取配置元数据
 * ，并可以根据需要进行修改，例如可以把bean的scope从singleton改为prototype
 * ，也可以把property的值给修改掉。可以同时配置多个BeanFactoryPostProcessor
 * ，并通过设置'order'属性来控制各个BeanFactoryPostProcessor的执行次序。
 * 注意：BeanFactoryPostProcessor是在spring容器加载了bean的定义文件之后
 * ，在bean实例化之前执行的。接口方法的入参是ConfigurrableListableBeanFactory
 * ，使用该参数，可以获取到相关bean的定义信息
 * 跟BeanPostProcessor比可以改动的更多
 * @author wuzl
 * 
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("boy");
		MutablePropertyValues values = beanDefinition.getPropertyValues();
		if (values.contains("birthday")) {
			PropertyValue value = values.getPropertyValue("birthday");
			TypedStringValue birthday = (TypedStringValue)value.getValue();
			try {
				value.setConvertedValue(sdf.parseObject(birthday.getValue()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

}
