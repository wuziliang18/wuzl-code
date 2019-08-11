package org.wuzl.test.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * wait nofity等只能用与同步快或lock内
 * 
 * @author wuzl
 * 
 */
public class TestWait {
	void test() {
		System.out.println("test开始");
		try {
			this.wait();// 会报错因为没有获取到锁
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("test结束");
	}

	void test2() {
		System.out.println("test2开始");
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("test2结束");
	}

	void test3() {
		System.out.println("test3开始");
		synchronized (this) {
			test();// 不会报错 因为调用的test方法已经包含在同步内
		}
		System.out.println("test3结束");
	}

	synchronized void unLock() {
		System.out.println("开始notifyAll");
		this.notifyAll();//必须获取到锁
	}

	public static void main(String[] args) {
		final TestWait test = new TestWait();
		// test.test();//报错
		// test.test3();//不会报错
		new Thread() {// 即使多个线程也没有问题 因为在同步快内 没有获取锁的线程不会执行wait
			public void run() {
				test.test2();
			};
		}.start();
		// test.notifyAll();//会报错 因为没有获取到锁
		try {
			TimeUnit.SECONDS.sleep(1);//等待下否则没有wait直接解锁notifyAll了
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.unLock();//不会报错
		test.test2();// 不会报错
	}
}
