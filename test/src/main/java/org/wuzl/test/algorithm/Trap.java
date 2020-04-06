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
        // System.out.println(new Trap().trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));
        System.out.println(new Trap().trap(new int[] { 2, 1, 0, 2 })); 
    }

    public int trap(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int h = height[stack.pop()];
                if (stack.isEmpty()) {
                    break;
                }
                int preIndex = stack.peek();
                int length = i - preIndex - 1;
                sum += (length * (Math.min(height[i], height[preIndex]) - h));

            }
            stack.push(i);

        }
        return sum;
    }
}
