package org.wuzl.test.date;

public class TestTIme {
	public static void main(String[] args) {
		System.out.println(System.nanoTime());
		System.out.println(System.currentTimeMillis());
		System.out.println(1429682978248L);
		Long money=550000l;
		long now=50000;
		long surplus=money-now;
		System.out.println("当前取:"+now);
		System.out.println("剩余:"+surplus);
		System.out.println("我可以取:"+surplus/2);
		long every=15000l*12/100*2;
		long months=surplus/2/every;
		long years=months/12;
		System.out.println(String.format("每月取%d，取%d月，%d年", every,months,years));
	}
}
