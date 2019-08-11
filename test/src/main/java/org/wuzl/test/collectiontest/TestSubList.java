package org.wuzl.test.collectiontest;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.IntStream;

public class TestSubList {
	public static void main(String[] args) {
		List<Integer> sellerMemberIdList = IntStream.range(0, 89).boxed().collect(toList());
		int batchSize = 10;
		for (int i = 0; i < (sellerMemberIdList.size() + batchSize - 1) / batchSize; i++) {
			int start = batchSize * i;
			int end = start + batchSize > sellerMemberIdList.size() ? sellerMemberIdList.size() : start + batchSize;
			System.out.println(sellerMemberIdList.subList(start, end).stream().map(String::valueOf).collect(joining(",")));

		}
	}
}
