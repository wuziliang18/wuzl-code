package org.family.learn.netty4.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;

public class OutServerHandler extends ChannelInboundHandlerAdapter{
	private String name;
	public OutServerHandler(String name) {
		this.name=name;
	}
//	@Override
//	protected void channelRead0(ChannelHandlerContext ctx, String msg)
//			throws Exception {
//		System.out.println(String.format("[%s]到了", name));
//		System.out.println("收到消息:" + msg);
//		super.channelRead(ctx, msg);
//	}
	//这种才可以流转
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println(String.format("[%s]到了", name));
		System.out.println("收到消息:" + msg);
//		System.out.println(msg instanceof ReferenceCounted);
//		ReferenceCountUtil.release(msg);  //没有意义的操作手动释放消息 不影响第二个的接收
		super.channelRead(ctx, msg);//注释后后边不到
	}
}
