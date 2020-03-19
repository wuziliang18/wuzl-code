package org.wuzl.test.algorithm;

/**
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 * 
 * 链接：https://leetcode-cn.com/problems/compress-string-lcci
 * 
 * @author ziliang.wu
 *
 */
public class CompressString {
    public static void main(String[] args) {
        CompressString obj = new CompressString();
        System.out.println(obj.compressString("aabcccccaaa"));
        System.out.println(obj.compressString("abbccd"));
    }

    public String compressString(String s) {
        if (s == null || s.isEmpty() || s.length() <= 2) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        char pre = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (pre != temp) {
                sb.append(pre).append(count);
                pre = temp;
                count = 1;
                continue;
            }
            count++;
        }
        sb.append(pre).append(count);
        return sb.length() >= s.length() ? s : sb.toString();
    }
}
