package org.wuzl.test.algorithm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 
 * 返回滑动窗口中的最大值。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/sliding-window-maximum 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author ziliang.wu
 *
 */
public class MaxSlidingWindow {
    public static void main(String[] args) {
        MaxSlidingWindow obj = new MaxSlidingWindow();
        System.out.println(Arrays.toString(obj.maxSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return null;
        }
        if (k == 1) {
            return nums;
        }
        Deque<Integer> deque = new ArrayDeque<>(k);
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < k - 1; i++) {// 初始化队列
            cleanDeque(deque, nums, i, k);
            deque.add(i);
        }
        int resultIndex = 0;
        for (int i = k - 1; i < nums.length; i++) {
            cleanDeque(deque, nums, i, k);
            deque.add(i);
            result[resultIndex++] = nums[deque.getFirst()];
        }
        return result;
    }

    private void cleanDeque(Deque<Integer> deque, int[] nums, int index, int k) {
        if (deque.isEmpty()) {
            return;
        }
        if (deque.getFirst() == index - k - 1) {// 上一步最大的是上个窗口最后值
            deque.removeFirst();
        }
        int newValue = nums[index];
        while (!deque.isEmpty() && nums[deque.getLast()] <= newValue) {
            deque.removeFirst();
        }
    }

}
