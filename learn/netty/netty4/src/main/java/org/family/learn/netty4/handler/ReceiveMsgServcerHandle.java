package org.family.learn.netty4.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ReceiveMsgServcerHandle extends
		SimpleChannelInboundHandler<String> {
	public static Map<String, String> threadMap = new ConcurrentHashMap<String, String>();
	public static Map<String, String> errorMap = new ConcurrentHashMap<String, String>();
	private static Pattern pattern = Pattern.compile("#[^#]+\\$");

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		// System.out.println("当前线程" + Thread.currentThread().getName());
		String threadName = Thread.currentThread().getName();
		 System.out.println("收到消息:" + msg);
		try {
			Matcher matcher = pattern.matcher(msg);
			if (matcher.find()) {
				String clinetName = matcher.group();
				if (threadMap.containsKey(clinetName)) {
					if (!threadName.equals(threadMap.get(clinetName))) {
						System.out.println("信息是" + msg);
						System.out.println("不在同一线程:threadName" + threadName
								+ "原有" + threadMap.get(clinetName));
						errorMap.put(clinetName, msg);
					}
				} else {
					threadMap.put(clinetName, threadName);
				}
			}

		} catch (Exception e) {
			System.out.println("错误：" + msg);
		}
	}
}
