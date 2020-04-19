package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class TestPriorityQueue {
    public static void main(String[] args) {
        // 在一个数组中找到最大的m个数字
        int m = 10;
        List<Integer> rows = IntStream.range(1, 101).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        Collections.shuffle(rows);
        PriorityQueue<Integer> queue = new PriorityQueue<>(m);
        rows.stream().forEach(num -> {
            if (queue.size() < m) {
                queue.add(num);
            } else {
                if (queue.peek() < num) {
                    queue.remove();
                    queue.add(num);
                }
            }
        });
        queue.stream().forEach(num -> System.out.println(num));

    }
}
