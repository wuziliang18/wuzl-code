package org.wuzl.test.algorithm;

/**
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。 https://leetcode-cn.com/problems/maximum-product-subarray/
 * 
 * @author ziliang.wu
 *
 */
public class MaxProduct {
    public static void main(String[] args) {
        MaxProduct obj = new MaxProduct();
        System.out.println(obj.maxProduct(new int[] { -2 }));
        System.out.println(obj.maxProduct(new int[] { 2, 3, -2, 4 }));
        System.out.println(obj.maxProduct(new int[] { -2, 0, -1 }));
        System.out.println(obj.maxProduct(new int[] { 2, 3, -2, 4, -1 }));
    }

    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int max = Integer.MIN_VALUE;
        int maxTemp = 1;
        int minTemp = 1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num < 0) {
                int temp = maxTemp;
                maxTemp = minTemp;
                minTemp = temp;
            }
            maxTemp = Math.max(maxTemp * num, num);
            minTemp = Math.min(minTemp * num, num);
            max = Math.max(max, maxTemp);
        }
        return max;

    }
}
