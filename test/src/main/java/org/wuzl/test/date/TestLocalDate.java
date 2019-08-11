package org.wuzl.test.date;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class TestLocalDate {
	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		print(date);
		date = LocalDate.of(2018, 6, 16);
		print(date);
		date = LocalDate.parse("2018-12-21");
		print(date);
//		date = LocalDate.parse("2018年12月21日");// error 需要传入DateTimeFormatter
		print(date);
		date = LocalDate.of(2018, 4, 22);
		System.out.println(date.get(ChronoField.DAY_OF_WEEK));;

	}

	private static void print(LocalDate date) {
		System.out.println(date.getYear());
		System.out.println(date.getMonth());
		System.out.println(date.getDayOfMonth());
	}
}
