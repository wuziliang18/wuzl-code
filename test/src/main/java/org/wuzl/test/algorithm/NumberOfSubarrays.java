package org.wuzl.test.algorithm;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 统计「优美子数组」
 * 
 * 给你一个整数数组 nums 和一个整数 k。
 * 
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * 
 * 请返回这个数组中「优美子数组」的数目。
 * 
 * 链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays
 * 
 * @author ziliang.wu
 *
 */
public class NumberOfSubarrays {
    public static void main(String[] args) {
        NumberOfSubarrays obj = new NumberOfSubarrays();
        System.out.println(obj.numberOfSubarrays(new int[] { 1, 1, 2, 1, 1 }, 3));
        System.out.println(obj.numberOfSubarrays(new int[] { 2, 2, 2, 1, 2, 2, 1, 2, 2, 2 }, 2));
        System.out.println(obj.numberOfSubarrays(new int[] { 1, 1, 1, 1 }, 1));
    }

    public int numberOfSubarrays(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k < 1 || nums.length < k) {
            return 0;
        }
        int count = 0;
        int left = 0;
        int right = 0;
        int tempCount = 0;
        while (right < nums.length) {
            if (nums[right++] % 2 == 1) {
                tempCount++;
                if (tempCount == k) {
                    // 左边
                    int leftCount = 0;
                    while (nums[left] % 2 == 0) {
                        leftCount++;
                        left++;
                    }
                    left++;
                    int rightCount = 0;
                    while (right < nums.length && nums[right] % 2 == 0) {
                        right++;
                        rightCount++;
                    }
                    tempCount--;
                    count += (leftCount + 1) * (rightCount + 1);
                }
            }
        }
        return count;
    }

    /**
     * 方案行不通 不能通过计算获取偶数 个数 考虑根据下标计算出偶数个数
     *
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarraysV1(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k < 1 || nums.length < k) {
            return 0;
        }
        int count = 0;
        Deque<Integer> queue = new LinkedList<Integer>();
        int start = -1;
        int end = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {// 奇数
                queue.add(i);
                if (queue.size() > k) {// 下次的边界
                    count += processCount(queue.pollFirst(), queue.getLast(), start, end);
                    start = end;
                }
            } else {
                if (start == -1) {
                    start = i;
                } else {
                    end = i;
                }

            }
        }
        if (queue.size() >= k) {// 下次的边界
            count += processCount(queue.getFirst(), queue.getLast(), start, end);
        }
        return count;
    }

    private int processCount(int left, int right, int start, int end) {
        if (start == -1) {
            return 1;
        }
        int count = 1;// 自己是 一个
        int leftLength = Math.max(left - start, 0);
        int rightLength = Math.max(end - right, 0);

        return count + leftLength + rightLength + leftLength * rightLength;
    }
}
