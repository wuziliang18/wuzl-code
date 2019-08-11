package org.wuzl.test.wuzl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Naifen {
	public static void main(String[] args) {
		List<NaifenType> rows = Arrays.asList(new NaifenType(4, 170), new NaifenType(9, 279));
		int max = 899;
		int benefit = 100;
		int sum = 0;
		// int[] result = new int[rows.size()];
		// Arrays.fill(result, 0);
		// while (sum >= max) {
		// for (NaifenType naifen : rows) {
		//
		// }
		// }
		int[] result = { 0, 0 };
		int price = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int tempSum = 170 * i + 279 * j;
				if (tempSum < max) {
					continue;
				}
				int tempPrice = (tempSum - benefit) / (4 * i + 9 * j);
				if (price == 0) {
					price = tempPrice;
				} else if (price > tempPrice) {
					price = tempPrice;
					result = new int[] { i, j };
					sum = tempSum;
					break;
				}
			}
		}
		System.out.println("总价格:" + sum + ",单价:" + price);
		System.out.println(Arrays.toString(result));
	}

}
