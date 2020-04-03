package org.wuzl.test.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 * 
 * @author ziliang.wu
 *
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 1) {
            return null;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left] + nums[right];
            if (temp == target) {
                return new int[] { nums[left], nums[right] };
            }
            if (temp < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }

    public int[] twoSumV1(int[] nums, int target) {
        if (nums == null || nums.length <= 1) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer value = map.get(num);
            if (value != null) {
                return new int[] { num, value };
            }
            map.put(target - num, num);
        }
        return null;
    }
}
