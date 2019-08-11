package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.List;

public class TestTree {
	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>();
		input.add(1);
		int count = 2;
		int preRownum = 1;
		for (int i = 1; i <= 5; i++) {
			for (int j = 0; j < 2 * preRownum; j++) {
				System.out.print(count+",");
				input.add(count++);
			}

			preRownum = 2 * preRownum;
			System.out.println("");
		}

	}
}
