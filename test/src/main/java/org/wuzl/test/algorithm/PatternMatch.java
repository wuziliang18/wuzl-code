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

    // TODO
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        if (s.equals("")) {
            return isMatchBlank(p);
        }
        if (p.length() == 1) {
            if (s.length() != 1) {
                return false;
            }
            if (p.charAt(0) != s.charAt(0) && p.charAt(0) != '.') {
                return false;
            }
        }
        boolean[][] matchArray = new boolean[p.length()][s.length() + 1];
        int pLength = p.length();
        int sLength = s.length();
        // 填充第一行
        char pFirstChar = p.charAt(0);
        if (pFirstChar == '.') {
            matchArray[0][0] = true;
            if (p.charAt(1) == '*') {
                for (int i = 1; i <= s.length(); i++) {
                    matchArray[0][i] = true;
                }
                return true;
            } else {
                matchArray[0][1] = true;
            }
        } else if (pFirstChar == s.charAt(0)) {
            matchArray[0][1] = true;
        }

        for (int pIndex = 1; pIndex < p.length(); pIndex++) {
            char pChar = p.charAt(pIndex);
            if (pChar == '.' && pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
                processRowTrue(matchArray, pIndex, 0, sLength);
                continue;
            }
            if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
                matchArray[pIndex][0] = true;
            }
            for (int i = 1; i <= s.length(); i++) {
                char sChar = s.charAt(i - 1);
                if (pChar == sChar) {
                    matchArray[pIndex][i] = true;
                }
            }
        }
        int j = 1;
        for (int i = 0; i < pLength; i++) {

        }
        return true;
    }

    private void processRowTrue(boolean[][] matchArray, int pIndex, int sStart, int sEnd) {
        for (int i = sStart; i <= sEnd; i++) {
            matchArray[pIndex][i] = true;
        }
    }

    private boolean isMatchBlank(String p) {
        if (p.length() == 0) {
            return false;
        }
        int length = p.length();
        for (int i = 0; i < length; i++) {
            char c = p.charAt(i);
            if (c != '.' || c != '*') {
                if (i == length - 1 || (p.charAt(i + 1) != '.' || p.charAt(i + 1) != '*')) {
                    return false;
                }
            }
        }
        return true;
    }
}
