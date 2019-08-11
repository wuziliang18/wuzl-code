package org.wuzl.test.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * 测试使用组播
 * 客户端
 * @author wuzl
 *
 */
public class TestMulticastClient {
	public static void main(String[] args) throws Exception {
		InetAddress mutilcastAddress=InetAddress.getByName("230.0.0.1");//多播地址
		final MulticastSocket socket=new MulticastSocket(2048);//不要求与服务端一样如果一样自己也可以接受到信息
		socket.joinGroup(mutilcastAddress);//加入组
		for(int i=0;i<50;i++){//发送信息
			String msg="hello multicast汉子可以吗"+i;
			DatagramPacket datagramPacket=new DatagramPacket(msg.getBytes(),msg.getBytes().length,mutilcastAddress,1024);//发送必须指定地址和端口
			socket.send(datagramPacket);
		}
		new Thread(){
			@Override
			public void run() {
				byte[] receiveBytes=new byte[100];
				DatagramPacket datagramPacket=new DatagramPacket(receiveBytes,receiveBytes.length);
				while(true){
					try {
						socket.receive(datagramPacket);
						System.out.println("收到信息是："+new String(datagramPacket.getData()));
						System.out.println("来源是"+datagramPacket.getSocketAddress());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();;
		//下边是关闭代码
//		socket.leaveGroup(mutilcastAddress);
//		socket.close();
	}
}
