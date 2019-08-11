package org.wuzl.test.stream;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class TestSum {
	public static void main(String[] args) {
		System.out.println(measureSumPerf(TestSum::parallelSumV2, 10_000_000));
	}

	public static long measureSumPerf(Function<Long, Long> adder, long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Result: " + sum);
			if (duration < fastest)
				fastest = duration;
		}
		return fastest;
	}
	/**
	 * 2 去除了拆箱操作
	 * 
	 * @param n
	 * @return
	 */
	public static long parallelSumV2(long n) {
		return LongStream.rangeClosed(1, n).parallel().reduce(0l, Long::sum);
	}
	/**
	 * 181
	 * 
	 * @param n
	 * @return
	 */
	public static long parallelSum(long n) {
		return Stream.iterate(1l, i -> i + 1).limit(n).parallel().reduce(0l, Long::sum);
	}

	/**
	 * 5 去除了拆箱操作
	 * 
	 * @param n
	 * @return
	 */
	public static long sequentialSumV2(long n) {
		return LongStream.rangeClosed(1, n).reduce(0l, Long::sum);
	}

	/**
	 * 103
	 * 
	 * @param n
	 * @return
	 */
	public static long sequentialSum(long n) {
		return Stream.iterate(1l, i -> i + 1).limit(n).reduce(0l, Long::sum);
	}

	/**
	 * 3
	 * 
	 * @param n
	 * @return
	 */
	public static long iteratorSum(long n) {
		long result = 0;
		for (long i = 0; i < n; i++) {
			result += i;
		}
		return result;
	}
}
