package org.wuzl.test.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 前 K 个高频元素 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 * 
 * @author ziliang.wu
 *
 */
public class TopKFrequent {
    public static void main(String[] args) {
        TopKFrequent obj = new TopKFrequent();
        System.out.println(Arrays.toString(obj.topKFrequent(new int[] { 1, 1, 1, 2, 2, 3 }, 2)));
    }

    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        if (nums.length <= k) {
            return nums;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<Map.Entry<Integer, Integer>>(k,
                new Comparator<Map.Entry<Integer, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(entry);
                continue;
            }
            Map.Entry<Integer, Integer> top = priorityQueue.peek();
            if (top.getValue() < entry.getValue()) {
                priorityQueue.poll();
                priorityQueue.add(entry);
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> top = priorityQueue.poll();
            result[i] = top.getKey();
        }
        return result;
    }
}
