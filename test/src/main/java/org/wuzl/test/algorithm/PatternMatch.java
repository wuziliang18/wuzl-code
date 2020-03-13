package org.wuzl.test.algorithm;

/**
 * 正则表达式匹配 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * 
 * '.' 匹配任意单个字符 '*' 匹配零个或多个前面的那一个元素
 * 
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 *
 */
public class PatternMatch {
    private static PatternMatch obj = new PatternMatch();

    public static void main(String[] args) {
        print("aa", "a", false);
        print("aa", "a*", true);
        print("ab", ".", false);
        print("aab", "c*a*b", true);
        print("mississippi", "mis*is*p*.", false);
        print("", "a*b*", true);
    }

    private static void print(String s, String p, boolean result) {
        if (obj.isMatch(s, p) != result) {
            System.out.println("error, s:" + s + " , p: " + p);
        }
    }

    public boolean isMatch(String s, String p) {
        if (s == null || s.equals("")) {
            return p == null || p.equals("");
        }
        return false;
    }
}
