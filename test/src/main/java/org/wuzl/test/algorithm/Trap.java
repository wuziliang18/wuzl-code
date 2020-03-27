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
        int count = 0;
        boolean isHeight = false;
        int pre = height[0];
        stack.push(height[0]);
        for (int i = 1; i < height.length; i++) {
            if (pre <= height[i]) {
                if (!isHeight) {
                    if (stack.size() == 1) {
                        stack.pop();// 开头
                    } else {
                        int j = 0;
                        while (pre == stack.peek()) {
                            j++;
                            stack.pop();
                        }
                        if (!stack.isEmpty()) {
                            int preStart = stack.pop();
                            preStart = Math.min(preStart, height[i]);
                            count += (j * (preStart - pre));
                        }
                    }
                    stack.push(height[i]);
                    pre = height[i];
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
                start = Math.min(start, height[i]);
                count += (j * (start - low));
                if (!stack.isEmpty()) {
                    for (int index = 0; index < j + 1; index++) {
                        stack.push(start);
                    }
                }
                stack.push(height[i]);
                pre = height[i];
                isHeight = false;
            } else {
                isHeight = true;
                stack.push(height[i]);
                pre = height[i];
            }
        }
        return count;
    }
}
