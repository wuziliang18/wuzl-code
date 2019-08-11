package org.family.learn.netty4.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

import org.family.learn.netty4.handler.HeartbeatHandle;

public class HeartbeatServer {
	public static void main(String[] args) {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap server = new ServerBootstrap()
				.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class).localAddress(65535)
				.childHandler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel ch) throws Exception {
						ChannelPipeline pipeLine = ch.pipeline();
						pipeLine.addLast(new IdleStateHandler(0, 0, 60,
								TimeUnit.SECONDS));//如果长时间空闲触发事件
						pipeLine.addLast(new HeartbeatHandle());
						pipeLine.addLast(new SimpleChannelInboundHandler<ByteBuf>() {

							@Override
							protected void channelRead0(
									ChannelHandlerContext ctx, ByteBuf msg)
									throws Exception {
								System.out.println("server receive:"+ msg.readBytes(msg.readableBytes()).toString(CharsetUtil.UTF_8));  
							}
							
						});
					}

				});
		server.bind();
		System.out.println("启动成功");
	}
}
