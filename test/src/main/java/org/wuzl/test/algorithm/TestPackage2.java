package org.wuzl.test.algorithm;

import java.util.Arrays;

/**
 * 背包动态规划 加入价值 求最大价值
 * 
 * @author ziliang.wu
 *
 */
public class TestPackage2 {
	private static int[] items = { 2, 2, 4, 6, 3 }; // 物品的重量
	private static int[] values = { 3, 4, 8, 9, 6 }; // 物品的价值
	private static int maxWeight = 9;

	public static void main(String[] args) {
		System.out.println(getMaxValue());
	}

	public static int getMaxValue() {
		int size = items.length;
		int maxValueArray[] = new int[maxWeight + 1];
		for (int i = 0; i < size; i++) {
			int weight = items[i];
			int value = values[i];
			for (int j = maxWeight; j >= weight; j--) {
				int temp = value + maxValueArray[j - weight];
				if (maxValueArray[j] < temp) {
					maxValueArray[j] = temp;
				}
			}
			System.out.println(Arrays.toString(maxValueArray));
		}
		// 返回值
		for (int i = maxValueArray.length - 1; i >= 0; i--) {
			if (maxValueArray[i] > 0) {
				return maxValueArray[i];
			}
		}
		return 0;
	}

}
