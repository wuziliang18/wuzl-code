package org.wuzl.test.algorithm;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 
 * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 * 
 * @author ziliang.wu
 *
 */
public class NumWays {
    public static void main(String[] args) {
        NumWays obj = new NumWays();
        System.out.println(obj.numWays(0));
        System.out.println(obj.numWays(1));
        System.out.println(obj.numWays(2));
        System.out.println(obj.numWays(7));
        System.out.println(obj.numWays(46));
    }

    public int numWays(int n) {
        if (n <= 0) {
            return 1;
        }
        if (n <= 3) {
            return n;
        }
        int pre = 1;
        int result = 2;

        for (int i = 3; i <= n; i++) {
            int temp = pre + result;
            pre = result;
            result = temp % 1000000007;
        }
        return result;
    }
}
