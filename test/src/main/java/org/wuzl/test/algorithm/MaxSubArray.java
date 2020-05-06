package org.wuzl.test.algorithm;

/**
 * 连续子数组的最大和
 * 
 * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。 要求时间复杂度为O(n)。
 * 
 * https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 * 
 * @author ziliang.wu
 *
 */
public class MaxSubArray {
    public static void main(String[] args) {
        MaxSubArray obj = new MaxSubArray();
        System.out.println(obj.maxSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
    }

    /**
     * 第二次做
     * 
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp += nums[i];
            max = Math.max(temp, max);
            if (temp < 0) {
                temp = 0;
            }
        }

        return max;
    }

    public int maxSubArrayV1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;

        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] > 0) {
                max = nums[i++];
                break;
            }
        }

        if (max <= 0) {
            for (int j = 0; j < nums.length; j++) {
                max = Math.max(max, nums[j]);
            }
        }

        int temp = max;
        for (; i < nums.length; i++) {
            temp += nums[i];
            if (temp > max) {
                max = temp;
                continue;
            }
            if (temp < 0) {
                temp = 0;
            }
        }
        return max;
    }
}
