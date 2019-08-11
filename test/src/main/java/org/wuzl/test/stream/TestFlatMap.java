package org.wuzl.test.stream;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

/**
 * 输出对数
 * 
 * @author ziliang.wu
 *
 */
public class TestFlatMap {
	public static void main(String[] args) {
		List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		List<Integer> numbers2 = Arrays.asList(3, 4);
		List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[] { i, j }))
				.collect(toList());
		pairs.forEach(pair -> System.out.println(Arrays.toString(pair)));
	}
}
