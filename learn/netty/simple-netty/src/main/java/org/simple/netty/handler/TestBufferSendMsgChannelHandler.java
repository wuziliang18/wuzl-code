package org.simple.netty.handler;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class TestBufferSendMsgChannelHandler extends SimpleChannelHandler {
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		// 分段发送信息
		sendMessageByFrame(e);
	}
	/**
	 * 即使是分段这么发 但服务端 取的数目不同结果也不同
	 * @param e
	 */
	private void sendMessageByFrame(ChannelStateEvent e) {
		String msgOne = "Hello, ";
		String msgTwo = "I'm ";
		String msgThree = "client.";
		e.getChannel().write(tranStr2Buffer(msgOne));
		e.getChannel().write(tranStr2Buffer(msgTwo));
		e.getChannel().write(tranStr2Buffer(msgThree));
	}

	private ChannelBuffer tranStr2Buffer(String str) {
		ChannelBuffer buffer = ChannelBuffers.buffer(str.length());
		buffer.writeBytes(str.getBytes());
		return buffer;
	}
}
