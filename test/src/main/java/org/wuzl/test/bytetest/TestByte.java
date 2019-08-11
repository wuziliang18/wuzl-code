package org.wuzl.test.bytetest;

public class TestByte {
	public static void main(String[] args) {
		byte     FLAG_REQUEST       = (byte) 0x80;
		Integer flag_int=(int) FLAG_REQUEST;
		System.out.println(Integer.toBinaryString(flag_int));
		byte end=(byte) (FLAG_REQUEST|8);
		System.out.println(Integer.toBinaryString(end));
		System.out.println(end);
		
		System.out.println((int)'a');
		byte b=-3;
		System.out.println(Integer.toBinaryString(b));
	}
}
