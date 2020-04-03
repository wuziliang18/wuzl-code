package org.wuzl.test.algorithm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 滑动窗口的最大值
 * 
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * 
 * @author ziliang.wu
 *
 */
public class MaxSlidingWindowV2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MaxSlidingWindowV2().maxSlidingWindow(new int[] { 9,11 }, 2)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null) {
            return null;
        }
        if (nums.length < k || k <= 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        Deque<Integer> deque = new ArrayDeque<Integer>(k);
        deque.add(0);
        for (int i = 1; i < k - 1; i++) {// 初始化
            addAndClear(deque, nums, k, i);
        }
        int[] array = new int[nums.length - k + 1];
        int index = 0;
        for (int i = k - 1; i < nums.length; i++) {
            addAndClear(deque, nums, k, i);
            array[index++] = nums[deque.getFirst()];
        }
        return array;
    }

    private void addAndClear(Deque<Integer> deque, int[] nums, int k, int index) {
        while (!deque.isEmpty() && nums[deque.getLast()] <= nums[index]) {
            deque.removeLast();
        }
        if (!deque.isEmpty() && deque.getFirst() + k == index) {
            deque.remove();
        }
        deque.add(index);
    }
}
