
package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 
 * 注意：答案中不可以包含重复的三元组
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/3sum 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * https://leetcode-cn.com/problems/3sum/
 * 
 * @author ziliang.wu
 *
 */
public class ThreeNumSum {
    public static void main(String[] args) {
        int[] nums = { -1, -2, -1, 0, 1, 2, -1, -4 };

        System.out.println(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int first = nums[i];
            if (first > 0) {// 排序后后边不会有小于0的了
                break;
            }
            if (i != 0 && first == nums[i - 1]) {
                continue;
            }
            int start = i + 1;
            int end = length - 1;
            while (start < end) {
                int sec = nums[start];
                int third = nums[end];
                int sum = first + sec + third;
                if (sum == 0) {
                    result.add(Arrays.asList(first, sec, third));
                    while (start < end) {
                        if (nums[start] == nums[start + 1]) {
                            start++;
                            continue;
                        }
                        if (nums[end] == nums[end - 1]) {
                            end--;
                            continue;
                        }
                        break;
                    }
                    start++;
                    end--;
                } else if (sum > 0) {
                    end--;
                } else {
                    start++;
                }

            }
        }
        return result;
    }
   
}
