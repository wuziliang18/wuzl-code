package org.simple.netty.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.simple.netty.handler.ObjectReceiveChannelHandler;
import org.simple.netty.handler.TestBufferHandler;

public class ObjectServer {
	public static void main(String[] args) {
		ServerBootstrap bootstrap=new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		// 设置一个处理客户端消息和各种消息事件的类(Handler)
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline(new ObjectDecoder()  ,new ObjectReceiveChannelHandler());//handler是要有顺序的
				return pipeline;
			}
		});
		//开放端口
		bootstrap.bind(new InetSocketAddress(8000));
		System.out.println("server is ok!");
	}
}
