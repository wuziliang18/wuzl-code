package org.wuzl.test.concurrent;
/**
 * 测试可见性
 * 不经意容易出现的多线程错误
 * 可能会出现死循环 因为inRun 没有加入volatile 会有修改不可见的可能性 但没有测试出来
 * @author wuzl
 *
 */
public class TestThreadForOverNoVolatile {
	static boolean isRun;
	static int value;
	
	static class TestThread implements Runnable{

		@Override
		public void run() {
			while(!isRun){
				Thread.yield();
			}
			System.out.println(value);
		}
		
	}
	public static void main(String[] args) {
		new Thread(new TestThread()).start();
		value=45;
		isRun=true;
	}
}
