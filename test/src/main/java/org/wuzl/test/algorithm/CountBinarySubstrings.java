package org.wuzl.test.algorithm;

/**
 * 计数二进制子串 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 * 
 * 重复出现的子串要计算它们出现的次数。
 * 
 * 示例 1 :
 * 
 * 输入: "00110011" 输出: 6 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 * 
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 * 
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * 
 * 链接：https://leetcode-cn.com/problems/count-binary-substrings
 * 
 * @author lvyue
 *
 */
public class CountBinarySubstrings {
    // TODO
    // 双指针
    // 右指针移到不等于自己值的前一个位置
    // 判断左右长度 取最小的count+=
    // 左指针移到右指针-长度位置 右指针移到下一位置循环找到下一个不等于自己的值
    public int countBinarySubstrings(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int count = 0;
        int left = 0, right = 0;
        char[] array = s.toCharArray();
        int preChar = array[left];
        for (int i = 1; i <= array.length; i++) {
            if (preChar != array[i]) {
                right = i;
                preChar = array[i];
                break;
            }
        }
        if (right == 0) {
            return 0;
        }
        while (right < array.length) {

        }
        return count;
    }
}
