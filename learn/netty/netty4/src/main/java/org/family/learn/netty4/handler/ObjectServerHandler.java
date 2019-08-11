package org.family.learn.netty4.handler;

import org.family.learn.netty4.bean.Request;
import org.family.learn.netty4.bean.Response;

import com.alibaba.fastjson.JSON;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ObjectServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if (msg instanceof Request) {
			System.out.println("接到信息" + JSON.toJSON(msg));
			ctx.writeAndFlush(getResponse((Request) msg));
		}
		super.channelRead(ctx, msg);
	}

	private Response getResponse(Request request) {
		Response response = new Response();
		response.setId(request.getId());
		response.setReturnMsg(JSON.toJSONString(request));
		return response;
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
}
