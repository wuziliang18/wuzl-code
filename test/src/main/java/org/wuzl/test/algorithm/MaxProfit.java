package org.wuzl.test.algorithm;

public class MaxProfit {
    public static void main(String[] args) {
        MaxProfit obj = new MaxProfit();
        System.out.println(obj.maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
        System.out.println(obj.maxProfit(new int[] { 7, 6, 4, 3, 1 }));
    }

    public int maxProfit(int[] prices) {
        int max = 0;
        int temp = max;
        for (int i = 1; i < prices.length; i++) {
            int add = prices[i] - prices[i - 1];
            if (temp + add < 0) {
                temp = 0;
            } else {
                temp += add;
                if (temp > max) {
                    max = temp;
                }
            }

        }
        return max;
    }
}
