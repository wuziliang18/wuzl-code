package org.family.learn.schema;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {  
        ApplicationContext context = new ClassPathXmlApplicationContext(  
                "testSchema.xml");  
        SimpleDateFormat format = (SimpleDateFormat) context  
                .getBean("defaultDateFormat");  
        System.out.println(format.format(new Date()));  
  
    }  
}
