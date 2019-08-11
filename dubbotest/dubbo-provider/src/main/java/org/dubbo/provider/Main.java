package org.dubbo.provider;

import java.io.IOException;

import org.dubbo.api.interfaces.IPersonService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "app-dubbo-provider.xml" });
		context.start();
		try {
			System.out.println("开始阻塞");
			// IPersonService personService =
			// context.getBean("remotePersonService",IPersonService.class);
			// System.out.println(personService.getPerson(1).getName());
			System.in.read(); // 按任意键退出
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
