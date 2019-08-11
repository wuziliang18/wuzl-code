package org.wuzl.test.io.nio;

import java.nio.ByteBuffer;

public class TestBufferClear {
	public static void main(String[] args) {
		ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
		byteBuffer.put("放到啊".getBytes());//放入4个字符
		byteBuffer.flip();
		System.out.println(byteBuffer.get());//116
		byteBuffer.clear();// 这玩意是不靠谱的
		System.out.println(byteBuffer.position());
		System.out.println(byteBuffer.get());//依然可以获取到116
		System.out.println(new String(byteBuffer.array()));//返回的是完整的
	}
}
