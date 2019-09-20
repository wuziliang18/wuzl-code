package org.wuzl.test.leetcode;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class FooBar {
	private int n;

	private final Semaphore semaphore1 = new Semaphore(1);
	private final Semaphore semaphore2 = new Semaphore(0);

	public FooBar(int n) {
		this.n = n;
	}

	public void foo(Runnable printFoo) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			semaphore1.acquire();
			printFoo.run();
			semaphore2.release();
		}
	}

	public void bar(Runnable printBar) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			semaphore2.acquire();
			printBar.run();
			semaphore1.release();

		}
	}

	public static void main(String[] args) {
		final FooBar bar = new FooBar(10000);
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
