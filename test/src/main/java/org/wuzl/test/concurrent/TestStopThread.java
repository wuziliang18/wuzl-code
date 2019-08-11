package org.wuzl.test.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 据说部分机器上不会停止 因为isStop没有同步 虚拟机会对while进行优化
 * 
 * @author wuzl
 *
 */
public class TestStopThread extends Thread {
	private boolean isStop;

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}

	public void run() {
		int i = 0;
		while (!isStop) {
			System.out.println(i++);
		}
	};

	public static void main(String[] args) {
		try {
			TestStopThread thread = new TestStopThread();
			thread.start();
			TimeUnit.SECONDS.sleep(1l);
			thread.setStop(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
