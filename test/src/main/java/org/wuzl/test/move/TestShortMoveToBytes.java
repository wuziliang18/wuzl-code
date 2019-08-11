package org.wuzl.test.move;

public class TestShortMoveToBytes {
	public static void main(String[] args) {
		short s=-1;
		byte first=(byte)s;
		byte sec=(byte)(s>>>8);
		System.out.println(first);
		System.out.println(sec);
		System.out.println((sec<<8)+(first& 0xFFL));//不加& 0xFFL 会对负数有影响 所以要加上
	}
}
