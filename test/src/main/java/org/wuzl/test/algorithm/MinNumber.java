package org.wuzl.test.algorithm;

import java.util.Arrays;

public class MinNumber {
    public static void main(String[] args) {
        MinNumber obj = new MinNumber();
        System.out.println(obj.minNumber(new int[] { 10, 2 }));
        System.out.println(obj.minNumber(new int[] { 3, 30, 34, 5, 9 }));
        System.out.println(obj.minNumber(new int[] { 3, 30, 31, 321, 34, 5, 9 }));
    }

    public String minNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        String[] array = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            array[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(array, (s1, s2) -> {
            return (s1 + s2).compareTo((s2 + s1));
        });
        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            sb.append(s);
        }
        return sb.toString();
    }
}
