package org.family.learn.netty4.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import org.family.learn.netty4.handler.OutServerHandler;

public class TestHandlerLinkServer {
	public static void main(String[] args) {
		NioEventLoopGroup boss=new NioEventLoopGroup(1);
		NioEventLoopGroup group=new NioEventLoopGroup();
		ServerBootstrap server = new ServerBootstrap();
		try {
			// 指定通道类型为NioServerSocketChannel
			server.group(boss,group).channel(NioServerSocketChannel.class)
					.localAddress(20880)
					.childHandler(new ChannelInitializer<Channel>() {
						@Override
						protected void initChannel(Channel arg0)
								throws Exception {
							ChannelPipeline pipeline=arg0.pipeline();
							pipeline.addLast("decoder", new StringDecoder());
							pipeline.addLast("encoder", new StringEncoder());
							pipeline.addLast(new OutServerHandler("第一个"));
							pipeline.addLast(new OutServerHandler("第二个"));
						}
					});
			ChannelFuture future = server.bind().sync();
			System.out.println("started and listen on "
					+ future.channel().localAddress());
			System.out.println("绑定通道"+future.channel());
//			future.channel().close();
			future.channel().closeFuture().sync();//阻塞防止关闭
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("关闭");
				boss.shutdownGracefully().sync();
				group.shutdownGracefully().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
