package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.List;

public class TestRooting {
	public static double rooting(int num) {
		double precise = 1e-6;
		double low = 0;
		double high = num;
		while (high > (low + precise)) {
			double mid = (high + low) / 2;
			double temp = mid * mid;
			if (temp > num) {
				high = mid;
			} else {
				low = mid;
			}
		}
		return (high + low) / 2;

	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(4);
		list.add(10);
		list.forEach(num -> {
			System.out.println(num + ":" + Math.sqrt(num) + ">>>>>" + rooting(num));
		});
	}
}
