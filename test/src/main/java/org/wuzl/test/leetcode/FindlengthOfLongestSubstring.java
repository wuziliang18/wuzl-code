package org.wuzl.test.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 
 * @author ziliang.wu
 *
 */
public class FindlengthOfLongestSubstring {
	public static void main(String[] args) {
		List<String> inputList = new ArrayList<>();
		inputList.add("abcabcbb");
		inputList.add("bbbbb");
		inputList.add("pwwkew");
		inputList.forEach(input -> {
			System.out.println("输入:" + input + ",输出:" + solution(input));
		});
	}

	public static int solution(String input) {
		if (input == null || input.length() == 0) {
			return 0;
		}
		int max = 0;
		int tempMax = max;
		char[] array = input.toCharArray();
		Set<Character> set = new HashSet<>();
		for (char c : array) {
			if (set.contains(c)) {
				max = Math.max(max, tempMax);
				set.clear();
				tempMax = 0;
				continue;
			}
			tempMax++;
			set.add(c);
		}
		//记得收尾
		max = Math.max(max, tempMax);
		return max;
	}
}
