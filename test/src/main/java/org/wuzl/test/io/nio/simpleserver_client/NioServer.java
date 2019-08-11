package org.wuzl.test.io.nio.simpleserver_client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioServer {
	public static void main(String[] args) {
		try {
			ServerSocketChannel serverSocketChanel=ServerSocketChannel.open();
//			serverSocketChanel.configureBlocking(false);//非阻塞 必须阻塞 否则需要轮训判断sockeyChanel 加入selector后可以不阻塞
			serverSocketChanel.socket().bind(new InetSocketAddress("localhost",10000));
			SocketChannel sockeyChanel=serverSocketChanel.accept();//阻塞等到一个客户端 连上后在接受不了其他的连接  断开后也不能重连
			System.out.println("接入连接");
			ByteBuffer bb=ByteBuffer.allocate(1024);
			while(true){
				bb.clear();///每次要清空
				if(sockeyChanel.read(bb)>0){ // 接收数据，也是以阻塞方式
					bb.flip();//读转写
					System.out.println("接收数据:"+new String(bb.array()));//输出
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
