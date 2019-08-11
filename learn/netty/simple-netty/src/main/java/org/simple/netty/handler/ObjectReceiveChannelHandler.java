package org.simple.netty.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.simple.netty.bean.Student;

public class ObjectReceiveChannelHandler extends SimpleChannelHandler{
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Student student=(Student) e.getMessage();
		System.out.println(student.getName());
	}
}
