package org.family.learn.netty4.server;

import org.family.learn.netty4.handler.ObjectServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ObjectServer {
	public void bind(int port) {
		NioEventLoopGroup boss = new NioEventLoopGroup(1);
		NioEventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap server = new ServerBootstrap();
			server.group(boss, group).channel(NioServerSocketChannel.class)
					.localAddress(port)
					.childHandler(new ChannelInitializer<Channel>() {
						@Override
						protected void initChannel(Channel arg0)
								throws Exception {
							ChannelPipeline pipeline = arg0.pipeline();
							pipeline.addLast(new ObjectEncoder());
							pipeline.addLast(new ObjectDecoder(
									1024 * 1024 * 20, ClassResolvers
											.weakCachingConcurrentResolver(this
													.getClass()
													.getClassLoader())));
							pipeline.addLast(new ObjectServerHandler());
						}
					});
			ChannelFuture future = server.bind().sync();
			System.out.println(String.format("启动完毕端口【%s】", port));
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
			group.shutdownGracefully();
		}

	}

	public static void main(String[] args) {
		new ObjectServer().bind(20880);
	}
}
