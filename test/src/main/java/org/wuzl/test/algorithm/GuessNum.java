package org.wuzl.test.algorithm;

import java.util.Random;

/**
 * 从100中猜一个数
 * 
 * @author ziliang.wu
 *
 */
public class GuessNum {
	public static void main(String[] args) {
		int maxNum=100;
		int count = 50;
		while (count-- > 0) {
			int num = new Random().nextInt(maxNum-1) + 1;
			System.out.println("num is " + num + " guess count: " + guess(0, maxNum, num));
		}

	}

	public static int guess(int start, int end, int num) {
		int count = 0;
		while (start != end) {
			int mid = (end + start) / 2;
			count++;
			if (mid == num) {
				return count;
			}
			if (mid < num) {
				start = mid + 1; // 之前没有想到 大概率会减少循环
			} else {
				end = mid - 1;// 之前没有想到 大概率会减少循环
			}
		}
		return count;
	}

}
