package org.wuzl.test.leetcode;

import java.util.concurrent.Semaphore;

/**
 * 现在有两种线程，氢 oxygen 和氧 hydrogen，你的目标是组织这两种线程来产生水分子。
 * 
 * 存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。
 * 
 * 氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。
 * 
 * 这些线程应该三三成组突破屏障并能立即组合产生一个水分子。
 * 
 * 你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。
 * 链接：https://leetcode-cn.com/problems/building-h2o
 * 
 * @author ziliang.wu
 *
 */
public class H2O {
	private final Semaphore semaphore1 = new Semaphore(2);
	private final Semaphore semaphore2 = new Semaphore(1);

	public H2O() {

	}

	public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
		semaphore1.acquire(1);
		releaseHydrogen.run();
		this.checkAndRelease();
	}

	public void oxygen(Runnable releaseOxygen) throws InterruptedException {
		semaphore2.acquire(1);
		releaseOxygen.run();
		this.checkAndRelease();
	}

	private void checkAndRelease() {
		if (checkFull()) {
			semaphore1.release(2);
			semaphore2.release(1);
		}
	}

	private boolean checkFull() {
		return semaphore1.availablePermits() == 0 && semaphore2.availablePermits() == 0;
	}

	public static void main(String[] args) {
		H2O h2O = new H2O();
		String input = "HHOHHOOOHOHHHOOOOOHHOHHHHOHHOOOHHHHHOHOOOOOHHOOHOHHOOHHHOOOOHHOOHOHHHHHOOHOOHHHHHOHOOHHHHHOOOOOOOOOHOHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH";
		char[] array = input.toCharArray();
		new Thread(new Runnable() {
			public void run() {
				for (char c : array) {
					if (c == 'H') {
						try {
							h2O.hydrogen(new Runnable() {

								@Override
								public void run() {
									System.out.print("H");
								}
							});
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				for (char c : array) {
					if (c == 'O') {
						try {
							h2O.oxygen(new Runnable() {

								@Override
								public void run() {
									System.out.print("O");
								}
							});
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}
		}).start();
	}
}
