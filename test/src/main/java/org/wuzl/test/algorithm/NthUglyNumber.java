package org.wuzl.test.algorithm;

/**
 * 丑数
 * 
 * 我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 * 
 * @author ziliang.wu
 *
 */
public class NthUglyNumber {
    public static void main(String[] args) {
        System.out.println(new NthUglyNumber().nthUglyNumber(10));
    }

    public int nthUglyNumber(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 3) {
            return n;
        }
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;
        int[] array = new int[n];
        array[0] = 1;
        for (int i = 1; i < n; i++) {
            int value = Math.min(Math.min(array[index2] * 2, array[index3] * 3),
                    Math.min(array[index3] * 3, array[index5] * 5));
            if (value == array[index2] * 2) {
                index2++;
            }
            if (value == array[index3] * 3) {
                index3++;
            }
            if (value == array[index5] * 5) {
                index5++;
            }
            array[i]=value;
        }

        return array[n - 1];
    }
}
