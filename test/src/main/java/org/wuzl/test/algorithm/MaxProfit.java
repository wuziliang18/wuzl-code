package org.wuzl.test.algorithm;

public class MaxProfit {
    public static void main(String[] args) {
        MaxProfit obj = new MaxProfit();
        System.out.println(obj.maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
        System.out.println(obj.maxProfit(new int[] { 7, 6, 4, 3, 1 }));
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int[] temp = new int[prices.length];
        int max = prices[prices.length - 1];
        temp[prices.length - 1] = max;
        for (int i = prices.length - 2; i >= 0; i--) {
            int price = prices[i];
            if (price < max) {
                temp[i] = max;
                continue;
            }
            temp[i] = price;
            max = price;
        }
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            result = Math.max(result, temp[i] - prices[i]);
        }
        return result;
    }

    /**
     * 更快
     * 
     * @param prices
     * @return
     */
    public int maxProfitV1(int[] prices) {
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
