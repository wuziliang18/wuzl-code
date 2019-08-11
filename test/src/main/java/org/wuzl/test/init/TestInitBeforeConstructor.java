package org.wuzl.test.init;

public class TestInitBeforeConstructor {
	{
		//在构造函数之前调用
		System.out.println("init");
	}
	public TestInitBeforeConstructor() {
		System.out.println("初始化构造函数");
	}
	public static void main(String[] args) {
		new TestInitBeforeConstructor();
		char c=65;
		System.out.println(c);
	}
	
}
