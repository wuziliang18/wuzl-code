package org.family.learn.netty4.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;

public class SimpleSendMsgClientHandle extends ChannelOutboundHandlerAdapter{
	private String name;
	public SimpleSendMsgClientHandle(String name) {
		this.name=name;
	}
		
//	@Override
//	public void write(ChannelHandlerContext ctx, Object msg,
//			ChannelPromise promise) throws Exception {
////		System.out.println(msg);
//		System.out.println(String.format("[%d]进入", name));
//		ReferenceCountUtil.release(msg); 
//		promise.setSuccess(); 
//	}
}
