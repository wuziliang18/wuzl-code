package org.wuzl.test.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * 测试使用组播
 * 服务端
 * @author wuzl
 *
 */
public class TestMulticastServer {
	public static void main(String[] args) throws Exception {
		final InetAddress mutilcastAddress=InetAddress.getByName("230.0.0.1");//多播地址
		final MulticastSocket socket=new MulticastSocket(1024);
		socket.setLoopbackMode(false);
		socket.joinGroup(mutilcastAddress);//加入组
		new Thread(){
			@Override
			public void run() {
				byte[] receiveBytes=new byte[100];
				DatagramPacket datagramPacket=new DatagramPacket(receiveBytes,receiveBytes.length);
				while(true){
					try {
						socket.receive(datagramPacket);
						System.out.println("收到信息是："+new String(datagramPacket.getData()));
						System.out.println("来源是"+datagramPacket.getSocketAddress());//ip是来源ip 端口是group端口
						System.out.println("吱声意思下");
						String returnMsg="吱";
						//注意端口不一样
						DatagramPacket send=new DatagramPacket(returnMsg.getBytes(),returnMsg.getBytes().length,mutilcastAddress,2048);
						socket.send(send);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();;
		System.out.println("服务启动");
	}
}
