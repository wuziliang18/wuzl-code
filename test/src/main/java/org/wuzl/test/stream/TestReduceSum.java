package org.wuzl.test.stream;

import java.util.Arrays;
import java.util.List;

public class TestReduceSum {
	public static void main(String[] args) {
		List<Integer> numerList = Arrays.asList(1, 2, 3, 4, 5);
		System.out.println(numerList.stream().reduce(0, (a, b) -> a + b));
		System.out.println(numerList.stream().reduce(0, Integer::sum));
		System.out.println(numerList.stream().reduce(Integer::sum).get());
	}
}
