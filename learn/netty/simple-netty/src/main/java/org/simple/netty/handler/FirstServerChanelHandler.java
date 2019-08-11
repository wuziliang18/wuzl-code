package org.simple.netty.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class FirstServerChanelHandler extends SimpleChannelHandler {
	//非单例
	public FirstServerChanelHandler() {
		System.out.println(">>>>>>>");
	}
	/**
	 * 当有连接的时候触发
	 */
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("have a clien connect");
	}
}
