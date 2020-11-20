package org.family.learn.netty4.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends  SimpleChannelInboundHandler<ByteBuf> {
    
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg)
			throws Exception {
		System.out.println("通讯通道："+ctx.channel());
		System.out.println("client receive:"+ msg.readBytes(msg.readableBytes()).toString(CharsetUtil.UTF_8));  
		System.out.println("客户端是"+ctx.channel().attr(AttributeKey.valueOf("connectionName")));
//		msg.release();//会 报错 因为SimpleChannelInboundHandler主动释放了 
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("连接上了服务端");
		System.out.println("连接上的服务端通道："+ctx.channel());
		System.out.println(ctx.channel());
		ctx.write(Unpooled.copiedBuffer("Netty rocks !",CharsetUtil.UTF_8));
		ctx.flush();
	}
	@Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        cause.printStackTrace();  
        ctx.close();  
    }
}
