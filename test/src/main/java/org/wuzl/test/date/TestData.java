package org.wuzl.test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestData {
	public static void main(String[] args) throws InterruptedException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf
				.parse(String.valueOf(""));
		System.out.println(date);
		Long time = 1430291081 * 1000l;
		System.out.println(new SimpleDateFormat("yyyy年MM月dd日 HH时:mm分:ss秒")
				.format(new Date(time)));

		Calendar calendar = Calendar.getInstance();

		System.out.println(calendar.getTime());
		TimeUnit.SECONDS.sleep(1l);
		System.out.println(calendar.getTime());
		calendar = Calendar.getInstance();

		System.out.println(calendar.getTime());
	}
}
