package org.family.learn.netty4.client;

import org.family.learn.netty4.handler.ObjectClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ObjectClient {
	public void connect(int port, String host) {
		EventLoopGroup group = new NioEventLoopGroup();

		try {
			Bootstrap boot = new Bootstrap();
			boot.group(group).channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception {
							ch.pipeline().addLast(new ObjectEncoder());
							ch.pipeline().addLast(
									new ObjectDecoder(1024 * 1024 * 20,
											ClassResolvers.cacheDisabled(this
													.getClass()
													.getClassLoader())));
							ch.pipeline().addLast(new ObjectClientHandler());
						}
					});

			// 发起异步连接操作
			ChannelFuture cf = boot.connect(host, port).sync();
			cf.channel().closeFuture().sync();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}

	}

	public static void main(String[] args) {
		new ObjectClient().connect(20880, "127.0.0.1");
	}
}
