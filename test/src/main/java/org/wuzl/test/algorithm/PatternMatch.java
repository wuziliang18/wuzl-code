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
        // print("aa", "a", false);
        // print("aa", "a*", true);
        // print("ab", ".", false);
        // print("ab", ".*", true);
        // print("aaa", "a*a", true);
        // print("aab", "c*a*b", true);
        // print("mississippi", "mis*is*p*.", false);
        // print("", "a*b*", true);
        // print("a", "ab*", true);
        // print("ab", ".*..c*", true);
        print("bb", "..*c", false);
        System.out.println("end");

    }

    private static void print(String s, String p, boolean result) {
        if (obj.isMatch(s, p) != result) {
            System.out.println("error, s:" + s + " , p: " + p);
        }
    }

    public boolean isMatch(String s, String p) {
        if (s == null) {
            return false;
        }
        if (p == null || p.isEmpty()) {
            return s.isEmpty();
        }
        return dpCheck(s, p, 0, 0);
    }

    private boolean dpCheck(String s, String p, int i, int j) {
        if (s.length() == i && p.length() == j) {
            return true;
        }
        if (p.length() <= j) {
            return false;
        }
        if (s.length() <= i) {
            // 判断p后续是否匹配空
            if ((p.length() - j) % 2 != 0) {
                return false;
            }
            for (j = j + 1; j < p.length(); j += 2) {
                if (p.charAt(j) != '*') {
                    return false;
                }
            }
            return true;
        }
        // 查看头是否匹配
        char pChar = p.charAt(j);
        boolean headMatch = s.charAt(i) == pChar || pChar == '.';
        if (headMatch) {
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                return dpCheck(s, p, i + 1, j) || dpCheck(s, p, i + 1, j + 2) || dpCheck(s, p, i, j + 2);
            }
            return dpCheck(s, p, i + 1, j + 1);
        }
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {// 后边有星
            return dpCheck(s, p, i, j + 2);
        }
        return false;
    }
}
