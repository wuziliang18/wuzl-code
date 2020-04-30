package org.wuzl.test.algorithm;

/**
 * 寻找旋转排序数组中的最小值
 * 
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * 
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 
 * 请找出其中最小的元素。
 * 
 * 你可以假设数组中不存在重复元素。
 * 
 * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
 * 
 * @author ziliang.wu
 *
 */
public class FindMin {
    public static void main(String[] args) {
        FindMin obj = new FindMin();
        System.out.println(obj.findMin(new int[] { 0, 1, 2, 4, 5, 6, 7 }));
        System.out.println(obj.findMin(new int[] { 4, 5, 6, 7, 0, 1, 2 }));
        System.out.println(obj.findMin(new int[] { 3,4,5,1,2 }));
    }

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums[0] < nums[nums.length - 1]) {
            return nums[0];
        }
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = (right + left) >> 1;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
