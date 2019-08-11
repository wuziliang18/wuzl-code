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

public class ManySendMsgClient {
	public void startClient(int count) {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		Bootstrap client = new Bootstrap();
		try {
			client.group(eventLoopGroup).channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception {
							ch.pipeline().addLast("decoder",
									new StringDecoder());
							ch.pipeline().addLast("encoder",
									new StringEncoder());
							ch.pipeline().addLast(new SendMsgClientHandle());
						}

					});
			client.option(ChannelOption.SO_KEEPALIVE, true);// 通道选项 tcp的
															// 保持连接检测对方主机是否崩溃
			for(int i=0;i<count;i++){
				final ChannelFuture futrue=client.connect("localhost",20880);
				final String name="客户端"+i;
				new Thread(){
					public void run() {
						Channel chanel=futrue.channel();
						for(int i=0;i<10;i++){
							chanel.write(name+"发送"+i);
							chanel.flush();
						}
					};
				}.start();
			}
//			eventLoopGroup.shutdownGracefully();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void main(String[] args) {
//		for (int i = 0; i < 100; i++) {
//			new SendMsgClient().startThread("client" + i);
//		}
		new ManySendMsgClient().startClient(10);
	}
}
