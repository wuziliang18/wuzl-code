package org.wuzl.test.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author ziliang.wu
 *
 */
public class TwoSum {
	public static void main(String[] args) {
		int[] nums = { 2, 7, 8, 11, 15 };
		int target = 17;
		System.out.println(Arrays.toString(twoSum(nums, target)));
	}

	public static int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		Map<Integer, Integer> cache = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int other = target - nums[i];
			Integer otherIndex = cache.get(nums[i]);
			if (otherIndex != null) {
				result[0] = otherIndex;
				result[1] = i;
				return result;
			}
			cache.put(other, i);
		}
		return result;
	}
}
