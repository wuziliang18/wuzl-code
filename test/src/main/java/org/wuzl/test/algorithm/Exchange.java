package org.wuzl.test.algorithm;

import java.util.Arrays;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 * 
 * @author ziliang.wu
 *
 */
public class Exchange {
    public static void main(String[] args) {
        Exchange obj = new Exchange();
        System.out.println(Arrays.toString(obj.exchange(new int[] { 1, 2, 3, 4 })));
    }

    public int[] exchange(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int start = 0;// 奇数
        int end = nums.length - 1;// 偶数
        while (start < end) {
            if (nums[start] % 2 != 0) {
                start++;
                continue;
            }
            if (nums[end] % 2 == 0) {
                end--;
                continue;
            }
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
        return nums;
    }
}
