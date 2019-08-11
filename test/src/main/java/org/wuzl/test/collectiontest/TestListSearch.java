package org.wuzl.test.collectiontest;

import java.util.ArrayList;
import java.util.List;

public class TestListSearch {
	public static void main(String[] args) {
		int count = 1000;
		List<Integer> rows = new ArrayList<Integer>();
		for (int i = 0; i < 1000; i++) {
			rows.add(i);
		}
		// 开始查询
		for(int i=499;i<count;i++){
			System.out.println("查询id:"+i);
			System.out.println(searchDesc(rows, i, 30));
		}
	}

	public static List<Integer> search(List<Integer> result, int lastId, int num) {
		for (int i = result.size() - 1; i >= 0; i--) {
			if (result.get(i) <= lastId) {
				int end = result.size() - i > 1 + num ? num +i+1: result.size();
				result = result.subList(i + 1, end);
				return result;
			}
		}
		return null;
	}
	
	public static List<Integer> searchDesc(List<Integer> result, int lastId, int num) {
		for (int i = 0; i < result.size(); i++) {
			if (result.get(i) >= lastId) {
				int start = num < i ? i - num : 0;
				result = result.subList(start, i);
				return result;
			}
		}
		return null;
	}
}
