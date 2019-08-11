package org.family.learn.spring_aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect 
public class ClassCacheAop {
	@Before("execution(* org.family.learn.spring_aop.service.*.*(..))")  
    public void authorith(){  
        System.out.println("模拟进行权限检查。");  
    }  
}
