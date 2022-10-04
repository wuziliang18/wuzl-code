package org.wuzl.test.algorithm;

public class MaxNum {
    public static void main(String[] args) {
        MaxNum obj = new MaxNum();
        System.out.println(obj.getMaxSum(new int[] { -2, 11, -4, 13, -5, 2 }));
        System.out.println(obj.getMaxSum(new int[] { -2, -11, -4, -13, -5, -1 }));
    }

    public int getMaxSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxSum = Integer.MIN_VALUE;
        int temp = 0;
        int maxNum = maxSum;
        boolean geZero = false;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num < 0) {
                maxSum = Math.max(maxSum, temp);
                if (temp + num <= 0) {
                    temp = 0;
                } else {
                    temp += num;
                }
                maxNum = Math.max(maxNum, num);
                continue;
            }
            geZero = true;
            temp += num;
        }
        if (!geZero) {
            return maxNum;
        }
        return maxSum;
    }
}
