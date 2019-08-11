package org.wuzl.test.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试CyclicBarrier
 * 作用是多个线程并行 到一个点后等待其他线程 所有完毕后 继续下一波
 * @author wuzl
 *
 */
public class TestCyclicBarrier {
	public static void main(String[] args) {
		new HorseRace(10, 100);
	}
}
class Horse implements Runnable{
	private static int counter=0;
	private int id=counter++;
	private int striders=0;
	private static Random random=new Random();
	private final CyclicBarrier cyclicBarrier;
	public Horse(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier=cyclicBarrier;
	}
	public synchronized int getStriders(){
		return striders;
	}
	public String tracks(){
		StringBuilder s=new StringBuilder();
		for(int i=0;i<getStriders();i++){
			s.append("*");
		}
		return s.toString();
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){//必须要这么判断
				synchronized(this){
					striders+=random.nextInt(3);
				}
				cyclicBarrier.await();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
class HorseRace{
	static final int FINISH_LINE=75;
	private List<Horse> horses=new ArrayList<Horse>();
	private ExecutorService service=Executors.newCachedThreadPool();
	public HorseRace(int nHorses,final long  pause) {
		CyclicBarrier cyclicBarrier=new CyclicBarrier(nHorses, new Runnable() {
			@Override
			public void run() {//每一次完毕的时候执行
				StringBuilder sb=new StringBuilder();
				for(int i=0;i<FINISH_LINE;i++){
					sb.append("=");
				}
				System.out.println(sb);
				for(Horse horse:horses){
					System.out.println(horse.tracks());
				}
				for(Horse horse:horses){
					if(horse.getStriders()>=FINISH_LINE){
						System.out.println("跑完了赢了");
						service.shutdownNow();
						return;
					}
				}
				try {
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		for(int i=0;i<nHorses;i++){
			Horse horse=new Horse(cyclicBarrier);
			horses.add(horse);
			service.submit(horse);
		}
	}
}