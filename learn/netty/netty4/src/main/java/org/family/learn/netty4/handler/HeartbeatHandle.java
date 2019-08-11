package org.family.learn.netty4.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

public class HeartbeatHandle extends ChannelInboundHandlerAdapter {
	private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled
			.unreleasableBuffer(Unpooled.copiedBuffer("HEARTBEAT",
					CharsetUtil.UTF_8));
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if(msg instanceof ByteBuf){
			ByteBuf bb=(ByteBuf)msg;
			if("HEARTBEAT".equals(bb.toString(CharsetUtil.UTF_8))){
				System.out.println("收到一次心跳跳过");
				return;
			}
		}
		super.channelRead(ctx, msg);
	}
	
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if (evt instanceof IdleStateEvent) {//长时间空闲触发事件
			System.out.println(ctx.channel());
			System.out.println("发送心跳"+ctx.channel());
			ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate()).addListener(
					ChannelFutureListener.CLOSE_ON_FAILURE);
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}

}
