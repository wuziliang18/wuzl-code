package org.wuzl.test.unicode;

import java.nio.charset.Charset;

public class TestCharset {
	public static void main(String[] args) {
//		System.out.println(Charset.availableCharsets());//所有支持字符集 速度可能会慢 
		Charset charset=Charset.defaultCharset();
		System.out.println(charset.name());
		System.out.println(charset.newEncoder().averageBytesPerChar());
		System.out.println(charset.newEncoder().maxBytesPerChar());
	}
}
