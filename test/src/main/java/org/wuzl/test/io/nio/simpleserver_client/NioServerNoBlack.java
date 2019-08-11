package org.wuzl.test.io.nio.simpleserver_client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 非阻塞的服务端
 * @author wuzl
 *
 */
public class NioServerNoBlack {
	public static void main(String[] args) throws Exception {
		ServerSocketChannel ssc=ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ssc.socket().bind(new InetSocketAddress(10000));
		while(true){//这种只能保证第一个连上 断开后在接收第二个 考虑把接收数据 单独线程处理 使得这个循环不断循环
			SocketChannel socketChannel=ssc.accept();
			if(socketChannel==null){
				Thread.sleep(500l);
				continue;
			}
			System.out.println("有人连接上了 打个招呼");
			ByteBuffer bb=ByteBuffer.wrap("你已经连接上了服务器，可以发送消息了".getBytes());
			socketChannel.write(bb);
			bb.clear();
			while(socketChannel.read(bb)>0){ // 接收数据，也是以阻塞方式
				bb.flip();//读转写
				System.out.println("接收数据:"+new String(bb.array()));//输出
			}
		}
	}
}
