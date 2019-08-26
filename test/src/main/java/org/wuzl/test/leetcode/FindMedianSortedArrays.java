package org.wuzl.test.leetcode;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 
 * 示例 1:
 * 
 * nums1 = [1, 3] nums2 = [2]
 * 
 * 则中位数是 2.0 示例 2:
 * 
 * nums1 = [1, 2] nums2 = [3, 4]
 * 
 * 则中位数是 (2 + 3)/2 = 2.5
 * 
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 
 * @author ziliang.wu
 *
 */
public class FindMedianSortedArrays {
	public static void main(String[] args) {
		// int[] nums1 = { 1, 3 };
		// int[] nums2 = { 2 };
		// int[] nums1 = { 1 };
		// int[] nums2 = { 4 };
		// int[] nums1 = { 1, 2 };
		// int[] nums2 = { 3,4 };
		//
		// int[] nums1 = { 1, 3, 5, 7, 9, 15 };
		// int[] nums2 = { 2, 11 ,12, 151 ,158 };
		int[] nums1 = { 1, 3, 5, 7, 9 };
		int[] nums2 = { 10, 11, 12, 151, 158 };
		System.out.println(solution(nums1, nums2));
	}
	//
	// // 使用二分法
	// public static double solutionV2(int[] nums1, int[] nums2) {
	// int length1 = nums1.length;
	// int length2 = nums2.length;
	// int maxLength = length1 + length2;
	// boolean findOne = maxLength % 2 != 0;
	// int findIndex = findOne ? (maxLength) + 1 / 2 : maxLength / 2;
	// int k = findIndex;
	// int start1 = 0;
	// int start2 = 0;
	// int find = 0;
	// while (true) {
	// k /= 2;
	// if (length1 < (start1 + k)) {
	//
	// }
	// }
	// return 0;
	// }

	public static double solution(int[] nums1, int[] nums2) {
		int length1 = nums1.length;
		int length2 = nums2.length;
		int maxLength = length1 + length2;
		boolean findOne = maxLength % 2 != 0;
		int findIndex = maxLength / 2;
		int endIndex = findOne ? findIndex : findIndex + 1;
		// if (nums1[length1 - 1] < nums2[length2 - 1]) {
		//
		// }
		int findValue = 0;
		int index1 = 0;
		int index2 = 0;
		int temp = 0;
		for (int i = 0; i <= endIndex; i++) {
			if (index1 == length1) {
				if (findIndex != 0) {
					temp = nums2[index2];
					continue;
				}
				int lack = findIndex - i;
				findValue = nums2[index2 - 1 + lack];
				temp = findOne ? findValue : nums2[index2 + lack];
				continue;
			}
			if (index2 == length2) {
				if (findIndex != 0) {
					temp = nums1[index1];
					continue;
				}
				int lack = findIndex - i;
				findValue = nums1[index1 - 1 + lack];
				temp = findOne ? findValue : nums2[index1 + lack];
				continue;
			}
			if (nums1[index1] > nums2[index2]) {
				temp = nums2[index2];
				index2++;
			} else {
				temp = nums1[index1];
				index1++;
			}
			if (i == findIndex - 1) {
				findValue = temp;
			}
		}
		return findOne ? temp : (findValue + temp) / 2.0;
	}
}
