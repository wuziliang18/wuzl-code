package org.wuzl.test.algorithm;

import java.util.ArrayDeque;
import java.util.Arrays;

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
        System.out.println(Arrays.toString(maxSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return null;
        }
        if (k == 1) {
            return nums;
        }
        return null;
    }

}
