package org.family.learn.netty4.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.family.learn.netty4.handler.EchoServerHandler;

public class EchoServer {
	private final static int port = 65535;

	public void startServer() {
		// 异步 指定NioEventLoopGroup来接受和处理新连接
		EventLoopGroup group = new NioEventLoopGroup();
		ServerBootstrap server = new ServerBootstrap();
		try {
			// 指定通道类型为NioServerSocketChannel
			server.group(new NioEventLoopGroup(1),group).channel(NioServerSocketChannel.class)
					.localAddress(port)
					.childHandler(new ChannelInitializer<Channel>() {//服务端用childHandler
						@Override
						protected void initChannel(Channel arg0)
								throws Exception {
							arg0.pipeline().addLast(new EchoServerHandler());
						}
					});
//			server.childOption(ChannelOption.SO_KEEPALIVE, true);// 通道选项
			// Binds server, waits for server to close, and releases resources
			// sync阻塞之道bind完毕
			ChannelFuture future = server.bind().sync();
			System.out.println("started and listen on "
					+ future.channel().localAddress());
			System.out.println("绑定通道"+future.channel());
			future.channel().closeFuture().sync();//阻塞防止关闭
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				group.shutdownGracefully().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new EchoServer().startServer();
	}
}
