package org.family.learn.netty4.bytebuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;
import java.util.Random;


public class TestByteBuffer {
	public static void main(String[] args) throws UnsupportedEncodingException {
		Random random=new Random();
		ByteBuf byteBuffer=Unpooled.buffer(16);
		while(byteBuffer.writableBytes()>=0){
			int ran=random.nextInt(100);
			byteBuffer.writeInt(ran);
		}
		while(byteBuffer.readableBytes()>=4){
			System.out.println(byteBuffer.readInt());
		}
	}
}
