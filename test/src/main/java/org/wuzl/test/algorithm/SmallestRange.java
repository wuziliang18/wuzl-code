package org.wuzl.test.algorithm;

import java.util.List;

/**
 * 最小区间
 * 
 * 你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 * 
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 * 
 * 示例 1:
 * 
 * 输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]] 输出: [20,24] 解释: 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。 列表 2：[0,
 * 9, 12, 20]，20 在区间 [20,24] 中。 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/smallest-range-covering-elements-from-k-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author lvyue
 *
 */
public class SmallestRange {
    public int[] smallestRange(List<List<Integer>> nums) {
        if (nums.isEmpty()) {
            return new int[0];
        }
        int start = Integer.MAX_VALUE;
        int end = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> list = nums.get(i);
            // 取结尾最小的
            int temp = Math.min(start, list.get(list.size() - 1));
            if (temp != start) {
                start = temp;
                index = i;
            }
        }
        /**
         * TODO
         * 取所有比start大于等于的下一个值和最后一个值
         * 从上一步中筛选出最大且比所有最后一个值小于等于的
         */
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> list = nums.get(i);
            // 取比start大的最小的
            for (int num : list) {
                if ((num == start && i != index) || num > start) {
                    end = Math.min(end, num);
                    continue;
                }
            }
        }
        return new int[] { start, end };
    }
}
