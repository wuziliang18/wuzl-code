package org.wuzl.test.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串转换整数 (atoi) 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/string-to-integer-atoi 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author ziliang.wu
 *
 */
public class MyAtoi {
    public static void main(String[] args) {
        MyAtoi obj = new MyAtoi();
        // System.out.println(obj.myAtoi("+1"));
        // System.out.println(obj.myAtoi("42"));
        // System.out.println(obj.myAtoi(" -42"));
        // System.out.println(obj.myAtoi("4193 with words"));
        // System.out.println(obj.myAtoi("-91283472332"));
        // System.out.println(obj.myAtoi(" 0000000000012345678"));
        System.out.println(obj.myAtoi("2147483646"));

    }

    private static char empty = ' ';
    private static char addSign = '+';
    private static char minusSign = '-';
    private static int maxValue = Integer.MAX_VALUE / 10;
    static Map<Character, Integer> map = new HashMap<>();
    static {
        for (char c = '0'; c <= '9'; c++) {
            map.put(c, c - 48);
        }
    }

    /**
     * 他妈的太恶心人了不完了 各种极限数值
     * 
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        int result = 0;
        boolean hasAdd = false;
        int sign = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            Integer value = map.get(c);
            if (value != null) {
                if (result > maxValue * 10 - value) {
                    return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result = result * 10 + value;
                hasAdd = true;
                continue;
            }
            if (c == addSign) {
                if (sign != 0) {
                    break;
                }
                sign = 1;
                continue;
            }
            if (c == minusSign) {
                if (sign != 0) {
                    break;
                }
                sign = -1;
                continue;
            }
            if (c == empty) {
                if (hasAdd) {
                    break;
                }
                continue;
            }
            break;
        }
        if (sign == 0) {
            sign = 1;
        }
        return sign * result;
    }
}
