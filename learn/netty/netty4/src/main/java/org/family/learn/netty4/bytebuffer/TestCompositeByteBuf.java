package org.family.learn.netty4.bytebuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

/**
 * CompositeByteBuf 复合bytebuffer 是bytebuffer的视图list 也是bytebuffer的子类 
 * @author wuzl
 *
 */
public class TestCompositeByteBuf {
	public static void main(String[] args) {
//		CompositeByteBuf composite=Unpooled.compositeBuffer();
//		ByteBuf byteBuffer=Unpooled.buffer(8);
//		ByteBuf direct=Unpooled.directBuffer(16);
//		composite.addComponents(byteBuffer,direct);
//		byte[] bytes="0123456789".getBytes();
//		System.out.println(bytes.length);
//		composite.writeBytes(bytes);
//		System.out.println(composite.capacity());
//		System.out.println(composite.writableBytes());
//		System.out.println(new String(byteBuffer.array()));
//		byte[] directByteArray=new byte[direct.readableBytes()];
//		direct.readBytes(directByteArray);//直接缓存区只能这么读取
//		System.out.println(new String(directByteArray));
//		
//		byte[] resultByteArray=new byte[composite.readableBytes()];
//		composite.readBytes(resultByteArray);
//		System.out.println(new String(resultByteArray));
//		System.out.println(composite.hasArray());//永远为fasle 因为可能包含有直接的 不明白怎么会返回true呢
		
		
		
		CompositeByteBuf composite=Unpooled.compositeBuffer();
		ByteBuf byteBuffer=Unpooled.buffer(8);
		byteBuffer.writeBytes("123456789".getBytes());//尼玛  传递的8是初始容量   最大容量默认是int的最大值
		ByteBuf direct=Unpooled.directBuffer(16);
		direct.writeBytes("910".getBytes());
		composite.addComponents(byteBuffer,direct);
//		byte[] bytes="0123456789".getBytes();
//		System.out.println(bytes.length);
//		composite.writeBytes(bytes);
		System.out.println(composite.capacity());
		System.out.println(composite.readableBytes());
		System.out.println(new String(byteBuffer.array()));
		byte[] directByteArray=new byte[direct.readableBytes()];
		direct.readBytes(directByteArray);//直接缓存区只能这么读取
		System.out.println(new String(directByteArray));
		
		byte[] resultByteArray=new byte[composite.readableBytes()];
		composite.readBytes(resultByteArray);
		System.out.println(new String(resultByteArray));//不会读出数据 只会读出自己写入的
		System.out.println(composite.hasArray());//永远为fasle 因为可能包含有直接的 
	}
}
