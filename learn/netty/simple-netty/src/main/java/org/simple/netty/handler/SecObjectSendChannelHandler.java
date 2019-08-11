package org.simple.netty.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.simple.netty.bean.Student;

public class SecObjectSendChannelHandler extends SimpleChannelHandler {
	/**
	 * 只要第一个有方法就不一定执行第二个（当前的）除非制定ctx.sendUpstream(e);
	 */ 
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("第二个接收handler");
	}
}
