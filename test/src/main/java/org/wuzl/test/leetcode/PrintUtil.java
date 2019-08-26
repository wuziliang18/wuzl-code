package org.wuzl.test.leetcode;

import java.util.List;
import java.util.function.Function;

public class PrintUtil {
	public static <T, R> void print(List<T> input, Function<T, R> funciton) {
		input.forEach(param -> {
			StringBuilder sb = new StringBuilder();
			sb.append("输入:").append(param).append(",输出:").append(funciton.apply(param));
			System.out.println(sb);
		});
	}
}
