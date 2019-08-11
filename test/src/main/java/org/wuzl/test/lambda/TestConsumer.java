package org.wuzl.test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TestConsumer {
	public static void main(String[] args) {
		List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6);
		forEach(intList, (Integer i) -> System.out.println(i));// 简单输出
		forEach(intList, (Integer i) -> i = i++);// 没有改变
		System.out.println(intList);

		List<String> strList = Arrays.asList("1", "2", "3", "4", "5", "6", "7");
		forEach(strList, (String s) -> System.out.println(s));// 简单输出
		forEach(strList, (String s) -> {
			if ("5".equals(s)) {
				throw new RuntimeException("抛出异常");
			}
			s = s + "追加";
			System.out.println(s);

		});// 不可以改变
		System.out.println(strList);
	}

	public static <T> void forEach(List<T> list, Consumer<T> consumer) {
		for (T obj : list) {
			consumer.accept(obj);
		}
	}
}
