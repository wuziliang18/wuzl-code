package org.wuzl.test.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 * 
 * 返回使 A 中的每个值都是唯一的最少操作次数
 * 
 * https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique/
 * 
 * @author ziliang.wu
 *
 */
public class MinIncrementForUnique {
    public static void main(String[] args) {
        MinIncrementForUnique obj = new MinIncrementForUnique();
        // v2 正确
        System.out.println(obj.minIncrementForUnique(new int[] { 3, 2, 1, 2, 1, 7 }));
    }

    public int minIncrementForUnique(int[] a) {
        if (a == null || a.length <= 1) {
            return 0;
        }
        int count = 0;
        Arrays.sort(a);
        for (int i = 1; i < a.length; i++) {
            int value = a[i];
            if (value <= a[i - 1]) {
                count += (a[i - 1] + 1 - value);
                a[i] = a[i - 1] + 1;
            }
        }
        return count;
    }

    /**
     * 0 <= A.length <= 40000 0 <= A[i] < 40000 有这个前提使用通 错误版本 a>40000如果有的值是40000 会超过
     * 
     * @param a
     * @return
     */
    public int minIncrementForUniqueV3(int[] a) {
        if (a == null || a.length <= 1) {
            return 0;
        }
        int count = 0;
        boolean[] array = new boolean[40001];
        for (int value : a) {
            while (array[value]) {
                value++;
                count++;
            }
            array[value] = true;
        }
        return count;
    }

    /**
     * 不用set 正确 节省内存 但慢
     * 
     * @param a
     * @return
     */
    public int minIncrementForUniqueV2(int[] a) {
        if (a == null || a.length <= 1) {
            return 0;
        }
        int count = 0;
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            int value = a[i];
            int index = i + 1;
            while (index < a.length && value >= a[index]) {
                index++;
                count++;
                value++;
            }
        }
        return count;
    }

    /**
     * 超时
     * 
     * @param a
     * @return
     */
    public int minIncrementForUniqueV1(int[] a) {
        if (a == null || a.length <= 1) {
            return 0;
        }
        int count = 0;
        Arrays.sort(a);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            int value = a[i];
            while (set.contains(value)) {
                value++;
                count++;
            }
            set.add(value);
        }
        return count;
    }
}
