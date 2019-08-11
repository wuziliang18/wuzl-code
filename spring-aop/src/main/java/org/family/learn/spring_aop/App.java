package org.family.learn.spring_aop;

import java.lang.reflect.Method;
import java.util.Map;

import org.family.learn.spring_aop.service.UserServiceImpl;
import org.family.learn.spring_aop.service.interfaces.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext(
					new String[] { "app-context.xml" });
//			context = new ClassPathXmlApplicationContext(
//					new String[] { "app-context-config.xml" });
			
			context.start();
			
			System.out.println("加载完毕");
			UserService service = context
			.getBean(UserService.class);
//			Map<String, Object>  dto=context.getBeansWithAnnotation(Service.class);
//			System.out.println(dto);
//			Object service=dto.values().iterator().next();
//			System.out.println(service);
//			System.out.println(context.getBean(UserService.class));
			Method method=service.getClass().getMethod("secGet", int.class);
			System.out.println(method.invoke(service, 1));

//			service.update(null);
//			service.secGet(1);
//			service.get(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			context.close();
			context.destroy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}
}
