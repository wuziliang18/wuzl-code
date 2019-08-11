package org.simple.netty.handler;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
/**
 * 识别的不是你每一次发送来的消息，不是分包的。
 * 而是，只认识一个整体的流，即使分三次分别发送三段话：ABC、DEF、GHI。在传递的过程中，
 * 他就是一个具有整体长度的流。在读流的过程中，如果我一次读取的长度选择的不是三个，
 * 我可以收到类似AB、CDEFG、H、I这样的信息
 * @author wuzl
 *
 */
public class TestBufferHandler extends SimpleChannelHandler{
	/**
	 * 接收数据
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		ChannelBuffer chanelbuffer=  (ChannelBuffer) e.getMessage();
		while(chanelbuffer.readableBytes()>5){//按照约定分段读
			 ChannelBuffer tempBuffer = chanelbuffer.readBytes(5);
			 System.out.println("接收的消息是:"+tempBuffer.toString(Charset.defaultCharset()));
		}
		//读出剩余的
		System.out.println("接收的消息是:"+chanelbuffer.toString(Charset.defaultCharset()));
	}
	
}
