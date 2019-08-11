package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 排序
 * 
 * @author ziliang.wu
 *
 */
public class TestSort {
	private static final Integer[] baseArray = { 81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15 };

	public static void main(String[] args) {
		System.out.println("输入:" + Arrays.toString(baseArray));
		// outArray(shellSort(baseArray.clone()));
		List<Integer> list = new ArrayList<>(Arrays.asList(baseArray));
		quickSort(list);
		System.out.println(list);

	}

	private static void outArray(int[] array) {
		System.out.println(Arrays.toString(array));
	}

	/**
	 * 插入排序
	 * 
	 * @param array
	 * @return
	 */
	public static int[] insertionSort(int[] array) {
		int j;
		for (int p = 1; p < array.length; p++) {
			int temp = array[p];
			// 没进循环的话j--失效
			for (j = p; j > 0 && temp < array[j - 1]; j--) {
				array[j] = array[j - 1];
			}
			array[j] = temp;
		}
		return array;
	}

	/**
	 * 希尔排序
	 * 
	 * @param array
	 * @return
	 */
	public static int[] shellSort(int[] array) {
		int j;
		for (int gap = array.length; gap > 0; gap = gap / 2) {
			for (int i = gap; i < array.length; i++) {
				int temp = array[i];
				for (j = i; j >= gap && temp < array[j - gap]; j = j - gap) {
					array[j] = array[j - gap];
				}
				array[j] = temp;
			}
		}
		return array;
	}

	/**
	 * 快速排序
	 * 
	 * @param rows
	 */
	public static void quickSort(List<Integer> rows) {
		if (rows.size() <= 1) {
			return;
		}
		List<Integer> smaller = new ArrayList<Integer>();
		List<Integer> same = new ArrayList<>();
		List<Integer> lager = new ArrayList<>();
		int choseItem = rows.get(rows.size() / 2);
		for (Integer item : rows) {
			if (item < choseItem) {
				smaller.add(item);
			} else if (item > choseItem) {
				lager.add(item);
			} else {
				same.add(item);
			}
		}
		quickSort(smaller);
		quickSort(lager);
		rows.clear();
		rows.addAll(smaller);
		rows.addAll(same);
		rows.addAll(lager);
	}
}
