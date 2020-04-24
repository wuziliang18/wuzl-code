package org.wuzl.test.algorithm;

/**
 * 硬币
 * 
 * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
 * 
 * https://leetcode-cn.com/problems/coin-lcci/
 * 
 * @author ziliang.wu
 *
 */
public class WaysToChange {
    public static void main(String[] args) {
        WaysToChange obj = new WaysToChange();
        System.out.println(obj.waysToChange(10));
    }

    public int waysToChange(int n) {
        if (n <= 1) {
            return n;
        }
        int[] array = new int[n + 1];
        int[] coins = new int[] { 1, 5, 10, 25 };
        array[0] = 1;
        for (int coin : coins) {
            for (int i = 1; i <= n; i++) {
                if (i >= coin) {
                    array[i] = (array[i] + array[i - coin]) % 1000000007;
                }
            }
        }

        return array[n];
    }
}
