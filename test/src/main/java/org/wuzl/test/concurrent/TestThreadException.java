package org.wuzl.test.concurrent;

import java.util.concurrent.TimeUnit;
/**
 * 默认情况下线程抛出的异常main是无法捕获的 会直接抛到控制台
 * 可以使用threadFactory
 * 
 * @author wuzl
 *
 */
public class TestThreadException {
	public static void main(String[] args) {
		System.out.println("ZILIANG".toLowerCase().equals("ziliang"));
		Thread thread;
//		try {
//			thread=new ExceptionThread();
//			thread.start();
//		} catch (Exception e) {
//			System.out.println("捕获异常"+e.getMessage());//捕获不到的 抛到控制台 但后边的代码会继续运行
//		}
		try {
			thread=new ExceptionThread();
			thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
			thread.start();
		} catch (Exception e) {
			System.out.println("捕获异常"+e.getMessage());//捕获不到的 但MyUncaughtExceptionHandler已经捕获
		}
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());//设置默认的错误捕获
		try {
			thread=new ExceptionThread();
			thread.start();
		} catch (Exception e) {
			System.out.println("捕获异常"+e.getMessage());//捕获不到的 但MyUncaughtExceptionHandler已经捕获
		}
		try {
			TimeUnit.SECONDS.sleep(1l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end");
	}
}
class ExceptionThread extends Thread{
	@Override
	public void run() {
		throw new RuntimeException("出现一个线程错误");
	}
}
/**
 * 捕获线程异常
 * @author wuzl
 *
 */
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("利用UncaughtExceptionHandler:"+e.getMessage());
//		throw new RuntimeException("利用UncaughtExceptionHandler再次抛出"+e.getMessage());
	}
	
}