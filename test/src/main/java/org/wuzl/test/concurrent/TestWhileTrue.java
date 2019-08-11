package org.wuzl.test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class TestWhileTrue {
	static final AtomicLong count = new AtomicLong();
	static ExecutorService threadPool = Executors.newFixedThreadPool(20);

	public static void main(String[] args) {
		while (true) {
			threadPool.submit(new Runnable() {

				@Override
				public void run() {
//					count.getAndIncrement();
//					System.out.println(count.get());
				}
			});
		}
	}
}
