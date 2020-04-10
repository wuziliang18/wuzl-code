package org.wuzl.test.algorithm;

public class StrToInt {

    public static void main(String[] args) {
        // System.out.println(new StrToInt().strToInt("42"));
        // System.out.println(new StrToInt().strToInt(" -42"));
        System.out.println(new StrToInt().strToInt("2147483646"));
    }

    public int strToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int index = 0;
        for (; index < str.length(); index++) {
            if (str.charAt(index) != ' ') {
                break;
            }
        }
        int result = 0;
        boolean sign = true;
        boolean hasSign = false;
        boolean hasNum = false;
        for (; index < str.length(); index++) {
            char c = str.charAt(index);
            if (c == ' ') {
                return result;
            }
            if (c == '+') {
                if (hasNum || hasSign) {
                    return result;
                }
                hasSign = true;
                continue;
            }
            if (c == '-') {
                if (hasNum || hasSign) {
                    return result;
                }
                sign = false;
                hasSign = true;
                continue;
            }
            int num = c - 48;
            if (num >= 0 && num <= 9) {
                if (sign) {
                    if (result >= Integer.MAX_VALUE || result > Integer.MAX_VALUE / 10
                            || result > (Integer.MAX_VALUE - num) / 10) {
                        return Integer.MAX_VALUE;
                    }
                    result = result * 10 + num;
                } else {
                    if (result <= Integer.MIN_VALUE || result < Integer.MIN_VALUE / 10
                            || result < (Integer.MIN_VALUE + num) / 10) {
                        return Integer.MIN_VALUE;
                    }
                    result = result * 10 - num;
                }
                hasNum = true;
                continue;
            }
            return result;
        }
        return result;
    }
}
