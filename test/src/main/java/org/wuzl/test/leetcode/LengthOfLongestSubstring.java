package org.wuzl.test.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 
 * 示例 1:
 * 
 * 输入: "abcabcbb" 输出: 3 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。 示例 2:
 * 
 * 输入: "bbbbb" 输出: 1 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 
 * @author ziliang.wu
 *
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        // List<String> list = Arrays.asList("dvdf", "abcabcbb", "bbbbb", "pwwkew", "pwwkewwabcdfe");
        // PrintUtil.print(list, input -> lengthOfLongestSubstring(input));
        // PrintUtil.print(list, input -> solution(input));
        // System.out.println(lengthOfLongestSubstring("bpfbhmipx"));
        System.out.println(lengthOfLongestSubstringV2("pwwkew"));
    }

    /**
     * 第二次写
     * 
     * @param input
     * @return
     */
    public static int lengthOfLongestSubstringV2(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        int result = 0;
        int start = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            Integer index = map.get(c);
            if (index == null) {
                map.put(c, i);
                continue;
            }
            result = Math.max(result, map.size());
            for (; start <= index; start++) {
                map.remove(input.charAt(start));
            }
            map.put(c, i);
        }
        result = Math.max(result, map.size());
        return result;
    }

    public static int lengthOfLongestSubstring(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        int max = 0;
        int start = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            Integer index = map.get(input.charAt(i));
            if (index != null) {
                max = Math.max(max, map.size());
                for (; start <= index; start++) {
                    map.remove(input.charAt(start));
                }
            }
            map.put(input.charAt(i), i);
        }
        max = Math.max(max, map.size());
        return max;
    }

    /**
     * 错误没考虑重复的前几位没有重复
     * 
     * @param input
     * @return
     */
    public static int solution(String input) {
        if (input == null || input.equals("")) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int max = 0;
        int length = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (set.contains(c)) {
                max = Math.max(length, max);
                set.clear();
                length = 0;
                continue;
            }
            length++;
            set.add(c);
        }
        max = Math.max(length, max);
        return max;
    }
}
