package org.wuzl.test.algorithm;

import java.util.Stack;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * 
 * 示例 1:
 * 
 * 输入: "(()" 输出: 2 解释: 最长有效括号子串为 "()" 示例 2:
 * 
 * 输入: ")()())" 输出: 4 解释: 最长有效括号子串为 "()()"
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-valid-parentheses 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author ziliang.wu
 *
 */
public class LongestValidParentheses {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses("("));
        System.out.println(longestValidParentheses("(()"));
        System.out.println(longestValidParentheses(")()())"));
        System.out.println(longestValidParentheses("()(()"));
        System.out.println(longestValidParentheses("()(())"));
    }

    public static int longestValidParentheses(String input) {
        if (input == null || input.equals("")) {
            return 0;
        }
        int maxans = 0, left = 0, right = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {// 两边相等就是匹配上的 因为left肯定大于right right不会出现在前边
                maxans = Math.max(left * 2, maxans);
            } else if (left < right) {
                left = 0;
                right = 0;
            }
        }
        left = 0;
        right = 0;
        for (int i = input.length() - 1; i >= 0; i--) {
            char c = input.charAt(i);
            if (c == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxans = Math.max(left * 2, maxans);
            } else if (left > right) {
                left = 0;
                right = 0;
            }
        }
        return maxans;
    }

    // 使用栈 实际存入的是上一个右括号和右括号前边的值(用来计算)
    public static int longestValidParenthesesStack(String input) {

        if (input == null || input.equals("")) {
            return 0;
        }
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;

    }

    public static int longestValidParenthesesSlow(String input) {
        if (input == null || input.equals("")) {
            return 0;
        }
        char[] charArray = input.toCharArray();
        while (true) {
            boolean found = false;
            for (int i = 0; i < charArray.length;) {
                char c = charArray[i];
                if (c == '(') {
                    if (i + 1 >= input.length()) {
                        break;
                    }
                    int j = i + 1;
                    while (j < input.length()) {
                        char next = charArray[j];
                        if (next == '0') {
                            j++;
                            continue;
                        } else if (next == ')') {
                            charArray[i] = '0';
                            charArray[j] = '0';
                            found = true;
                        }
                        break;
                    }
                    i += j - i;
                } else {
                    i++;
                }
            }
            if (!found) {
                break;
            }
        }
        int maxCount = 0;
        int count = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '0') {
                count++;
            } else {
                if (maxCount < count) {
                    maxCount = count;
                }
                count = 0;
            }
        }
        if (maxCount < count) {
            maxCount = count;
        }
        return maxCount;
    }

    // error
    public static int longestValidParentheses1(String input) {
        if (input == null || input.equals("")) {
            return 0;
        }
        int leftCount = 0;
        int validCount = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                leftCount++;
            } else {
                if (leftCount > 0) {
                    leftCount--;
                    validCount++;
                }
            }
        }
        return validCount * 2;
    }
}
