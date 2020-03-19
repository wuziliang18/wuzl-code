package org.wuzl.test.algorithm;

import java.util.Arrays;

/**
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * 
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。 https://leetcode-cn.com/problems/longest-palindrome/
 * 
 * @author ziliang.wu
 *
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome obj = new LongestPalindrome();
        System.out.println(obj.longestPalindrome("abccccddZzzZ"));

    }

    public int longestPalindrome(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() <= 1) {
            return s.length();
        }
        short[] array = new short[52];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = 0;
            if (c >= 'A' && c <= 'Z') {
                index = c - 65;
            } else {
                index = c - 97 + 26;
            }
            array[index] = (short) (array[index] + 1);
        }
        int count = 0;
        for (int i = 0; i < 52; i++) {
            count += array[i] % 2 == 0 ? array[i] : array[i] - 1;
        }
        if (count < s.length()) {
            count++;
        }
        return count;
    }

    public int longestPalindromeV1(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() <= 1) {
            return s.length();
        }
        char[] array = s.toCharArray();
        Arrays.sort(array);
        int count = 0;
        int tempCount = 1;
        char tempChar = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] == tempChar) {
                tempCount++;
                continue;
            }
            if (tempCount % 2 == 1) {
                tempCount--;
            }
            count += tempCount;
            tempCount = 1;
            tempChar = array[i];
        }
        if (tempCount % 2 != 0) {
            tempCount--;
        }
        count += tempCount;
        if (count < array.length) {
            count++;
        }
        return count;
    }
}
