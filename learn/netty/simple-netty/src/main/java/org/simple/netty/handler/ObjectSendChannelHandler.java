package org.simple.netty.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.simple.netty.bean.Student;

public class ObjectSendChannelHandler extends SimpleChannelHandler {
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("第一个接收handler,不想费事了直接下一个吧");
//		ctx.sendUpstream(e);//不加这个不会调用第二个 但此处加上似乎另外一个线程
		System.out.println("第一个后续继续走");
		Student student=new Student();
		student.setId(1);
		student.setName("王童鞋");
		ctx.getChannel().write(student);
//		ctx.sendUpstream(e);//加上才走第二个
	}
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		System.out.println("接收服务端返回的信息");
		Student student=(Student) e.getMessage();
		System.out.println(student.getName()+",生日:"+student.getBirthDay()+",说明："+student.getMemo());
	}
	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Object obj=e.getMessage();
		System.out.println(obj);
		System.out.println("功能似乎是拦截写");
		super.writeRequested(ctx, e);//必须有
	}
}
