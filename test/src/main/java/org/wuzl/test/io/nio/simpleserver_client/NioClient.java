package org.wuzl.test.io.nio.simpleserver_client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NioClient {
	public static void main(String[] args) {
		try {
			SocketChannel socketChannel = SocketChannel.open(); 
			socketChannel.socket().connect(new InetSocketAddress("localhost", 10000));
			ByteBuffer buffer = ByteBuffer.allocate(1024); // 创建Buffer
//			socketChannel.read(buffer);
//			System.out.println("来自于服务器的消息:"+new String(buffer.array()));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(new Date());
			StringBuilder msg=new StringBuilder();
			int num=3;
//			Thread.sleep(num*1000l);
			for(int i=0;i<100;i++){
				try {
					msg=new StringBuilder();
					buffer.clear();
					msg.append(i);
					for(int j=0;j<2;j++){
						msg.append("客户端"+num+"测试数据");
					}
					msg.append("结束"+i);
					buffer.put(msg.toString().getBytes());
					buffer.flip();
					System.out.println("通道状态"+socketChannel.isConnected());
					socketChannel.write(buffer);
//				System.out.println("客户端2发送一条数据："+msg);
					System.out.println("发送数据成功");
					Thread.sleep(2000l);
				} catch (Exception e) {
					//模拟中途断开
					System.out.println("通道状态"+socketChannel.isConnected());
					System.out.println(socketChannel.isOpen());
					System.out.println(socketChannel.socket().isConnected());
					System.out.println(socketChannel.socket().isClosed());
					System.out.println("通道状态"+socketChannel.isConnected());
					System.out.println(socketChannel.isOpen());
					System.out.println(socketChannel.socket().isConnected());
					System.out.println(socketChannel.socket().isClosed());
					socketChannel.socket().connect(new InetSocketAddress("localhost", 10000));
					e.printStackTrace();
				}
			}
			socketChannel.close();
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
}
