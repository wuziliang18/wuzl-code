package org.wuzl.test.io.nio.simpleserver_client;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.wuzl.test.io.file.GetCurrentDir;
/**
 * 接收大数据 写入文件
 * @author wuzl
 *
 */
public class SelectorServerBigMsg {
	private final static String FILE_SEPARATOR =System.getProperty("line.separator");  
	public static void main(String[] args) {
		try {
			Selector selector = Selector.open();
			ServerSocketChannel serverSocketChanel = ServerSocketChannel.open();
			serverSocketChanel.socket().bind(new InetSocketAddress(20880));
			serverSocketChanel.configureBlocking(false);// 非阻塞
			serverSocketChanel.register(selector, SelectionKey.OP_ACCEPT);// 注册连接监听
			System.out.println(serverSocketChanel);
			File file=GetCurrentDir.getFileCreateAfterDelete("org.wuzl.test.io.nio.simpleserver_client","nio.txt");
			FileChannel fileChannel=new FileOutputStream(file).getChannel();
			ByteBuffer fileBuffer = ByteBuffer.wrap(">>>>".getBytes());
			while (true) {
				System.out.println("进入循环");
				int selectChannel=selector.select();
				System.out.println("selectChannel:"+selectChannel);
				if (selectChannel > 0) {// 阻塞 直到有感兴趣的事件发生
					Set<SelectionKey> selectedKeys = selector.selectedKeys(); // 获取发生的事件
					Iterator<SelectionKey> it = selectedKeys.iterator(); // 依次进行处理
					while (it.hasNext()) {// 处理事件
						SelectionKey selectedKey = it.next(); 
						if (selectedKey.isAcceptable()) {// 是新连接
							// 获取注册的ServerSocketChannel
							serverSocketChanel = ((ServerSocketChannel) selectedKey
									.channel());
							System.out.println(serverSocketChanel);
							SocketChannel socketChanel = serverSocketChanel
									.accept();// 建立连接
							socketChanel.configureBlocking(false);//必须有 否则无法注册
							socketChanel.register(selector,
									SelectionKey.OP_READ);// 注册读监听
						} else {
							// 获取注册的SocketChannel
							SocketChannel socketChannel = (SocketChannel) selectedKey
									.channel();
							ByteBuffer buffer = ByteBuffer.allocate(1024);
							int count;
							while ((count=socketChannel.read(buffer)) > 0) { // 读取接收到的数据
								buffer.flip();
								fileChannel.write(buffer);
								buffer.clear();
							}
							
							fileChannel.write(fileBuffer);
							fileBuffer.flip();
							if(count<0){
								System.out.println("关闭同道");
								socketChannel.close();//关闭同道 最可行的方案 解决注释上的问题
							}
						}	
						it.remove();//移除事件 已经选择的键不会被自动移除 用完了必须自己手动删除
					}
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
