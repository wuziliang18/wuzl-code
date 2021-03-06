package org.wuzl.test.algorithm;

/**
 * 
 * 盛最多水的容器
 * 
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i,
 * 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 
 * @author ziliang.wu
 *
 */
public class MaxArea {
    public static void main(String[] args) {
        MaxArea obj = new MaxArea();
        System.out.println(obj.maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }));
    }

    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int min = Math.min(height[left], height[right]);
            max = Math.max((right - left) * min, max);
            
            if (min == height[left]) {
                left++;
            } else {
                right--;
            }

        }
        return max;
    }

    public int maxAreaV1(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max((j - i) * Math.min(height[i], height[j]), max);
            }
        }
        return max;
    }
}
