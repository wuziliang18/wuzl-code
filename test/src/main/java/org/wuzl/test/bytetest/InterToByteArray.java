package org.wuzl.test.bytetest;

public class InterToByteArray {
	public static void main(String[] args) {
		int from=95000;
		System.out.println(Integer.toBinaryString(from));
		byte first=(byte)from;
		System.out.println(Integer.toBinaryString(first));
		from=from>>>8;
		System.out.println(Integer.toBinaryString(from));
		byte sec=(byte)from;
		System.out.println(Integer.toBinaryString(sec));
		System.out.println(Integer.toBinaryString(32));
		System.out.println(0x40&0x80);
		System.out.println("?>");
		int i=1<<7;
		System.out.println(i);
		byte[] bytes=Integer.toBinaryString(i).getBytes();
		System.out.println(Integer.parseInt(new String(bytes), 2));
	}
}	
