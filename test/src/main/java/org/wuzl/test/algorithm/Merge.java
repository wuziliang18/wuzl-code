package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间 给出一个区间的集合，请合并所有重叠的区间。 https://leetcode-cn.com/problems/merge-intervals/
 * 
 * @author ziliang.wu
 *
 */
public class Merge {
    public static void main(String[] args) {
        System.out
                .println(Arrays.toString(new Merge().merge(new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } })));
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }

        });
        int[] temp = intervals[0];
        int[][] result = new int[intervals.length][2];
        int index = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] array = intervals[i];
            if (temp[1] >= array[0]) {
                temp[1] = Math.max(array[1], temp[1]);
            } else {
                result[index++] = temp;
                temp = array;
            }
        }
        result[index++] = temp;
        if (index == intervals.length) {
            return result;
        }
        intervals = new int[index][1];
        for (int i = 0; i < index; i++) {
            intervals[i] = result[i];
        }
        return intervals;
    }
}
