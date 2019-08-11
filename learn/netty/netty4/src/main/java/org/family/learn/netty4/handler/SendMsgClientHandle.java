package org.family.learn.netty4.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SendMsgClientHandle extends SimpleChannelInboundHandler<String>{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("连接上了");
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		
	}
}
