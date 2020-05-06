package org.wuzl.test.algorithm;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * 
 * @author ziliang.wu
 *
 */
public class Jump {
    public static void main(String[] args) {
        Jump obj = new Jump();
        // System.out.println(obj.jump(new int[] { 2, 3, 1, 1, 4 }));
        // System.out.println(obj.jump(new int[] { 4, 3, 1, 1, 4 }));
        System.out.println(obj.jump(new int[] { 1, 2 }));
        System.out.println(obj.jump(new int[] { 1, 1, 1, 1 }));
    }

    public int jump(int[] nums) {

        return 0;
    }

    public int jumpV1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int[] cache = new int[nums.length];
        if (nums[0] >= nums.length - 1) {
            return 1;
        }
        for (int i = nums[0]; i > 0; i--) {
            cache[i] = 1;
        }

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            int index = cache[i];
            if (index > 0) {
                for (int j = num; j > 0; j--) {
                    if (j + i >= nums.length - 1) {
                        return index + 1;
                    }
                    cache[j + i] = cache[j + i] > 0 ? Math.min(cache[j + i], index + 1) : index + 1;
                }
            }
        }
        return -1;
    }
}
