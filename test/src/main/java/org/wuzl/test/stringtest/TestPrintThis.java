package org.wuzl.test.stringtest;

public class TestPrintThis {
	@Override
	public String toString() {
//		return "内存地址"+this;//报错 因为字符串+this会调用this的toString 死循环
		return "内存地址"+super.toString();
	}
	public static void main(String[] args) {
		System.out.println(new TestPrintThis());
	}
}
