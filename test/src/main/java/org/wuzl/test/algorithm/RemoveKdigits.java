package org.wuzl.test.algorithm;

/**
 * 移掉K位数字
 * 
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 
 * 注意:
 * 
 * num 的长度小于 10002 且 ≥ k。 num 不会包含任何前导零。
 * 
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 
 * @author ziliang.wu
 *
 */
public class RemoveKdigits {
    public static void main(String[] args) {
        
    }

    public String removeKdigits(String num, int k) {
        if (num == null || num.equals("")) {
            return "0";
        }
        if (num.length() <= k) {
            return "0";
        }
        return "0";
    }
}
