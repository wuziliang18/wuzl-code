package org.family.learn.netty4.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import org.family.learn.netty4.handler.EchoClientHandler;
import org.family.learn.netty4.handler.HeartbeatHandle;

public class HeartbeatClient {
	private int port;
	private String host;

	public HeartbeatClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void start() {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		Bootstrap client = new Bootstrap();
		try {
			client.group(eventLoopGroup).channel(NioSocketChannel.class)
					.remoteAddress(new InetSocketAddress(host, port))
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception {
							ChannelPipeline pipeLine = ch.pipeline();
							pipeLine.addLast(new IdleStateHandler(0, 0, 60,
									TimeUnit.SECONDS));
							pipeLine.addLast(new HeartbeatHandle());
							pipeLine.addLast(new EchoClientHandler());
							pipeLine.addLast(new SimpleChannelInboundHandler<ByteBuf>() {

								@Override
								protected void channelRead0(
										ChannelHandlerContext ctx, ByteBuf msg)
										throws Exception {
									System.out.println("client receive:"+ msg.readBytes(msg.readableBytes()).toString(CharsetUtil.UTF_8));  
								}
								
							});
						}

					});
			ChannelFuture f = client.connect().sync();
			System.in.read();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				eventLoopGroup.shutdownGracefully().sync();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new HeartbeatClient("localhost", 65535).start();
	}
}
