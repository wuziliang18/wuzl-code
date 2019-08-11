package org.wuzl.test.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.wuzl.test.concurrent.SimpleThread;

public class CacheThreadPools {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService cachePool=Executors.newCachedThreadPool();
		for(int i=0;i<10;i++){
			cachePool.execute(new SimpleThread());
			try {
				TimeUnit.MILLISECONDS.sleep(1);//会导致顺序执行 如果时间小岛线程没执行完 才会并发
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("开始了呀");
		cachePool.shutdown();
		cachePool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		System.out.println("完事了呀");
	}
}
