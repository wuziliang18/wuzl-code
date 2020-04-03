package org.wuzl.test.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class MaxQueue {
    private final Queue<Integer> mainQueue = new LinkedList<Integer>();
    private final LinkedList<Integer> helperQueue = new LinkedList<Integer>();

    public MaxQueue() {

    }

    public int max_value() {
        return helperQueue.isEmpty() ? -1 : helperQueue.peek();
    }

    public void push_back(int value) {
        mainQueue.add(value);
        while (!helperQueue.isEmpty() && helperQueue.getLast() < value) {
            helperQueue.removeLast();
        }
        helperQueue.add(value);
    }

    public int pop_front() {
        if (mainQueue.isEmpty()) {
            return -1;
        }
        int value = mainQueue.poll();
        if (value == helperQueue.peek()) {
            helperQueue.poll();
        }
        return value;
    }
}
