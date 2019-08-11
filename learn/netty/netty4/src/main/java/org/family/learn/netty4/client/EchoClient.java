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
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

import org.family.learn.netty4.handler.EchoClientHandler;

public class EchoClient {
	private int port;
	private String host;

	public EchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void start() {
		// EventLoopGroup可以理解为是一个线程池，这个线程池用来处理连接、接受数据、发送数据
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		Bootstrap client = new Bootstrap();
		try {
			client.group(eventLoopGroup).channel(NioSocketChannel.class)
					.remoteAddress(new InetSocketAddress(host, port))
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception {
							ch.pipeline().addLast(new EchoClientHandler());
						}

					});
			client.option(ChannelOption.SO_KEEPALIVE, true);// 通道选项 tcp的 保持连接检测对方主机是否崩溃
			client.attr(AttributeKey.valueOf("connectionName"), "pay");//设置通道属性
			ChannelFuture f = client.connect().sync();
			System.out.println("开始关闭");
			Channel chanel=f.channel();
			while(true){
				chanel.write("ASDFASDF");
			}
//			System.out.println(chanel);
////			f.channel().closeFuture().sync();
//			System.out.println("关闭成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			try {
//				eventLoopGroup.shutdownGracefully().sync();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}

	public static void main(String[] args) {
		new EchoClient("localhost", 20880).start();
	}
}
