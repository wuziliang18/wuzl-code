package org.family.learn.netty4.handler;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
/*
 * 固定长度的解码器
 */
public class FixedLengthDecoder extends ByteToMessageDecoder{
	private final int frameLenth;
	
	public FixedLengthDecoder(int frameLenth) {
		this.frameLenth=frameLenth;
	}
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		while((in.readableBytes())>=frameLenth){
			ByteBuf buf=in.readBytes(frameLenth);
			out.add(buf);
		}
	}
	public static void main(String[] args) {
		ByteBuf buf=Unpooled.buffer();
		for(int i=0;i<9;i++){
			buf.writeByte(i);
		}
		ByteBuf input = buf.duplicate();  
		EmbeddedChannel channel=new EmbeddedChannel(new FixedLengthDecoder(3));
		channel.writeInbound(input);
		System.out.println(buf.readBytes(3).equals(channel.readInbound()));;
		System.out.println(buf.readBytes(3).equals(channel.readInbound()));;
		System.out.println(buf.readBytes(3).equals(channel.readInbound()));;
	}
}
