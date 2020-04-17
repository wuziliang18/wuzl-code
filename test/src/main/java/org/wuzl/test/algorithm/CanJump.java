package org.wuzl.test.algorithm;

import java.util.Arrays;

/**
 * 跳跃游戏
 * 
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 
 * 判断你是否能够到达最后一个位置。
 * 
 * https://leetcode-cn.com/problems/jump-game/
 * 
 * @author ziliang.wu
 *
 */
public class CanJump {
    public static void main(String[] args) {
        CanJump obj = new CanJump();
        System.out.println(obj.canJump(new int[] { 1, 2 }));

        System.out.println(obj.canJump(new int[] { 2, 3, 1, 1, 4 }));
        System.out.println(obj.canJump(new int[] { 3, 2, 1, 0, 4 }));
        System.out.println(obj.canJump(new int[] { 0, 1, 2 }));
        System.out.println(obj.canJump(new int[] { 3, 0, 8, 2, 0, 0, }));
    }

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canJumpV1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        int length = nums.length;
        boolean[] visit = new boolean[length];
        return find(0, nums, visit);
    }

    private boolean find(int index, int[] nums, boolean[] visit) {
        if (index >= nums.length - 1 || visit[index]) {
            return false;
        }
        if (index + 1 + nums[index] >= nums.length) {
            return true;
        }
        visit[index] = true;
        for (int i = 1; i <= nums[index]; i++) {
            if (!visit[index + i] && find(index + i, nums, visit)) {
                return true;
            }
        }
        return false;
    }
}
