package org.wuzl.test.algorithm;

/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * 
 * https://leetcode-cn.com/problems/subarray-sum-equals-k/
 * 
 * @author ziliang.wu
 *
 */
public class SubarraySum {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = 0;
            for (int j = i; j < nums.length; j++) {
                temp += nums[j];
                if (temp == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
