package org.wuzl.test.algorithm;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 
 * 示例:
 * 
 * 输入: [10,9,2,5,3,7,101,18] 输出: 4 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 
 * @author ziliang.wu
 *
 */
public class LengthOfLIS {
    public static void main(String[] args) {
        LengthOfLIS obj = new LengthOfLIS();
        System.out.println(obj.lengthOfLIS(new int[] { 10, 9, 2, 5, 3, 6, 101, 18 }));
        // System.out.println(obj.lengthOfLIS(new int[] { 1, 3, 6, 7, 9, 4, 10, 5, 6 }));

    }
    
    /**
     * 二分查找+动态规划
     * 
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] array = new int[nums.length];// 存储长度为k-1的最小节点
        // Arrays.fill(array, 1);
        int res = 0;
        for (int num : nums) {
            int i = 0;
            int j = res;
            while (i < j) {// 二分查找
                int mid = (i + j) / 2;
                if (num > array[mid]) {
                    i = mid + 1;
                } else {
                    j = mid - 1;
                }
            }
            array[i] = num;
            if (j == res) {
                res += 1;
            }
        }
        return res;
    }

    /**
     * 神奇的TreeSet
     * 
     * @param nums
     * @return
     */
    public int lengthOfLISV3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer gt = treeSet.ceiling(nums[i]);
            if (gt == null) {
                treeSet.add(nums[i]);
            } else {
                treeSet.remove(gt);
                treeSet.add(nums[i]);
            }
        }
        return treeSet.size();
    }

    /**
     * 暴力还是慢
     * 
     * @param nums
     * @return
     */
    public int lengthOfLISV2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 1;
        int[] array = new int[nums.length];
        int per = nums[0];
        int temp = 1;
        array[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (per < nums[i]) {
                temp++;
                max = Math.max(temp, max);
                array[i] = temp;
                per = nums[i];
                continue;
            }
            per = nums[i];
            if (i == 1) {
                array[i] = 1;
                temp = 1;
                continue;
            }
            temp = 1;
            // 向前查询
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    temp = Math.max(temp, nums[j] + 1);
                    break;
                }
            }
            array[i] = temp;
        }
        return max;
    }

    /**
     * 慢了
     * 
     * @param nums
     * @return
     */
    public int lengthOfLISV1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int max = 0;
        for (int num : nums) {
            int temp = 1;
            for (Map.Entry<Integer, Integer> entity : map.entrySet()) {
                if (entity.getKey() < num && temp < entity.getValue() + 1) {
                    temp = entity.getValue() + 1;
                    continue;
                }
            }
            map.put(num, temp);
            max = Math.max(temp, max);
        }
        return max;
    }
}
