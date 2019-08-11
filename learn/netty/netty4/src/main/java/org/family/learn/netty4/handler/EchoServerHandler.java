package org.family.learn.netty4.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		System.out.println("有客户端连接");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("当前线程"+Thread.currentThread().getName());
		System.out.println("客户端是"+ctx.channel().attr(AttributeKey.valueOf("connectionName")));
		System.out.println("通讯的客户端通道："+ctx.channel());
		System.out.println("hanlder地址"+this);
		System.out.println("read from client:"+msg);
		ByteBuf m = (ByteBuf) msg;
		System.out.println(m.toString(CharsetUtil.UTF_8));
		ctx.write(msg);
		ctx.flush();
//		try {
//			
//			System.out.println("read from client:"+msg);
//			ctx.write(msg);
//			ctx.flush();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			m.release();
		/**
		 * //要手动释放资源 因为这里使用 的ChannelInboundHandlerAdapter 如果是用SimpleChannelInboundHandler就不用 因为bytebufe取完就自动删除了
		 * 不过这里有写操作 会自动释放资源不用手动关闭
		 */
//		}
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelReadComplete");
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);//会自动关闭bytebuffer？
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		System.out.println("error:"+cause.getMessage());
		cause.printStackTrace();
		ctx.close();
	}
	
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
		System.out.println("连接上的客户端通道："+ctx.channel());
	}
}
