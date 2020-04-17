package org.wuzl.test.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * 
 * 
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 
 * @author ziliang.wu
 *
 */
public class TwoSum1 {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(target - nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer value = map.get(nums[i]);
            if (value != null && value != i) {
                return new int[] { i, value };
            }
        }
        return new int[0];
    }
}
