package org.wuzl.test.algorithm;

public class Search {
    public static void main(String[] args) {
        Search obj = new Search();
        System.out.println(obj.findIndex(new int[] { 2, 2 }, 3));
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int index = findIndex(nums, target);
        if (index < 0) {
            return 0;
        }
        int count = 1;
        for (int i = index - 1; i >= 0; i--) {
            if (nums[i] == target) {
                count++;
            }
        }
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;
            }
        }
        return count;
    }

    private int findIndex(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }
        return -1;
    }
}
