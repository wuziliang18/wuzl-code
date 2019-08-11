package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestPattern {
	public static void main(String[] args) {
		List<String[]> inputList = new ArrayList<>();

		inputList.add(new String[] { "北京 杭州 杭州 北京", "abba", });
		inputList.add(new String[] { "北京 杭州 杭州 北京", "aabb", });
		inputList.add(new String[] { "北京 杭州 杭州 北京", "baab", });
		inputList.forEach(array -> {
			StringBuilder sb = new StringBuilder();
			sb.append("input: ").append(array[0]);
			sb.append(" pattern: ").append(array[1]);
			sb.append(" result: ").append(checkPatern(array[0], array[1]));
			System.out.println(sb);
		});
	}

	public static boolean checkPatern(String input, String pattern) {
		String[] inputArray = input.split(" ");
		char[] patternArray = pattern.toCharArray();
		if (inputArray.length != patternArray.length) {
			return false;
		}
		Map<Character, String> map = new HashMap<>();
		for (int i = 0; i < patternArray.length; i++) {
			char c = patternArray[i];
			String text = inputArray[i];
			String old = map.get(c);
			if (old == null) {
				map.put(c, text);
				continue;
			}
			if (!old.equals(text)) {
				return false;
			}
		}
		return true;
	}
}
