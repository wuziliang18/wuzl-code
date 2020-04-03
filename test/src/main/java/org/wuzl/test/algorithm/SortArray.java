package org.wuzl.test.algorithm;

/**
 * 给你一个整数数组 nums，将该数组升序排列。
 * 
 * @author ziliang.wu
 *
 */
public class SortArray {
    public static void main(String[] args) {
        System.out.println(new SortArray().sortArray(new int[] { 5, 2, 3, 1 }));
        ;
    }

    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int min = 5000;
        int max = -5000;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int length = max - min + 1;
        int[] array = new int[length];
        for (int num : nums) {
            array[num - min]++;
        }
        int index = 0;
        for (int num = min; num <= max; num++) {
            int count = array[num - min];
            while (count-- > 0) {
                nums[index++] = num;
            }
        }
        return nums;
    }
}
