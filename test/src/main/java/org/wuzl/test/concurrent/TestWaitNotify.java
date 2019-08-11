package org.wuzl.test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/*
 * 注意 要wait外边套while循环 用条件控制 防止被意外或无意义的唤醒
 * while循环要同步 否则容易死锁
 */
public class TestWaitNotify {
	public static void main(String[] args) {
		ExecutorService execuoreEervice=Executors.newCachedThreadPool();
		CookRoom cookRoom =new CookRoom();
		execuoreEervice.submit(new Cooker(cookRoom));
		execuoreEervice.submit(new Eater(cookRoom));
		execuoreEervice.shutdown();
	}
}

class Cooker implements Runnable {
	private CookRoom cookRoom;

	public Cooker(CookRoom cookRoom) {
		this.cookRoom = cookRoom;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			System.out.println("厨师的工作");
			cookRoom.cooking();
			cookRoom.waitEat();
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("做饭结束了");
	}
}

class Eater implements Runnable {
	private CookRoom cookRoom;

	public Eater(CookRoom cookRoom) {
		this.cookRoom = cookRoom;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			System.out.println("吃货的工作");
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cookRoom.wartCooking();
			cookRoom.eat();
		}
		System.out.println("吃东西结束了");

	}

}

class CookRoom {
	private boolean haveFood = false;

	public synchronized void eat() {
		System.out.println("吃了食物");
		haveFood = false;
		notifyAll();
	}

	public synchronized void cooking() {
		System.out.println("做好食物");
		haveFood = true;
		notifyAll();
	}

	public synchronized void wartCooking() {
		while (!haveFood) {
			try {
				System.out.println("等待做饭的");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void waitEat() {
		while (haveFood) {
			try {
				System.out.println("等待吃饭的");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}