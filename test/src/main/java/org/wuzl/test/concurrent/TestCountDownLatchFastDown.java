package org.wuzl.test.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch等待一组完成后才可以继续走 这个类测试先down 然后等待看是否出错
 * @author wuzl
 *
 */
public class TestCountDownLatchFastDown {
	public static void main(String[] args) {
		CountDownLatch latch=new CountDownLatch(1);
		new Runner2(latch, "赛跑者").start();
		try {
			Thread.sleep(2000);
			System.out.println("尝试等待");
			latch.await();//先down 后wait 就算完事了也不错报错 而且保证会有阻塞
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("整个比赛完成");
	}
}
class Runner2 extends Thread{
	private String name;
	private CountDownLatch latch;
	Runner2(CountDownLatch latch,String name){
		this.name=name;
		this.latch=latch;
	}
	@Override
	public void run() {
		try {
			System.out.println(name+"开始跑了");
			this.doRun();
			System.out.println(name+"跑完了");
		} finally{
			latch.countDown();//数字减1
		}
	}
	public void doRun(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}