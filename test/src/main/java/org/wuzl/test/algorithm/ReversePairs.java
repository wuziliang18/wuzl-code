package org.wuzl.test.algorithm;

import java.util.Arrays;

/**
 * 数组中的逆序对
 * 
 * 
 * 
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * 
 * @author ziliang.wu
 *
 */
public class ReversePairs {
    public static void main(String[] args) {
        ReversePairs obj = new ReversePairs();
        // System.out.println(obj.reversePairs(new int[] { 7, 5, 6, 4 }));
        // System.out.println(obj.reversePairs(new int[] { 1, 3, 5, 6, 4, 9, 2 }));
        // System.out.println(obj.reversePairs(new int[] { 1, 6, 4, 9, 3, 5 }));
        System.out.println(obj.reversePairs(new int[] { 1, 3, 2, 3, 1 }));

    }

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }
        int mid = (end + start) >> 1;
        return mergeSort(nums, start, mid) + mergeSort(nums, mid + 1, end) + merge(nums, start, end, mid);
    }

    private int merge(int[] nums, int start, int end, int mid) {
        int count = 0;
        int left = start;
        int ritght = mid + 1;
        int index = 0;
        int[] array = new int[end + 1 - start];
        while (left <= mid && ritght <= end) {
            if (nums[left] > nums[ritght]) {
                count += mid - left + 1;
            }
            if (nums[left] <= nums[ritght]) {
                array[index++] = nums[left++];
            } else {
                array[index++] = nums[ritght++];
            }
        }
        while (left <= mid) {
            array[index++] = nums[left++];
        }
        while (ritght <= end) {
            array[index++] = nums[ritght++];
        }
        for (int i = 0; i < array.length; i++) {
            nums[start + i] = array[i];
        }
        return count;
    }

    /**
     * 超时
     * 
     * @param nums
     * @return
     */
    public int reversePairsV1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[i]) {
                    count++;
                }
            }
        }
        return count;
    }
}
