package org.wuzl.test.algorithm;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 
 * 示例 1:
 * 
 * 输入: s = "anagram", t = "nagaram" 输出: true 示例 2:
 * 
 * 输入: s = "rat", t = "car" 输出: false 说明: 你可以假设字符串只包含小写字母。
 * 
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * 
 * @author ziliang.wu
 *
 */
public class IsAnagram {
    public boolean isAnagram(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) {
            return (s == null || s.isEmpty()) && (t == null || t.isEmpty());
        }
        if (s.length() != t.length()) {
            return false;
        }
        int[] array = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            array[index] = array[index] + 1;
        }
        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            array[index] = array[index] - 1;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
