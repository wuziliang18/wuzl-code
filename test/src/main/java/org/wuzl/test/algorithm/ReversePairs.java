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
        System.out.println(obj.reversePairs(new int[] { 7, 5, 6, 4 }));
        System.out.println(obj.reversePairs(new int[] { 1, 3, 5, 6, 4, 9, 2 }));
        System.out.println(obj.reversePairs(new int[] { 1, 6, 4, 9, 3, 5 }));

    }

    private int count = 0;

    // 没做出来太恶心了
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        mergeSort(nums, 0, nums.length - 1);
        return count;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        if (left == right - 1) {
            if (nums[left] > nums[right]) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
            return;
        }
        int mid = (right + left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        int leftTemp = left;
        int rightTemp = mid + 1;
        int[] tempArray = new int[right - left + 1];
        int index = 0;
        while (leftTemp <= mid && rightTemp <= right) {
            if (nums[leftTemp] > nums[rightTemp]) {
                tempArray[index++] = nums[rightTemp++];
            } else {
                tempArray[index++] = nums[leftTemp++];
            }
        }
        while (leftTemp <= mid) {
            tempArray[index++] = nums[leftTemp++];
        }
        while (rightTemp <= right) {
            tempArray[index++] = nums[rightTemp++];
        }
        for (int i = 0; i < tempArray.length; i++) {
            nums[left + i] = tempArray[i];
        }
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
