package org.simple.netty.handler;


import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.DownstreamMessageEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.simple.netty.bean.Student;

public class ObjectReceiveAndReturnChannelHandler extends SimpleChannelHandler{
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		System.out.println(ctx.getChannel());
		Student student=(Student) e.getMessage();
		System.out.println("收到信息"+student.getId());
		//完善student信息后返回
		student.setBirthDay("19831128");
		student.setMemo("很好很和谐");
		Channel channel=e.getChannel();
		//使用ChannelFuture和DownstreamMessageEvent返回数据
//		ChannelFuture chnelFuture=Channels.future(channel);
//		ChannelEvent channelEvent=new DownstreamMessageEvent(channel, chnelFuture, student, e.getRemoteAddress());
//		ctx.sendDownstream(channelEvent);
		channel.write(student);//直接返回数据也可以 但不太明白为啥非得用上边的方式
	}
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
	}
}
