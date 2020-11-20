package org.wuzl.test.algorithm;

/**
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 * 
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 * 
 * A.length >= 3 在 0 < i < A.length - 1 条件下，存在 i 使得： A[0] < A[1] < ... A[i-1] < A[i] A[i] > A[i+1] > ... > A[A.length -
 * 1]  
 * 
 * 链接：https://leetcode-cn.com/problems/valid-mountain-array
 * 
 * @author ziliang.wu
 *
 */
public class ValidMountainArray {
    public static void main(String[] args) {
        ValidMountainArray obj = new ValidMountainArray();
        System.out.println(obj.validMountainArray(new int[] { 1, 2, 1 }));
        System.out.println(obj.validMountainArray(new int[] { 2, 1, 2 }));
        System.out.println(obj.validMountainArray(new int[] { 3, 2, 1 }));
    }

    public boolean validMountainArray(int[] a) {
        if (a == null || a.length < 3) {
            return false;
        }
        boolean low = false;
        int pre = a[0];
        if (pre > a[1]) {
            return false;
        }
        for (int i = 1; i < a.length; i++) {
            int num = a[i];
            if (num >= pre) {
                if (low) {
                    return false;
                }
            } else {
                low = true;
            }
            pre = num;
        }
        return low;
    }
}
