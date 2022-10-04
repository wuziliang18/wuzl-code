package org.wuzl.test.algorithm;

/**
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 
 * 示例 1：
 * 
 * 输入：s = "bcabc" 输出："abc" 示例 2：
 * 
 * 输入：s = "cbacdcbc" 输出："acdb" https://leetcode-cn.com/problems/remove-duplicate-letters/
 * 
 * @author ziliang.wu
 *
 */
public class RemoveDuplicateLetters {
    public static void main(String[] args) {

        RemoveDuplicateLetters obj = new RemoveDuplicateLetters();
        // System.out.println(obj.removeDuplicateLetters("bcabc"));
//        System.out.println(obj.removeDuplicateLetters("cbacdcbc"));
        System.out.println(obj.removeDuplicateLetters("ecbacba"));
    }

    public String removeDuplicateLetters(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        int[] temp = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            temp[index] = temp[index] + 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            int count = temp[index];
            if (count == 1) {// 只有一个
                sb.append(c);
                temp[index] = 0;
                continue;
            }
            if (count > 1) {// 重复的
                int end = i + 1;
                temp[index] = temp[index] - 1;
                while (end < s.length()) {
                    if (c > s.charAt(end) && temp[s.charAt(end) - 'a'] == 1) {
                        break;
                    }
                    if (c < s.charAt(end) && temp[s.charAt(end) - 'a'] == 1) {
                        s.charAt(end);
                        temp[index] = 0;
                        sb.append(c);
                        break;
                    }
                    end++;
                }
            }
        }
        return sb.toString();
    }
}
