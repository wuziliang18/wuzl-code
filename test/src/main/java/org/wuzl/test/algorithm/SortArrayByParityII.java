package org.wuzl.test.algorithm;

import java.util.Arrays;

/**
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * 
 * 你可以返回任何满足上述条件的数组作为答案。
 * 
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 * 
 * @author ziliang.wu
 *
 */
public class SortArrayByParityII {
    public static void main(String[] args) {
        SortArrayByParityII obj = new SortArrayByParityII();
        int[] a = new int[] { 4, 2, 5, 7 };
        System.out.println(Arrays.toString(obj.sortArrayByParityII(a)));
    }

    public int[] sortArrayByParityII(int[] a) {
        if (a == null || a.length == 0 || a.length % 2 != 0) {
            return a;
        }
        int i = 0;
        int j = 1;
        while (i < a.length - 1 && j < a.length) {
            if (a[i] % 2 == 0) {
                i += 2;
                continue;
            }

            if (a[j] % 2 != 0) {
                j += 2;
                continue;
            }

            // 交换
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 2;
            j += 2;
        }

        return a;
    }
}
