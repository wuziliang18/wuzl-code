package org.wuzl.test.leetcode;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 * 
 * 线程 A 将调用 zero()，它只输出 0 。 线程 B 将调用 even()，它只输出偶数。 线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为
 * 2n。 链接：https://leetcode-cn.com/problems/print-zero-even-odd
 * 
 * @author Lvyue
 *
 */
public class ZeroEvenOdd {
	private int n;
	private final Semaphore semaphore1 = new Semaphore(1);
	private final Semaphore semaphore2 = new Semaphore(0);
	private final Semaphore semaphore3 = new Semaphore(0);

	public ZeroEvenOdd(int n) {
		this.n = n;
	}

	public void zero(IntConsumer printNumber) throws InterruptedException {
		for (int i = 1; i <= n; i++) {
			semaphore1.acquire(1);
			printNumber.accept(0);
			if (i % 2 == 0) {
				semaphore3.release(1);
			} else {
				semaphore2.release(1);
			}
		}
	}

	public void odd(IntConsumer printNumber) throws InterruptedException {
		for (int i = 1; i <= n; i++) {
			if (i % 2 != 0) {
				semaphore2.acquire(1);
				printNumber.accept(i);
				semaphore1.release(1);
			}
		}
	}

	public void even(IntConsumer printNumber) throws InterruptedException {
		for (int i = 1; i <= n; i++) {
			if (i % 2 == 0) {
				semaphore3.acquire(1);
				printNumber.accept(i);
				semaphore1.release(1);
			}
		}
	}

	public static void main(String[] args) {
		ZeroEvenOdd object = new ZeroEvenOdd(6);
		new Thread(new Runnable() {
			public void run() {
				try {
					object.odd(num -> System.out.print(num));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				try {
					object.zero(num -> System.out.print(num));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				try {
					object.even(num -> System.out.print(num));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
