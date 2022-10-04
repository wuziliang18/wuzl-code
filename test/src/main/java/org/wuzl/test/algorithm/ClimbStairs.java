package org.wuzl.test.algorithm;

import java.util.HashMap;
import java.util.Map;

public class ClimbStairs {
    public static void main(String[] args) {
        ClimbStairs obj = new ClimbStairs();
        System.out.println(obj.climbStairs(2));
        System.out.println(obj.climbStairs(3));
        System.out.println(obj.climbStairs(9));
    }

    Map<Integer, Integer> map = new HashMap<>();

    /**
     * 第二次写
     * 
     * @param n
     * @return
     */
    public int climbStairsV2(int n) {

        if (n <= 2) {
            return n;
        }
        int[] array = new int[n + 1];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int pre = 2;
        int prePre = 1;
        int value = 0;
        for (int i = 3; i <= n; i++) {
            value = pre + prePre;
            prePre = pre;
            pre = value;
        }
        return value;
    }

    public int climbStairs1(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        Integer count = map.get(n);
        if (count != null) {
            return count;
        }
        count = climbStairs(n - 1) + climbStairs(n - 2);
        map.put(n, count);
        return count;
    }
}
