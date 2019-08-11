package org.dubbo.consumer;

import org.dubbo.api.interfaces.IPersonService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;

public class TestException {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "app-dubbo-consumer.xml" });
		context.start();
		IPersonService personService = context.getBean(IPersonService.class);
		// 正常输入参数
		outString(personService.getByNick("某某"));
		// 输入null
		outString(personService.getByNick(null));
	}

	public static void outString(Object obj) {
		System.out.println(JSONObject.toJSONString(obj));
	}
}
