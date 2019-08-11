package org.dubbo.consumer;

import java.util.List;

import org.dubbo.api.bean.Person;
import org.dubbo.api.exception.UicException;
import org.dubbo.api.interfaces.IPerson2Service;
import org.dubbo.api.interfaces.IPerson3Service;
import org.dubbo.api.interfaces.IPersonService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 * 
 */
public class TestMain {
	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "app-dubbo-consumer.xml" });
		context.start();
		IPersonService personService = context.getBean(IPersonService.class);
		List<Person> rows=personService.getPeron();
		for(Person person:rows){
			System.out.println(person.getId()+"::"+person.getName());
		}
		try {
//			long time1 = System.currentTimeMillis();
//			person2Service.getPerson(1);
//			System.out.println(System.currentTimeMillis() - time1);
//			time1 = System.currentTimeMillis();
//			person2Service.getPerson(1);
//			System.out.println(System.currentTimeMillis() - time1);
//			time1 = System.currentTimeMillis();
//			person2Service.getPerson(1);
//			System.out.println(System.currentTimeMillis() - time1);
//			time1 = System.currentTimeMillis();
//			person2Service.getPerson(1);
//			System.out.println(System.currentTimeMillis() - time1);
//			person2Service.getPerson(-1);
//			Thread.sleep(1000000l);
		} catch (UicException e) {
			System.out.println(e.getMsg());
			System.out.println("#########"+e.getCode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
