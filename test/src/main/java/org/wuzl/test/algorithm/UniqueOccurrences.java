package org.wuzl.test.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * 
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 * 
 * https://leetcode-cn.com/problems/unique-number-of-occurrences/
 * 
 * @author ziliang.wu
 *
 */
public class UniqueOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return true;
        }
        Map<Integer, Integer> map = new HashMap<>(20001);// 保存每个数字出现的次数
        int[] countArray = new int[arr.length];// 槽位是单个数字出现的次数 值是出现的数值个数
        for (int num : arr) {
            int count = map.getOrDefault(num, 0);
            count++;
            map.put(num, count);
            if (count >= 1) {
                countArray[count - 1] = countArray[count - 1] - 1;
                countArray[count] = countArray[count] + 1;
            }
        }
        for (int count : countArray) {
            if (count > 1) {
                return false;
            }
        }
        return true;
    }
}
