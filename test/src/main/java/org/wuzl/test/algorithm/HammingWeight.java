package org.wuzl.test.algorithm;

/**
 * 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
 * 
 * 链接：https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof
 * 
 * @author ziliang.wu
 *
 */
public class HammingWeight {
    public static void main(String[] args) {
        HammingWeight obj = new HammingWeight();
        // System.out.println(obj.hammingWeight(1));
        // System.out.println(obj.hammingWeight(9));
        // System.out.println(obj.hammingWeight(-9));
        // System.out.println(obj.hammingWeight(255));
        // System.out.println(obj.hammingWeight(Integer.MAX_VALUE));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.toBinaryString(-3));
        System.out.println(obj.hammingWeight(-3));
        System.out.println(obj.hammingWeight(Integer.MIN_VALUE));
        System.out.println(obj.hammingWeight(11));

    }

    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }

    public int hammingWeightV1(int n) {
        if (n == 0) {
            return 0;
        }
        int count = 0;
        boolean negative = false;
        if (n < 0) {
            n = -(n + 1);
            negative = true;
        }
        while (n != 0) {
            if (n % 2 != 0) {
                count++;
            }
            n = (n / 2);
        }

        return negative ? 32 - count : count;
    }
}
