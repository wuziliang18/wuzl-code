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
public class TestScheduledaFor {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledThreadPoolExecutor executorService=new ScheduledThreadPoolExecutor(2);//如果初始线程数小于要运行的线程 最后一个等待
		ScheduledFuture<?> future=executorService.scheduleWithFixedDelay(new TestThread1(), 2000, 1000, TimeUnit.MILLISECONDS);//每隔1s运行一次 最初一次是等待2s'
		System.out.println(future);
//		executorService.shutdown();//会终止 不像普通线程池一样
	}
	
}
class TestThread1 extends Thread{
	public TestThread1() {//只初始化一次
		System.out.println(Thread.currentThread());
	}
	@Override
	public void run() {
		String threadName=Thread.currentThread().getName();
		for(int i=0;i<10;i++){
			System.out.println("1线程"+threadName+"打印时间"+new Date());
		}
		System.out.println("完成一次");
		
	}
}