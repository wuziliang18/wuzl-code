package org.wuzl.test.algorithm;

import java.util.Arrays;

/**
 * 分割等和子集
 * 
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 * 
 * @author ziliang.wu
 *
 */
public class CanPartition {
    public static void main(String[] args) {
        CanPartition obj = new CanPartition();
        System.out.println(obj.canPartition(new int[] { 1, 5, 11, 5 }));
        System.out.println(obj.canPartition(new int[] { 1, 2, 3, 5 }));
    }

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        boolean[] array = new boolean[sum + 1];
        array[0] = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum - nums[i]; j >= 0; j--) {
                if (array[j]) {
                    array[j + nums[i]] = true;
                }
            }
            if (array[sum]) {
                return true;
            }
        }
        return false;
    }
}
