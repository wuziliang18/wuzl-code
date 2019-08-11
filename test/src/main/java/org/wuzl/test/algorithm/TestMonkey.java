package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMonkey {
	public static void main(String[] args) {
		List<int[]> list = new ArrayList<>();
		list.add(new int[] { 10, 4, 5, 12, 8 });
		list.add(new int[] { 10, 4, 3, 12, 8 });
		list.add(new int[] { 20, 15, 13, 12, 8 });
		list.add(new int[] { 1, 2, 13, 15, 18 });
		list.forEach(array -> {
			StringBuilder sb = new StringBuilder();
			sb.append("input: ").append(Arrays.toString(array));
			sb.append(" result:" + getPeach(array));
			System.out.println(sb);
		});
	}

	public static int getPeach(int[] tree) {

		int temp = 0;
		int maxPeachEveryTree =0;
		int result = tree[0]>0?1:0;
		for (int i = 0; i < tree.length; i++) {
			if (tree[i] >= maxPeachEveryTree) {
				maxPeachEveryTree = tree[i];
				temp++;
			} else {
				result = Math.max(result, temp);
				temp = tree[i]>0?1:0;
				maxPeachEveryTree = tree[i];
			}
		}
		result = Math.max(result, temp);
		return result;
	}
}
