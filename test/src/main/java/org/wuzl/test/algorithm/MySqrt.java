package org.wuzl.test.algorithm;

/**
 * x 的平方根
 * 
 * 实现 int sqrt(int x) 函数。
 * 
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 
 * @author ziliang.wu
 *
 */
public class MySqrt {
    public static void main(String[] args) {
        MySqrt obj = new MySqrt();
        System.out.println(obj.mySqrt(1));
        // System.out.println(obj.mySqrt(3));
        // System.out.println(obj.mySqrt(4));
        // System.out.println(obj.mySqrt(2147483647));
        // System.out.println(obj.mySqrt(6));
        // System.out.println(obj.mySqrt(7));
        // System.out.println(obj.mySqrt(36));
    }

    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int left = 1;
        int right = x;
        int mid = left / 2 + right / 2;
        while (true) {
            int temp = x / mid;
            if (temp == mid) {
                return mid;
            }
            if (mid < temp) {
                left = mid;
            } else {
                right = mid;
            }
            if (right - left <= 1) {
                return left;
            }
            mid = (left + right) / 2;
        }
    }
}
