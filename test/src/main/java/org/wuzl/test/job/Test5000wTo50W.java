package org.wuzl.test.job;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Test5000wTo50W {
	public static void main(String[] args) {
		int userCount = 50000;
		int moneyCount = 5000000;
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		Random random = new Random();
		long begin = System.currentTimeMillis();
		while (userCount-- > 0) {
			int money = random.nextInt(moneyCount);
			moneyCount = moneyCount - money;
			result.put(userCount, money);
		}
		long end = System.currentTimeMillis();
		System.out.println("时间:" + (end - begin));
	}
}
