package org.wuzl.test.algorithm;

/**
 * 左旋转字符串
 * 
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 * 
 * 链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
 * 
 * @author ziliang.wu
 *
 */
public class ReverseLeftWords {
    public static void main(String[] args) {
        System.out.println(new ReverseLeftWords().reverseLeftWords("abcdefg", 2));
    }
    public String reverseLeftWords(String s, int n) {
        if (s == null || s.length() <= n) {
            return s;
        }
        int length = s.length();
        char[] array = new char[length];
        int index = 0;
        for (int i = length - n; i < length; i++) {
            array[i] = s.charAt(index++);
        }
        index = 0;
        for (int i=n; i < length; i++) {
            array[index++] = s.charAt(i);
        }
        return new String(array);
    }
}
