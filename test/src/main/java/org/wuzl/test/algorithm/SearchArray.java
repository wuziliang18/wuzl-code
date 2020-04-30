package org.wuzl.test.algorithm;

/**
 * 搜索旋转排序数组
 * 
 * @author ziliang.wu
 *
 */
public class SearchArray {
    public static void main(String[] args) {
        SearchArray obj = new SearchArray();
        System.out.println(obj.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 4));
        System.out.println(obj.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 1));
        System.out.println(obj.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 3));
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (right + left) >> 1;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                
            } else {

            }
        }
        return -1;
    }
}
