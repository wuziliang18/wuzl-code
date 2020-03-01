package org.wuzl.test.algorithm;

import java.util.Arrays;

/**
 * 假设我们有几种不同币值的硬币v1， v2， ……， vn（单位是元）。如 果我们要支付w元，求最少需要多少个硬币。比如，我们有3种不同的硬币， 1元、 3元、 5元，我们要支付9元，最少需要3个硬币（3个3元的硬币）。
 * 
 * @author ziliang.wu
 *
 */
public class TestCoin {
    public static void main(String[] args) {
        // 默认从小到大 考虑加入排序
        for (int i = 1; i < 20; i++) {
            smallChange(new int[] { 1, 2, 3, 5 }, i);
            smallChange(new int[] { 1, 3, 5 }, i);
            smallChange(new int[] { 1, 2, 5 }, i);
            smallChange(new int[] { 2, 3 }, i);
            smallChange(new int[] { 2 }, i);
            smallChange(new int[] { 1, 2 }, i);
        }
//        smallChange(new int[] { 1, 2, 5 }, 9);
//        smallChange(new int[] { 1, 2 }, 19);

    }

    public static void smallChange(int[] coins, int change) {
        int[] result = new int[change + 1];
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= change; j++) {
                if (j % coins[i] == 0) {// 硬币从小到大排列 所以整除肯定是最大
                    result[j] = j / coins[i];
                    continue;
                }
                if (coins[i] > j) {
                    continue;
                }
                int count = j / coins[i];
                while (count >= 1) {
                    int padding = j - count * coins[i];
                    if (result[padding] > 0) {
                        count += result[padding];
                        if (count <= result[j] || result[j] == 0) {
                            result[j] = count;
                        }
                        break;
                    }
                    count--;
                }
            }
            // System.out.println(Arrays.toString(result));
        }
        // System.out.println(Arrays.toString(result));
        // System.out.println(result[change]);
        System.out.println("coins:" + Arrays.toString(coins) + ",change:" + change + ",reuslt:" + result[change]);
    }
}
