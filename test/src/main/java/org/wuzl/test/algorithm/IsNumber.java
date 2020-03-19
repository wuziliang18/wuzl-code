package org.wuzl.test.algorithm;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"0123"及"-1E-16"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 * https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/ qnm的数字校验
 * 
 * @author ziliang.wu
 *
 */
public class IsNumber {
    public static void main(String[] args) {
        IsNumber obj = new IsNumber();
        // System.out.println(obj.isNumber("1 "));
        // System.out.println(obj.isNumber("+100"));
        // System.out.println(obj.isNumber("5e2"));
        // System.out.println(obj.isNumber("-123"));
        // System.out.println(obj.isNumber("3.1416"));
        // System.out.println(obj.isNumber("0123"));
        // System.out.println(obj.isNumber("-1E-16"));
        // System.out.println(obj.isNumber("12e"));
        // System.out.println(obj.isNumber("1a3.14"));
        // System.out.println(obj.isNumber("1.2.3"));
        // System.out.println(obj.isNumber("+-5"));
        // System.out.println(obj.isNumber("12e+5.4"));
        // System.out.println(obj.isNumber("e"));
        System.out.println(obj.isNumber("4e+"));
    }

    public boolean isNumber(String s) {
        if (s == null) {
            return false;
        }
        s = s.trim();
        if (s.isEmpty()) {
            return false;
        }
        char first = s.charAt(0);
        if ((first > 57 || first < 48) && first != '+' && first != '-' && first != '.') {
            return false;
        }
        boolean point = false;
        if (first == '.') {
            point = true;
            if (s.length() == 1 || s.charAt(1) == 'e' || s.charAt(1) == 'E') {
                return false;
            }
        }
        if (first == 'e' && s.length() == 1) {
            return false;
        }

        boolean e = false;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 57 || c < 48) {
                if (c == '.') {
                    if (point) {
                        return false;
                    }
                    point = true;
                    continue;
                }
                if (c == 'e' || c == 'E') {
                    if (e) {
                        return false;
                    }
                    if (i + 1 < s.length()) {
                        if (s.charAt(i + 1) == '+' || s.charAt(i + 1) == '-') {
                            if (i + 2 >= s.length()) {
                                return false;
                            }
                            i++;
                        }
                    } else {
                        return false;
                    }
                    e = true;
                    point = true;
                    continue;
                }
                return false;
            }
        }
        return true;
    }
}
