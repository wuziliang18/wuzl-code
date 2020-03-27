package org.wuzl.test.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数据流中的中位数
 * 
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 
 * 链接：https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof
 * 
 * @author ziliang.wu
 *
 */
public class MedianFinder {
    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(1);
        obj.addNum(2);
        System.out.println(obj.findMedian());
        obj.addNum(3);
        System.out.println(obj.findMedian());
    }

    private final PriorityQueue<Integer> big = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return -o1.compareTo(o2);
        }
    });
    private final PriorityQueue<Integer> small = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    });

    public MedianFinder() {

    }

    public void addNum(int num) {
        if (big.isEmpty()) {
            big.add(num);
            return;
        }
        if (num < big.peek()) {
            big.add(num);
            if (big.size() > small.size()) {
                small.add(big.poll());
            }
            return;
        }
        small.add(num);
        if (small.size() > big.size()) {
            big.add(small.poll());
        }
    }

    public double findMedian() {
        if (small.size() == big.size()) {
            return (big.peek() + small.peek()) / 2.0;
        }
        if(small.size()>big.size()) {
            return small.peek();
        }
        return big.peek();
    }
}
