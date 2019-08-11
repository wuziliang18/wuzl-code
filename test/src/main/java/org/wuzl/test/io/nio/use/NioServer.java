package org.wuzl.test.io.nio.use;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 每一个连接对应一个SelectionKey 负责接入的一个key 其他的每个客户端一个key
 * 每个连接上搭载一个buffer 
 * @author wuzl
 * 
 */
public class NioServer {
	private int port;
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	private static final ExecutorService THREAD_POOL = Executors
			.newFixedThreadPool(10);

	public NioServer(int port) {
		this.port = port;
		try {
			selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleData(final SelectionKey selectedKey) {
		THREAD_POOL.submit(new Runnable() {
			@Override
			public void run() {
				SocketChannel socketChannel = (SocketChannel) selectedKey
						.channel();
				ByteBuffer buffer = (ByteBuffer) selectedKey.attachment();
				buffer.clear();
				int count = 0;
				try {
					while ((count = socketChannel.read(buffer)) > 0) { // 读取接收到的数据
						buffer.flip();
						byte[] dst = new byte[buffer.limit()];
						buffer.get(dst);
						buffer.clear();
						System.out.println("服务端接收数据：" + new String(dst));
					}
					if (count < 0) {
						System.out.println("关闭同道");
						try {
							socketChannel.close();// 关闭同道 最可行的方案 解决注释上的问题
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// 没有可用字节,继续监听OP_READ
					selectedKey.interestOps(selectedKey.interestOps()
							| SelectionKey.OP_READ);
					selectedKey.selector().wakeup();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public void start() {
		try {
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress(port));
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("启动成功");
			while (true) {
				int selects = selector.select();
				if (selects > 0) {// 有连接接入
					Set<SelectionKey> selectedKeys = selector.selectedKeys(); // 获取发生的事件
					Iterator<SelectionKey> it = selectedKeys.iterator(); // 依次进行处理
					while (it.hasNext()) {
						SelectionKey selectedKey = it.next();
						it.remove();
						if (selectedKey.isAcceptable()) {// 是新连接
							// 获取注册的ServerSocketChannel
							ServerSocketChannel serverSocketChanel = ((ServerSocketChannel) selectedKey
									.channel());
							SocketChannel socketChanel = serverSocketChanel
									.accept();// 建立连接
							socketChanel.configureBlocking(false);// 必须有 否则无法注册
							SelectionKey key = socketChanel.register(selector,
									SelectionKey.OP_READ);// 注册读监听
							ByteBuffer buffer = ByteBuffer.allocate(1024);
							key.attach(buffer);// 每一个连接都会有唯一的key
							System.out.println("服务端连接上客户端: "
									+ socketChanel.socket()
											.getRemoteSocketAddress());
						} else if (selectedKey.isReadable()) {
							selectedKey.interestOps(selectedKey.interestOps()
									& (~SelectionKey.OP_READ));
							System.out.println("处理数据");
							this.handleData(selectedKey);
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			selector.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			serverSocketChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		THREAD_POOL.shutdown();
	}

	public static void main(String[] args) {
		new NioServer(20880).start();
	}
}
