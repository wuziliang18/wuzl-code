package org.wuzl.test.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class EveryDayWork {
	//一天间隔
	private static final long PERIOD_TIME= 24 * 60 * 60 * 1000;//从第一次开始运行后开始执行 可能不是预计的结果因为如果定时的时间已经大于当前 会从当前开始计时
	public void runEveryDayWork(){
		Calendar calendar = Calendar.getInstance();  
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) ;
		int day = calendar.get(Calendar.DAY_OF_MONTH)+1;//从第二天开始运行
		calendar.set(year, month, day, 01, 00, 00);
        Date date=calendar.getTime(); //第一次执行定时任务的时间
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
			@Override
			public void run() {
				//此处运行代码
				System.out.println("当前时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}
		}, date,PERIOD_TIME);
	}
	public static void main(String[] args) {
		new EveryDayWork().runEveryDayWork();
	}
}
