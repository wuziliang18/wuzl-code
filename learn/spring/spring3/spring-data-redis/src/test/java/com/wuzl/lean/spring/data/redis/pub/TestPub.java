package com.wuzl.lean.spring.data.redis.pub;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wuzl.lean.spring.data.redis.bean.ChatRecordBean;
import com.wuzl.lean.spring.data.redis.pulish.interfaces.IPublishMessage;

public class TestPub {
	static AtomicInteger i=new AtomicInteger();
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:app-redis-pub.xml");
		IPublishMessage publish = context.getBean(IPublishMessage.class);
		Map<String,Object> msg=new HashMap<String,Object>();
		msg.put("type", "3");
		msg.put("status", "0");
		msg.put("id", "396");
		publish.pulishMesage("DIREC_BROADCAST_RECORD_SUB_1", msg);//直播状态变更
//		for (int i = 0; i < 300; i++) {
//			publish.pulishMesage("test",getBean( "信息" + i));
//		}
//		Random random=new Random();
//		for(int i=0;i<100000;i++){
//			String chanel="test"+(i%8);
//			try {
//				Thread.sleep(random.nextInt(10));
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			publish.pulishMesage(chanel, getBean(chanel+">>>>222信息" + i));
//		}
		System.out.println("发送完毕");
	}
	public static ChatRecordBean getBean(String message){
		ChatRecordBean bean=new ChatRecordBean();
		bean.setId(i.getAndIncrement());
		bean.setMessage(message);
		return bean;
	}
}
