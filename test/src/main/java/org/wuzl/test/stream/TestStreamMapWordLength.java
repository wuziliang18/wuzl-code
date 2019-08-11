package org.wuzl.test.stream;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public class TestStreamMapWordLength {
	public static void main(String[] args) {
		List<String> wordList = Arrays.asList("java 8", "lambdas", "in", "action");
//		List<Integer> result = wordList.stream().map(word -> word.length()).collect(toList());
		List<Integer> result = wordList.stream().map(String::length).collect(toList());
		System.out.println(result);
	}
}
