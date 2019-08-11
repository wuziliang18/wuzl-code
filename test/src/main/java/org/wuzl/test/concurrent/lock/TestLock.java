package org.wuzl.test.concurrent.lock;

import java.util.Random;

public class TestLock {
	public static void main(String[] args) {
		WriteThread thread1=new WriteThread("线程1");
		WriteThread thread2=new WriteThread("线程2");
		thread1.start();
		thread2.start();
		ArrangeThread arrangeThread1=new ArrangeThread("zhengli线程1");
		ArrangeThread arrangeThread2=new ArrangeThread("zhengli线程2");
		arrangeThread1.start();
		arrangeThread2.start();
	}
}
class ArrangeThread extends Thread{
	private String name;
	public ArrangeThread(String name) {
		this.name=name;
	}
	@Override
	public void run() {
		while(true){
			System.out.println(name+"开始整理数据########");
			ThreadsLock.beginArrange();
			for(int i=0;i<10;i++){
				try {
					System.out.println(name+"整理"+i+"条后开始等待");
					Thread.sleep(new Random().nextInt(300));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(name+"完成一次整理数据%%%%%%%%%%");
			ThreadsLock.endArrange();
		}
	}
}
class WriteThread extends Thread{
	private String name;
	public WriteThread(String name) {
		this.name=name;
	}
	@Override
	public void run() {
		while(true){
			System.out.println(name+"开始写入数据########");
			ThreadsLock.beginWrite();
			for(int i=0;i<10;i++){
				try {
					System.out.println(name+"写入"+i+"条后开始等待");
					Thread.sleep(new Random().nextInt(300));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(name+"完成一次写入数据%%%%%%%%%%");
			ThreadsLock.endWrite();
		}
	}
}