package org.wuzl.test.algorithm;

import java.util.Stack;

/**
 * 字符串解码
 * 
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 
 * 
 * 
 * 链接：https://leetcode-cn.com/problems/decode-string
 * 
 * @author ziliang.wu
 *
 */
public class DecodeString {
    public static void main(String[] args) {
        DecodeString obj = new DecodeString();
        System.out.println(obj.decodeString("3[a]2[bc]"));
        System.out.println(obj.decodeString("3[a2[c]]"));
        System.out.println(obj.decodeString("2[abc]3[cd]ef"));
    }

    public String decodeString(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c <= '9' && c >= '0') {
                num += (num * 10 + c - '0');
                continue;
            }
            if (c == '[') {
                stack.add(num);
                num = 0;
                continue;
            }
            if (c == ']') {
                int currentNum = stack.pop();
            }
        }
        return sb.toString();
    }
}
