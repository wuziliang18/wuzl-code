package org.wuzl.test.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimerCancel {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("当前时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				try {
					Thread.sleep(2000);//模拟运行时间 但不影响结果 只要运行时间小于间隔数就可以 切间隔时间不是从运行完算起而是从开始运行算起
//					Thread.sleep(4000);//如果大于间隔时间 则使用运行时间作为间隔
					System.out.println(Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 1000,3000);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timer.cancel();
	}
}
