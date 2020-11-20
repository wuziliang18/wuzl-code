package org.wuzl.test.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 划分字母区间
 * 
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 * https://leetcode-cn.com/problems/partition-labels/
 * 
 * @author ziliang.wu
 *
 */
public class PartitionLabels {
    public static void main(String[] args) {
        PartitionLabels obj = new PartitionLabels();
        // System.out.println(obj.partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(obj.partitionLabels("aebbedaddc"));
    }

    public List<Integer> partitionLabels(String s) {
        if (s == null || s.length() == 0) {
            return Arrays.asList(0);
        }
        int[] minIndex = new int[26];
        int[] maxIndex = new int[26];
        Arrays.fill(minIndex, -1);
        Arrays.fill(maxIndex, -1);
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (minIndex[index] == -1) {
                minIndex[index] = i;
            }
            maxIndex[index] = i;
        }
        List<Integer> list = new LinkedList<Integer>();
        int i = 0;
        while (i < s.length()) {
            int index = s.charAt(i) - 'a';
            // 找到开始节点
            int start = minIndex[index];
            // 找到结束节点
            int end = maxIndex[index];
            for (int j = start + 1; j <= end - 1; j++) {
                // 查找中间的
                int tempIndex = s.charAt(j) - 'a';
                int tempMax = maxIndex[tempIndex];
                if (tempMax > end) {
                    end = tempMax;
                }
            }
            i = end + 1;
            list.add(end - start + 1);
        }
        return list;
    }
}
