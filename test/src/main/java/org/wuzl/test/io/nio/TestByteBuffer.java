package org.wuzl.test.io.nio;

import java.nio.ByteBuffer;

public class TestByteBuffer {
	public static void main(String[] args) {
		ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
		System.out.println(byteBuffer.capacity());
		System.out.println(byteBuffer.limit());
		System.out.println(byteBuffer.position());
		byteBuffer.put("text".getBytes());//放入4个字符
		System.out.println(byteBuffer.capacity());
		System.out.println(byteBuffer.limit());
		System.out.println(byteBuffer.position());
		byteBuffer.flip();//反转
		System.out.println(byteBuffer.capacity());
		System.out.println(byteBuffer.limit());
		System.out.println(byteBuffer.position());
		byteBuffer.flip();//反转
		System.out.println(byteBuffer.capacity());
		System.out.println(byteBuffer.limit());
		System.out.println(byteBuffer.position());
	}
}
