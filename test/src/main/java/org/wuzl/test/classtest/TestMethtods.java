package org.wuzl.test.classtest;

import java.lang.reflect.Method;

public class TestMethtods {
	public static void staticMethod(){
		
	}
	public void simpleMethod(){
		
	}
	private void privateMethod(){
		
	}
	protected void protectedMethod() {
		
	}
	void noMethod(){
		
	}
	public static void main(String[] args) {
//		Method[] methods=TestMethtods.class.getMethods();//打印所有的公共方法包括继承的 不打印似有和包作用域方法 以及保护方法
		Method[] methods=TestMethtods.class.getDeclaredMethods();//包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。
		for(Method method:methods){
			System.out.println(method);
		}
	}
}
