package org.wuzl.test.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 使用Exchange 交换两个线程的对象
 * @author wuzl
 *
 */
public class TestExchange {
	static int size=20;
	public static void main(String[] args) {
		ExecutorService pool=Executors.newCachedThreadPool();
		List<String> rowsP=new ArrayList<String>();
		List<String> rowsC=new ArrayList<String>();
		Exchanger<List<String>> exchanger=new Exchanger<List<String>>();
		pool.execute(new Produceer(exchanger, rowsP));
		pool.execute(new Consumer(exchanger, rowsC));
	}
}
class Produceer implements Runnable{
	private List<String> rows;
	private Exchanger<List<String>> exchanger;
	public Produceer(Exchanger<List<String>> exchanger,List<String> rows) {
		this.exchanger=exchanger;
		this.rows=rows;
	}
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			
			try {
				System.out.println("生产者开始生产");
				for (int i = 0; i < TestExchange.size; i++) {
					rows.add( i+"");
					TimeUnit.MICROSECONDS.sleep(500);
				}
				System.out.println(rows);
				System.out.println("生产者生产完毕"+rows.size());
				System.out.println("生产者开始交换");
				this.rows=exchanger.exchange(rows);
				System.out.println("生产者交换完毕");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class Consumer implements Runnable{
	private List<String> rows;
	private Exchanger<List<String>> exchanger;
	public Consumer(Exchanger<List<String>> exchanger,List<String> rows) {
		this.exchanger=exchanger;
		this.rows=rows;
	}
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				System.out.println("消费者开始交换");
				this.rows=exchanger.exchange(rows);
				System.out.println(rows);
				System.out.println("消费者交换完毕"+rows.size());
				for (int i = TestExchange.size-1; i >=0; i--) {//注意arraylist的循环删除
//					System.out.println(rows.get(i));
					rows.remove(i);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
}