package org.family.learn.netty4.handler;

import org.family.learn.netty4.bean.Request;

import com.alibaba.fastjson.JSON;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ObjectClientHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 1; i <= 10; i++) {
			ctx.write(buildRequest("信息"+i));
		}
		ctx.flush();
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("返回消息："+JSON.toJSON(msg));
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	private Request buildRequest(String message) {
		Request request = new Request();
		request.setFrom("client");
		request.setMessage(message);
		return request;
	}
}
