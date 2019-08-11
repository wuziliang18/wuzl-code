package org.wuzl.test.concurrent.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ThreadsLock {
	private static AtomicInteger WRITE_THREADS=new AtomicInteger();//写入数据线程数
	private static AtomicInteger ARRANGE_THREADS=new AtomicInteger();//整理数据库线程数
	private static Lock lock=new ReentrantLock();//使用锁使得判断同步
	private ThreadsLock(){
	}
	/**
	 * 开始写入数据
	 */
	public static void beginWrite(){
		try {
			lock.lock();
			/*1.判断是否有进程在整理数据库*/
			if(ARRANGE_THREADS.get()!=0){
				while(ARRANGE_THREADS.get()!=0){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			/*2.标记在写入数据*/
			WRITE_THREADS.incrementAndGet();
		}finally{
			lock.unlock();
		}
	}
	/**
	 * 结束写入数据 一定要有调用否则会无法整理数据
	 */
	public static void endWrite(){
		WRITE_THREADS.getAndDecrement();
	}
	/**
	 * 开始整理数据
	 */
	public static void beginArrange(){
		lock.lock();
		try {
			/*1.判断是否有进程在写入数据*/
			if(WRITE_THREADS.get()!=0){
				while(WRITE_THREADS.get()!=0){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			/*2.标记在整理数据*/
			ARRANGE_THREADS.incrementAndGet();
		} finally{
			lock.unlock();
		}
	}
	/**
	 * 结束整理数据 一定要有调用否则会无法写入数据
	 */
	public static void endArrange(){
		ARRANGE_THREADS.getAndDecrement();
	}
}
