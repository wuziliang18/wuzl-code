package org.wuzl.test.algorithm;

import java.util.Arrays;

/**
 * n个骰子的点数
 * 
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * 
 * https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/
 * 
 * @author ziliang.wu
 *
 */
public class TwoSumV2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TwoSumV2().twoSum(2)));
    }

    public double[] twoSum(int n) {
        if (n <= 0) {
            return new double[0];
        }
        int count = 6 * n;
        int[][] sumCount = new int[n][count];
        for (int i = 1; i <= 6; i++) {
            sumCount[0][i - 1] = 1;
        }
        for (int time = 2; time <= n; time++) {
            int maxNum = 6 * time - time + 1;
            for (int num = time; num <= maxNum; num++) {
                for (int i = 1; i <= 6; i++) {
                    if (num - i > 0) {
                        sumCount[time - 1][num - 1] = sumCount[time - 1][num - 1] + sumCount[time - 2][num - i - 1];
                    }
                }
            }
        }
        double[] result = new double[count];
        double all = Math.pow(6, n);
        for (int i = 0; i < 6 * n - n + 1; i++) {
            System.out.println(n + i - 1);
            result[i] = sumCount[n - 1][n + i - 1] / all;
        }
        return result;
    }
}
