package org.wuzl.test.reflect;

import java.util.HashMap;
import java.util.Map;

public class TestArray {
	public static void main(String[] args) {
		byte[] bytes=new byte[5];//[B
		System.out.println(bytes.getClass());
		Map[][] maps=new HashMap[5][3];// [[Ljava.util.HashMap;
		System.out.println(maps.getClass());
	}
}
