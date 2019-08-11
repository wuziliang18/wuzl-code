package org.family.learn.netty4.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.family.learn.netty4.handler.EchoServerHandler;
/*×
 * 测试使用贡献handle
 */
public class TestSharableServcer {
	private final static int port = 65535;

	public void startServer() {
		// 异步 指定NioEventLoopGroup来接受和处理新连接
		EventLoopGroup group = new NioEventLoopGroup();
		ServerBootstrap server = new ServerBootstrap();
		try {
			final EchoServerHandler handle=new EchoServerHandler();
			// 指定通道类型为NioServerSocketChannel
			server.group(group).channel(NioServerSocketChannel.class)
					.localAddress(port)
					.childHandler(new ChannelInitializer<Channel>() {
						@Override
						protected void initChannel(Channel arg0)
								throws Exception {
							arg0.pipeline().addLast(handle);
						}
					});
			// Binds server, waits for server to close, and releases resources
			// sync阻塞之道bind完毕
			ChannelFuture future = server.bind().sync();
			System.out.println("started and listen on "
					+ future.channel().localAddress());
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

	public static void main(String[] args) {
		new TestSharableServcer().startServer();
	}
}
