package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        int[] nums = { -1, 0, 1, 2, -1, -4 };

        System.out.println(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        return result;
    }
}
