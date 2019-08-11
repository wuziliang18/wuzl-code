package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Reverse {
	public static void main(String[] args) {
		List<char[]> data = new ArrayList<>();
		data.add("hello world ni hao ya ceshi".toCharArray());
		data.add("hello world ni hao ya ceshi ab".toCharArray());
		for (char[] input : data) {
			System.out.print("输入:" + new String(input));
			reverseCharArray(input);
			System.out.println("输出:" + new String(input));
		}
	}

	private static void reverseCharArray(char[] input) {
		reverseChar(input, 0, input.length);
		int start = 0;
		// 翻转单词
		for (int i = 0; i < input.length; i++) {
			if (input[i] == ' ' || i == input.length - 1) {
				reverseChar(input, start, i == input.length - 1 ? i + 1 : i);
				start = i + 1;
			}
		}
	}

	private static void reverseChar(char[] input, int start, int end) {
		int mid = (end - start) / 2;
		// 翻转全部
		for (int i = 0; i < mid; i++) {
			int firstIndex = start + i;
			int replaceIndex = end - i - 1;
			char temp = input[firstIndex];
			input[firstIndex] = input[replaceIndex];
			input[replaceIndex] = temp;
		}
	}
}
