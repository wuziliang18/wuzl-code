package dubbo.impl;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		context.start();
		try {
			System.out.println("���������ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.in.read(); // ��������˳�
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
