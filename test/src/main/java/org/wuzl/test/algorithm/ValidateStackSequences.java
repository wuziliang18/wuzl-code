package org.wuzl.test.algorithm;

import java.util.Stack;

public class ValidateStackSequences {
    public static void main(String[] args) {
        ValidateStackSequences obj = new ValidateStackSequences();
        System.out.println(obj.validateStackSequences(new int[] { 1, 2, 3, 4, 5 }, new int[] { 4, 5, 3, 2, 1 }));
        System.out.println(obj.validateStackSequences(new int[] { 1, 2, 3, 4, 5 }, new int[] { 4, 3, 5, 1, 2 }));
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null) {
            return popped == null;
        }
        if (pushed.length != popped.length) {
            return false;
        }
        if (pushed.length == 0) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty()) {
                if (stack.peek() == popped[j]) {
                    stack.pop();
                    j++;
                    continue;
                }
                break;
            }
        }
        return stack.isEmpty();
    }
}
