package com.wuzl.lean.spring.data.redis.sub;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.wuzl.lean.spring.data.redis.SubManage;
import com.wuzl.lean.spring.data.redis.chat.ChatRecordMessageOperator;

public class TestSub {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"app-redis-sub.xml");
		SubManage manage = context.getBean(SubManage.class);
		manage.addSub("test",new ChatRecordMessageOperator());
		try {
			Thread.sleep(3000l);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		Random random=new Random();
//		for(int i=0;i<8;i++){
//			try {
//				Thread.sleep(random.nextInt(10)*100l);
//				System.out.println(String.format("开启第%d个", i));
//				manage.addSub("test"+i,new ChatRecordMessageOperator());
//			} catch (InterruptedException e) {
//			}
//		}
//		try {
//			Thread.sleep(1000l);
//		} catch (InterruptedException e) {
//			
//			e.printStackTrace();
//		}
//		for(int i=0;i<8;i++){
//			try {
//				Thread.sleep(random.nextInt(10)*100l);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			String channel="test"+i;
//			if(!channel.equals("test"+6)){
//				manage.removeSub(channel);
//				System.out.println("关闭"+channel);
//			}
//			
//		}
	}
}
