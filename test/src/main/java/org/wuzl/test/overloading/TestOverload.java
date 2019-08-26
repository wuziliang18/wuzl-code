package org.wuzl.test.overloading;

public class TestOverload {
	public void test(String s) {
		System.out.println("public void test(String s)");
	}
	public void test(String ...s) {
		System.out.println("public void test(String ...s)");
	}
	public void test(String s1,String s2) {
		System.out.println("public void test(String  s1 s2)");
	}
	public static void main(String[] args) {
		TestOverload test=new TestOverload();
		test.test("1");
		test.test("1","2");
	}
}
