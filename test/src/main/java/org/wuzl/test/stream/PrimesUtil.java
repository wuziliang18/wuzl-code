package org.wuzl.test.stream;

/*
 * 利用延迟队列不断生成质数
 */
public class PrimesUtil {
	public static LazyList<Integer> from(int n) {
		return new LazyList<Integer>(n, () -> {
			System.out.println("生成n  " + (n + 1));// 方便查看
			return from(n + 1);
		});
	}

	public static LazyList<Integer> primes(LazyList<Integer> numbers) {
		System.out.println("primes 方法" + numbers.getHead());
		return new LazyList<Integer>(numbers.getHead(), () -> {
			System.out.println("primes " + numbers.getHead());// 方便查看
			return primes(numbers.tail().filter(n -> n % numbers.getHead() != 0));
		});
	}

	public static void main(String[] args) {
		// LazyList<Integer> lazyList = from(2);
		// for (int i = 0; i < 10; i++) {
		// lazyList = lazyList.tail();
		// System.out.println(lazyList.getHead());
		// }
		LazyList<Integer> numbers = from(2);// 没有任何创建
		System.out.println("from");
		// int two = primes(numbers).getHead();
		// int three = primes(numbers).tail().getHead();
		// int five = primes(numbers).tail().tail().getHead();
		// System.out.println(five);
		numbers = primes(numbers);// 没有任何操作 也就是没有对象生成
		System.out.println("primes1>>>");
		numbers = numbers.tail();
		System.out.println("tail1>>>");
		numbers = numbers.tail();
		System.out.println("tail2>>>");
		System.out.println(numbers.getHead());
		System.out.println("end>>>");
	}
}
