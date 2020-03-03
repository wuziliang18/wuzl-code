package org.wuzl.test.algorithm;

/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * 
 * https://leetcode-cn.com/problems/first-missing-positive/
 * 
 * @author ziliang.wu
 *
 */
public class FirstMissingPositive {
    public static void main(String[] args) {
        // System.out.println(firstMissingPositiveV2(new int[] {}));
        // System.out.println(firstMissingPositiveV2(new int[] { 1 }));
        // System.out.println(firstMissingPositiveV2(new int[] { 2 }));
        // System.out.println(firstMissingPositiveV2(new int[] { 1, 2, 0 }));
        System.out.println(firstMissingPositiveV2(new int[] { 3, 4, -1, 1 }));
        System.out.println(firstMissingPositiveV2(new int[] { 7, 8, 9, 11, 12 }));
        System.out.println(firstMissingPositiveV2(new int[] { 1, 5, 15, 6, 7, 2, 3, 4, 18, 199 }));
        System.out.println(firstMissingPositiveV2(new int[] { 8, 1, 5, 15, 6, 7, 2, 3, 4, 18, 199 }));
    }

    public static int firstMissingPositiveV2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int length = nums.length;
        boolean has1 = false;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (num == 1) {
                has1 = true;
                continue;
            }
            if (num > length || num <= 0) {
                nums[i] = 1;
                continue;
            }
        }
        if (!has1) {
            return 1;
        }
        for (int i = 0; i < length; i++) {
            int num = Math.abs(nums[i]);
            nums[num - 1] = -Math.abs(nums[num - 1]);// 用负数是用了标记
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return length + 1;
    }

    public static int firstMissingPositiveV1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int length = nums.length;
        for (int i = 0; i < length;) {
            int num = nums[i];
            if (num > length) {
                continue;
            }
            if (num > 0 && num <= length && num != i + 1) {
                // 交换
                nums[i] = nums[num - 1];
                nums[num - 1] = num;
                continue;
            }
            i++;
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return length + 1;
    }
}
