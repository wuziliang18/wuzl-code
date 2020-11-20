package org.family.learn.netty4.server;

import org.family.learn.netty4.handler.EchoV1ServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoV1Server {
    private final static int port = 65535;

    public void startServer() {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();
        final EchoV1ServerHandler echoV1ServerHandler = new EchoV1ServerHandler();
        try {
            server.group(new NioEventLoopGroup(1), group).channel(NioServerSocketChannel.class).localAddress(port)
                    .childHandler(new ChannelInitializer<Channel>() {// 服务端用childHandler
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(echoV1ServerHandler);// @Sharable注解支持共享
                        }
                    });
            // sync阻塞之道bind完毕
            ChannelFuture future = server.bind().sync();
            System.out.println("started and listen on " + future.channel().localAddress());
            System.out.println("绑定通道" + future.channel());
            future.channel().closeFuture().sync();// 阻塞防止关闭
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
        new EchoV1Server().startServer();
    }
}
