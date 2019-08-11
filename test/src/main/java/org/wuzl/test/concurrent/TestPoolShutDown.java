package org.wuzl.test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestPoolShutDown {
	public static void main(String[] args) {
		ExecutorService executorService=Executors.newCachedThreadPool();
		for(int i=0;i<10;i++){
			executorService.execute(new NoStopThread());
		}
		executorService.shutdown();//不会立刻停止等待线程完成 但不可以新加入任务  但如果是定时执行那种 不会再次执行
//		executorService.execute(new NoStopThread());//会报错
//		try {
//			executorService.awaitTermination(10, TimeUnit.SECONDS);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("开始看戏");//上句不注释 到不了这里
	}
}
class NoStopThread extends Thread{
	@Override
	public void run() {
		long begin=System.currentTimeMillis();
		while(true){
			long end =System.currentTimeMillis();
			if((end-begin)>1000){
				begin=System.currentTimeMillis();
				System.out.println(Thread.currentThread().getName()+"高喊永不止步，有种你阻止我");
			}
			
		}
	}
}