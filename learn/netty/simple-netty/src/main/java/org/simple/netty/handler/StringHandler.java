package org.simple.netty.handler;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class StringHandler extends SimpleChannelHandler{
	/**
	 * 接收数据
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		ChannelBuffer chanelbuffer=  (ChannelBuffer) e.getMessage();
		System.out.println("#receive msg is:"+chanelbuffer.toString(Charset.defaultCharset()));
	}
	
}
