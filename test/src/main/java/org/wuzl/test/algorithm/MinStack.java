package org.wuzl.test.algorithm;

import java.util.Stack;

public class MinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.min());
        ; // 返回 -3.
        minStack.pop();
        System.out.println(minStack.top());
        ; // 返回 0.
        System.out.println(minStack.min());
        ; // 返回 -2.
        minStack.pop();
        minStack.pop();
    }

    private Stack<Integer> stack = new Stack<Integer>();
    private int min = Integer.MAX_VALUE;

    public MinStack() {

    }

    public void push(int x) {
        min = Math.min(x, min);
        stack.push(min);
        stack.push(x);
    }

    public void pop() {
        stack.pop();
        int temp = stack.pop();
        if (temp == min) {
            if (stack.isEmpty()) {
                min = Integer.MAX_VALUE;
                return;
            }
            int pre = stack.pop();
            min = stack.peek();
            stack.push(pre);
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {

        return min;
    }
}
