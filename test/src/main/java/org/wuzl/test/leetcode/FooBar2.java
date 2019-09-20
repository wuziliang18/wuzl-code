package org.wuzl.test.leetcode;

import java.util.Random;
import java.util.concurrent.Semaphore;
/**
 * 有问题
 * @author Lvyue
 *
 */
public class FooBar2 {
	private int n;

	private final Semaphore semaphore = new Semaphore(3);

	public FooBar2(int n) {
		this.n = n;
	}

	public void foo(Runnable printFoo) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			semaphore.acquire(3);
			printFoo.run();
			semaphore.release(2);
		}
	}

	public void bar(Runnable printBar) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			semaphore.acquire(2);
			printBar.run();
			semaphore.release(2);

		}
	}

	public static void main(String[] args) {
		final FooBar2 bar = new FooBar2(10000);
		new Thread() {

			@Override
			public void run() {
				try {
					bar.foo(new Runnable() {

						@Override
						public void run() {
							System.out.println("foo");
							try {
								Thread.sleep(new Random().nextInt(30));
							} catch (InterruptedException e) {
							}

						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}.start();
		new Thread() {

			@Override
			public void run() {
				try {
					bar.bar(new Runnable() {

						@Override
						public void run() {
							System.out.println("bar");
							try {
								Thread.sleep(new Random().nextInt(30));
							} catch (InterruptedException e) {
							}
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}.start();
	}
}
