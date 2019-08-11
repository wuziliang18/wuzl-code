package org.wuzl.test.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 测试volatile的防止指令重排功能
 * 下边代码 据说可能会死循环 因为没有volatile 修饰 会把while 循环优化 但没有试出来………………
 * @author wuzl
 *
 */
public class TestVolatile {
	private static  boolean stop;
	public static void main(String[] args) {
		Thread thread=new Thread(){
			int i=0;
			@Override
			public void run() {
				while(!stop){
					System.out.println("输出一次");
					try {
						TimeUnit.SECONDS.sleep(i++);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stop=true;
	}
}
