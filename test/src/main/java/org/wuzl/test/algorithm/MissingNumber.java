package org.wuzl.test.algorithm;

/**
 * 0～n-1中缺失的数字
 * 
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * 
 * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
 * 
 * @author ziliang.wu
 *
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == mid) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
}
