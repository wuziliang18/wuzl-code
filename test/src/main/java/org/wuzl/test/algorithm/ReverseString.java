package org.wuzl.test.algorithm;

import java.util.Arrays;

/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/reverse-string 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author ziliang.wu
 *
 */
public class ReverseString {
    public static void main(String[] args) {
        char[] s = "hellow".toCharArray();
        new ReverseString().reverseString(s);
        System.out.println(Arrays.toString(s));
    }

    public void reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        int left = 0;
        int right = s.length - 1;
        while (left <= right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }

    }

    public void reverseStringV1(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        int length = s.length;
        for (int i = 0; i < length / 2; i++) {
            char temp = s[i];
            int changeIndex = length - 1 - i;
            s[i] = s[changeIndex];
            s[changeIndex] = temp;
        }

    }
}
