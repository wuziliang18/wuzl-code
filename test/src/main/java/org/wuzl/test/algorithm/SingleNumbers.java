package org.wuzl.test.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中数字出现的次数
 * 
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * 
 * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
 * 
 * @author ziliang.wu
 *
 */
public class SingleNumbers {
    public int[] singleNumbers(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return nums;
        }
        int xorNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            xorNum ^= nums[i];
        }
        xorNum = -xorNum & xorNum;// 取最低位是1的位置
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & xorNum) == xorNum) {
                num1 ^= nums[i];
            } else {
                num2 ^= nums[i];
            }
        }
        return new int[] { num1, num2 };
    }

    public int singleNumberV2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer count = map.get(num);
            if (count == null) {
                map.put(num, 1);
                continue;
            }
            if (count == 2) {
                map.remove(num);
            } else {
                map.put(num, 2);
            }
        }
        return map.keySet().iterator().next();
    }
}
