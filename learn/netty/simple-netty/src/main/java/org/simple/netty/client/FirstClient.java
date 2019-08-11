package org.simple.netty.client;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.simple.netty.handler.FirstClientChanelHandler;

public class FirstClient {
	public static void main(String[] args) {
		//client服务启动器
		ClientBootstrap clientBootstrap=new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		// 设置一个处理服务端消息和各种消息事件的类(Handler)
		clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new FirstClientChanelHandler());
			}
		});
		//连接到服务端
		clientBootstrap.connect(new InetSocketAddress("127.0.0.1",8000));
		System.out.println("client is connect ok!");
	}
}
