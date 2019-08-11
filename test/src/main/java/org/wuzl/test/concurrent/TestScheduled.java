package org.wuzl.test.concurrent;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试定时任务的线程池
 * @author wuzl
 *
 */
public class TestScheduled {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//可以使用executors静态生成
//		ScheduledThreadPoolExecutor executorService=(ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(1);
		ScheduledThreadPoolExecutor executorService=new ScheduledThreadPoolExecutor(2);//如果初始线程数小于要运行的线程 最后一个等待
		ScheduledFuture<String> result1=executorService.schedule(new TestThread(), 3, TimeUnit.SECONDS);
		ScheduledFuture<String> result2=executorService.schedule(new TestThread2(), 1, TimeUnit.SECONDS);
		ScheduledFuture<String> result3=executorService.schedule(new TestThread3(), 2, TimeUnit.SECONDS);
		System.out.println("开始关闭");
		executorService.shutdown();
		System.out.println("结果可以看到了吗");
		System.out.println(result1.get());//只有所有运行完了才会打印
		System.out.println("一直等着上边那个get呢");
//		while(result1.isDone()){
//			System.out.println("这时候都有结果拉吧");	
//			System.out.println(result1.get());
//			System.out.println(result2.get());
//		}
		executorService.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(new Date());
				
			}
		}, 10000, 1000, TimeUnit.MILLISECONDS);//每隔1s运行一次 最初一次是等待10s
	}
}
/**
 * 与runable和thread类似不过有返回值
 * @author wuzl
 *
 */
class TestThread implements Callable<String>{

	@Override
	public String call() throws Exception {
		String threadName=Thread.currentThread().getName();
		for(int i=0;i<10;i++){
			System.out.println("1线程"+threadName+"打印时间"+new Date());
			Thread.sleep(1000);
		}
		return threadName+"end";
	}
	
}
class TestThread2 implements Callable<String>{class TestThread implements Callable<String>{

	@Override
	public String call() throws Exception {
		String threadName=Thread.currentThread().getName();
		for(int i=0;i<10;i++){
			System.out.println("线程"+threadName+"打印时间"+new Date());
			Thread.sleep(1000);
		}
		return threadName+"end";
	}
	
}

	@Override
	public String call() throws Exception {
		String threadName=Thread.currentThread().getName();
		for(int i=0;i<10;i++){
			System.out.println("2线程"+threadName+"打印时间"+new Date());
			Thread.sleep(1000);
		}
		return threadName+"end";
	}
	
}
class TestThread3 implements Callable<String>{

	@Override
	public String call() throws Exception {
		String threadName=Thread.currentThread().getName();
		for(int i=0;i<10;i++){
			System.out.println("3线程"+threadName+"打印时间"+new Date());
			Thread.sleep(1000);
		}
		return threadName+"end";
	}
	
}
