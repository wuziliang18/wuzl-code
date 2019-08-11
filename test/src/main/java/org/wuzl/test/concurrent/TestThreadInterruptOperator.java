package org.wuzl.test.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestThreadInterruptOperator {
	public static void main(String[] args) throws InterruptedException {
		final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		Thread thread=new Thread() {
			public String get() {
				boolean interruted = false;
				try {
					while (true) {
						try {
							System.out.println("一次获取");
							System.out.println(Thread.currentThread().isInterrupted());
							return queue.take();// 阻塞等待
						} catch (InterruptedException e) {//catch后中断状态变为false
							System.out.println("一次中断");
							System.out.println(Thread.currentThread().isInterrupted());
							interruted = true;// 目的：记录中断状态 重试成功后继续中断 
//							interrupt();//打开后会无限死循环
						}
					}
				} finally {
					if (interruted) {// 之所以在这里去处理是因为阻塞方法都会判断线程中断状态
						Thread.currentThread().interrupt();
					}
				}
			}

			public void run() {
				this.get();
			};
		};
		thread.start();
		Thread.sleep(1000l);
		System.out.println("中断线程");
		thread.interrupt();
	}
}
