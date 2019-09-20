package org.wuzl.test.algorithm;

/**
 * 在有序循环数组中找到指定的值
 * 
 * @author ziliang.wu
 *
 */
public class FindMidInRingSortArray {
	public int findMid(int[] array, int value) {
		int end = array.length - 1;
		int start = 0;
		int mid = end / 2;
		while (start<mid) {
			if (array[mid] == value) {
				return mid;
			}
			if(value<array[mid]&&array[start]<array[mid]&&array[start]<=value) {
				end=mid-1;
				continue;
			}
			if(value<array[mid]&&array[start]<array[mid]) {
				
			}
			
		}
		return -1;
	}

	public static void main(String[] args) {
		FindMidInRingSortArray obj = new FindMidInRingSortArray();
		int[] array = new int[] { 4, 5, 6,7, 1, 2, 3 };
//		int[] array = new int[] { 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 161, 178, 1211, 1999, 1, 2, 3 };
		for (int i = 0; i < array.length; i++) {
			int result = obj.findMid(array, array[i]);
			if (result != i) {
				System.out.println("errror:" + array[i] + " find result " + result);
			} else {
				System.out.println(array[i] + " find result " + array[result]);
			}
		}
		System.out.println(138 + " find result " + obj.findMid(array, 138));
	}
}
