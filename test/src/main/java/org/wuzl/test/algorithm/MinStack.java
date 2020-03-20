package org.wuzl.test.algorithm;

import java.util.Stack;

public class MinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.min(); // 返回 -3.
        minStack.pop();
        minStack.top(); // 返回 0.
        System.out.println(minStack.min());
        ; // 返回 -2.
    }

    private Stack<Integer> stack = new Stack<Integer>();
    private Stack<Integer> temp = new Stack<Integer>();

    public MinStack() {

    }

    public void push(int x) {
        while (!stack.isEmpty()) {
            if (stack.peek() < x) {
                temp.push(stack.pop());
                continue;
            }
            break;
        }
        stack.push(x);
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return stack.peek();
    }
}
