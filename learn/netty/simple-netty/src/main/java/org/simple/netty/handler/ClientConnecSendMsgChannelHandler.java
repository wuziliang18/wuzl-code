package org.simple.netty.handler;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ClientConnecSendMsgChannelHandler extends SimpleChannelHandler {
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		String msg="HELLO SERVER";
//		String msg="连接上服务端了，打个招呼";
		ChannelBuffer buffer=ChannelBuffers.buffer(msg.getBytes().length);//如果是msg.length 汉字会抛出数组异常
		buffer.writeBytes(msg.getBytes(Charset.defaultCharset()));
		e.getChannel().write(buffer);
	}
}
