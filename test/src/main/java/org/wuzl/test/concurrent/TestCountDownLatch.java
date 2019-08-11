package org.wuzl.test.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch等待一组完成后才可以继续走
 * @author wuzl
 *
 */
public class TestCountDownLatch {
	public static void main(String[] args) {
		int runnerCount=5;
		CountDownLatch latch=new CountDownLatch(runnerCount);
		ExecutorService race=Executors.newFixedThreadPool(runnerCount);
		for(int i=0;i<runnerCount;i++){
			race.execute(new Runner(latch, "赛跑者"+i));
		}
		try {
			latch.await();//阻塞如果赛跑者少于runnerCount 也就是CountDownLatch的参数会永远等待 keyi使用latch.await(timeout, unit)
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		race.shutdown();
		System.out.println("整个比赛完成");
	}
}
/*
 * 赛跑者
 */
class Runner extends Thread{
	private String name;
	private CountDownLatch latch;
	Runner(CountDownLatch latch,String name){
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
			Thread.sleep(new Random().nextInt(5000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}