package org.wuzl.test.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 示例 1：
 * 
 * 输入: "babad" 输出: "bab" 注意: "aba" 也是一个有效答案。 示例 2：
 * 
 * 输入: "cbbd" 输出: "bb"
 * 
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 
 * @author ziliang.wu
 *
 */
public class LongestPalindrome {
	public static void main(String[] args) {
		List<String> inputs = Arrays.asList("babad", "bab", "cbbd", "aaaabccba");
		PrintUtil.print(inputs, input -> solution(input));
	}

	public static String solution(String input) {
		if (input == null || input.equals("")) {
			return "";
		}
		int start = 0;
		int end = 0;
		for (int i = 0; i < input.length(); i++) {
			int leftLenght = expandAroundCenter(input, i, i);
			int rightLenght = expandAroundCenter(input, i, i + 1);
			int length = Math.max(leftLenght, rightLenght);
			if (length > (end - start)) {
				start = i - (length - 1) / 2;
				end = i + length / 2;
			}
		}
		return input.substring(start, end + 1);
	}

	private static int expandAroundCenter(String s, int left, int right) {
		while (left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		return right - left - 1;
	}
}
