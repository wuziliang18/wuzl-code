package org.wuzl.test.algorithm;

/**
 * 验证回文字符串 Ⅱ 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 * 
 * https://leetcode-cn.com/problems/valid-palindrome-ii/
 * 
 * @author ziliang.wu
 *
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        ValidPalindrome obj = new ValidPalindrome();
        System.out.println(obj.validPalindrome("aba"));
        System.out.println(obj.validPalindrome("abca"));
        System.out.println(obj.validPalindrome("ebcbbececabbacecbbcbe"));
    }

    public boolean validPalindrome(String s) {

        if (s == null || s.isEmpty()) {
            return true;
        }

        return validPalindrome(s, 0, s.length() - 1, false);
    }

    public boolean validPalindrome(String s, int left, int right, boolean del) {
        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
                continue;
            }
            if (del) {
                return false;
            }
            del = true;
            if (s.charAt(left) == s.charAt(right - 1) && s.charAt(left + 1) == s.charAt(right)) {
                return validPalindrome(s, left + 1, right, del) || validPalindrome(s, left, right - 1, del);
            }
            if (s.charAt(left) == s.charAt(right - 1)) {
                right--;
                continue;
            }
            if (s.charAt(left + 1) == s.charAt(right)) {
                left++;
                continue;
            }
            return false;
        }
        return true;
    }
}
