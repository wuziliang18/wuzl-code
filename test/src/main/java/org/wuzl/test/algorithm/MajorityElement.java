package org.wuzl.test.algorithm;

import java.util.Arrays;

public class MajorityElement {
    public static void main(String[] args) {
        System.out.println(majorityElement(new int[] { 3 }));
        System.out.println(majorityElement(new int[] { 3, 2, 3 }));
        System.out.println(majorityElement(new int[] { 2, 2, 1, 1, 1, 2, 2 }));

    }
    public static int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    public static int majorityElementV2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int num = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    count = 1;
                    num = nums[i];
                }
            }
        }
        return num;
    }

    public static int majorityElementV1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int limit = length / 2;
        Arrays.sort(nums);
        int count = 1;
        int index = 0;
        while (index < limit + 1) {
            int num = nums[index];
            count = 1;
            while (index < length - 1 && num == nums[index + 1]) {
                index++;
                if (++count > limit) {
                    return num;
                }
            }
            index++;
        }
        return -1;
    }
}
