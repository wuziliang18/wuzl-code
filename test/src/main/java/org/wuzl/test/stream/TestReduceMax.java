package org.wuzl.test.stream;

import java.util.Arrays;
import java.util.List;

public class TestReduceMax {
	public static void main(String[] args) {
		List<Integer> numerList = Arrays.asList(1, 2, 3, 4, 5, 10, 12, 2, 8, 16, 3, 12);
		System.out.println(numerList.stream().reduce((x, y) -> x > y ? x : y).get());
		System.out.println(numerList.stream().reduce(0,(x, y) -> x+1));
		System.out.println(numerList.stream().reduce(Integer::max).get());
	}
}
