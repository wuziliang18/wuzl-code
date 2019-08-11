package org.family.learn.netty4.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

import org.family.learn.netty4.handler.SendMsgClientHandle;
import org.family.learn.netty4.handler.SimpleSendMsgClientHandle;

public class TestHandlerLinkClient {
	public void startClient(String clientName) {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		Bootstrap client = new Bootstrap();
		try {
			client.group(eventLoopGroup).channel(NioSocketChannel.class)
					.remoteAddress(new InetSocketAddress("localhost", 20880))
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception {
							ch.pipeline().addLast("decoder",
									new StringDecoder());
							ch.pipeline().addLast("encoder",
									new StringEncoder());
							ch.pipeline().addLast(new SimpleSendMsgClientHandle("第一个"));
							ch.pipeline().addLast(new SimpleSendMsgClientHandle("第二个"));
						}

					});
			client.option(ChannelOption.SO_KEEPALIVE, true);// 通道选项 tcp的
															// 保持连接检测对方主机是否崩溃
			ChannelFuture f = client.connect().sync();
			Channel chanel = f.channel();
			for (int i = 0; i < 10; i++) {
				chanel.write("#" + clientName + "$发送信息" + i);
				chanel.flush();
			}
			System.out.println("开始关闭");
			chanel.close();
			eventLoopGroup.shutdownGracefully();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public void startThread(final String name) {

		new Thread() {
			public void run() {
				startClient(name);
			};
		}.start();
	}

	public static void main(String[] args) {
		new TestHandlerLinkClient().startThread("client");
	}
}
