package org.wuzl.test.stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class TestStreamIterate {

	public static void main(String[] args) {
		// 斐波纳契元组序列
		Stream.iterate(new int[] { 0, 1 }, (a) -> new int[] { a[1], a[0] + a[1] }).limit(10)
				.forEach(t -> System.out.printf(Arrays.toString(t)));
	}
}
