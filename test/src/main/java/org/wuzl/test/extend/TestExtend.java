package org.wuzl.test.extend;

public class TestExtend {
	public static void main(String[] args) {
		new Son1("d");
	}
}
class Parent1{
	public Parent1() {
		System.out.println(">>");
	}
	public Parent1(String name) {
		System.out.println(name);
	}
}
class Son1 extends Parent1{
	Parent1 p;
	public Son1() {
		System.out.println("2");
		p=new Parent1();
	}
	public Son1(String name) {
		System.out.println(name);
		p=new Parent1(name);
	}
}