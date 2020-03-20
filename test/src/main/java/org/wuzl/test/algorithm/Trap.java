package org.wuzl.test.algorithm;

import java.util.Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 https://leetcode-cn.com/problems/trapping-rain-water/
 * 
 * @author ziliang.wu
 *
 */
public class Trap {
    public static void main(String[] args) {
        System.out.println(new Trap().trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));
    }

    public int trap(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        boolean isHeight = false;
        int temp = height[0];
        stack.push(height[0]);
        for (int i = 1; i < height.length; i++) {
            if (temp <= height[i]) {
                if (!isHeight) {
                    stack.pop();// 丢弃
                    stack.push(height[i]);
                    temp = height[i];
                    continue;
                }
                // 找到一个可以接的
                int low = stack.pop();
                int j = 1;
                int start = stack.pop();
                while (low == start) {
                    j++;
                    start = stack.pop();
                }
                count += (j * (start - low));
                if (!stack.isEmpty()) {
                    for (int index = 0; index < j - 1; index++) {
                        stack.push(start);
                    }
                }
                stack.push(height[i]);
                temp = height[i];
            } else {
                if (!isHeight) {
                    isHeight = true;
                } else {
                    isHeight = false;
                    temp = height[i];
                }
                stack.push(height[0]);
            }
        }

        // while(true) {
        //
        // }
        return count;
    }
}
