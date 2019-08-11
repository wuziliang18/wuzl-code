package com.wuzl.lean.spring3.commen;

import java.text.SimpleDateFormat;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.wuzl.lean.spring3.commen.bean.PersonBean;

public class Main {
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:app-context.xml");
		PersonBean boy=context.getBean("boy",PersonBean.class);
		System.out.println(sdf.format(boy.getBirthday()));
		outString(boy);
	}
	public static void outString(Object obj){
		System.out.println(JSONObject.toJSON(obj));
	}
}
