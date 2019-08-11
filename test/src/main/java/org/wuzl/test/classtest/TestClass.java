package org.wuzl.test.classtest;

public class TestClass {
	public static void main(String[] args) {
		System.out.println(TestClass.class.getName());
		System.out.println(TestClass.class.getCanonicalName());
		System.out.println(String.class.getName().equals("java.lang.String"));
		
	}
}
