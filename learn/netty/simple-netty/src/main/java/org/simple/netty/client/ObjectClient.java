package org.simple.netty.client;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;
import org.simple.netty.bean.Student;
import org.simple.netty.handler.ObjectSendChannelHandler;

public class ObjectClient {
	public static void main(String[] args) throws InterruptedException {
		//client服务启动器
		ClientBootstrap clientBootstrap=new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		// 设置一个处理服务端消息和各种消息事件的类(Handler)
		clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new ObjectEncoder(),new ObjectSendChannelHandler());//handler是要有顺序的
			}
		});
		//连接到服务端
		ChannelFuture chanelFuture=clientBootstrap.connect(new InetSocketAddress("127.0.0.1",8000));
		System.out.println(chanelFuture.getChannel());
		System.out.println("client is connect ok!");
		Student student=new Student();
		student.setId(1);
		student.setName("新童鞋");
		ChannelFuture writeFuture=chanelFuture.getChannel().write(student);
		System.out.println(writeFuture.getChannel());//就是连接的那个chanel
		Thread.sleep(1000l);
		chanelFuture.getChannel().close();//因为是异步的 会报错 因为别的方法没有执行完毕
	}
}
