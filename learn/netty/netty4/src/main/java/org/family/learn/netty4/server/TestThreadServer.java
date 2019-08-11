package org.family.learn.netty4.server;

import org.family.learn.netty4.handler.ReceiveMsgServcerHandle;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class TestThreadServer {
	public static void main(String[] args) {
		NioEventLoopGroup group=new NioEventLoopGroup();
		ServerBootstrap server = new ServerBootstrap();
		try {
			// 指定通道类型为NioServerSocketChannel
			server.group(new NioEventLoopGroup(1),group).channel(NioServerSocketChannel.class)
					.localAddress(20880)
					.childHandler(new ChannelInitializer<Channel>() {
						@Override
						protected void initChannel(Channel arg0)
								throws Exception {
							arg0.pipeline().addLast("decoder", new StringDecoder());
							arg0.pipeline().addLast("encoder", new StringEncoder());
							arg0.pipeline().addLast(new ReceiveMsgServcerHandle());
						}
					});
			ChannelFuture future = server.bind().sync();
			System.out.println("started and listen on "
					+ future.channel().localAddress());
			System.out.println("绑定通道"+future.channel());
			Thread.sleep(5000l);
			System.out.println("输出map");
			System.out.println(ReceiveMsgServcerHandle.threadMap);
			System.out.println(ReceiveMsgServcerHandle.errorMap);
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
}
