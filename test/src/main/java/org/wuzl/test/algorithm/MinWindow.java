package org.wuzl.test.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 最小覆盖子串
 * 
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 * 
 * 示例：
 * 
 * 输入: S = "ADOBECODEBANC", T = "ABC" 输出: "BANC"
 * 
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 
 * @author ziliang.wu
 *
 */
public class MinWindow {
    public static void main(String[] args) {
        MinWindow obj = new MinWindow();
        System.out.println(obj.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(obj.minWindow("ADOBECODEBANC", "ABCC"));
        System.out.println(obj.minWindow("ABC", "ABC"));

    }

    public String minWindow(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer count = map.get(c);
            if (count == null) {
                count = 0;
            }
            map.put(c, count + 1);
        }
        int min = s.length();
        int max = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer count = map.get(c);
            if (count != null) {
                if (count == 1) {
                    map.remove(c);
                } else {
                    map.put(c, count - 1);
                }
                min = Math.min(min, i);
                max = Math.max(max, i);
                if (map.isEmpty()) {
                    break;
                }
            }
        }
        if (map.isEmpty()) {
            return s.substring(min, max + 1);
        }
        return "";
    }
}
