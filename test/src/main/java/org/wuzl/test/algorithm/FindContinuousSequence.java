package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 和为s的连续正数序列
 * 
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * 
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * 
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
 * 
 * @author ziliang.wu
 *
 */
public class FindContinuousSequence {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindContinuousSequence().findContinuousSequence(9)));
    }

    public int[][] findContinuousSequence(int target) {
        if (target <= 2) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i < target - 1; i++) {
            int right = i + 1;
            int sum = i;
            while (right < target - 1) {
                sum += right;
                if (sum == target) {
                    List<Integer> list = new ArrayList<>(right - i + 1);
                    for (int j = i; j <= right; j++) {
                        list.add(j);
                    }
                    result.add(list);
                    break;
                }
                if (sum > target) {
                    break;
                }
                right++;
            }
        }
        int[][] resutltArray = new int[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            List<Integer> temp = result.get(i);
            int[] tempArray = new int[temp.size()];
            for (int j = 0; j < temp.size(); j++) {
                tempArray[j] = temp.get(j);
            }
            resutltArray[i]=tempArray;
        }
        return resutltArray;
    }
}
