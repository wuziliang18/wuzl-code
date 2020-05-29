package org.wuzl.test.algorithm;

/**
 * 每个元音包含偶数次的最长子字符串
 * 
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
 * 
 * 
 * 链接：https://leetcode-cn.com/problems/find-the-longest-substring-containing-vowels-in-even-counts
 * 
 * @author ziliang.wu
 *
 */
public class FindTheLongestSubstring {
    public static void main(String[] args) {
        FindTheLongestSubstring obj = new FindTheLongestSubstring();
        System.out.println(obj.findTheLongestSubstring("eleetminicoworoep"));
        // System.out.println(obj.findTheLongestSubstring("leetcodeisgreat"));
    }

    public int findTheLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        return findTheLongestSubstring(s, 0, s.length() - 1, new char[] { 'a', 'e', 'i', 'o', 'u' }, 0);
    }

    /**
     * 暴力 超时
     * 
     * @param s
     * @param start
     * @param end
     * @param array
     * @param index
     * @return
     */
    private int findTheLongestSubstring(String s, int start, int end, char[] array, int index) {
        if (index == array.length) {
            return end - start + 1;
        }
        if (start < 0 || end >= s.length()) {
            return 0;
        }
        int count = 0;
        int first = -1;
        int last = -1;
        char c = array[index];
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) == c) {
                if (first == -1) {
                    first = i;
                }
                last = i;
                count++;
            }
        }
        if (count % 2 == 0) {
            return findTheLongestSubstring(s, start, end, array, index + 1);
        }
        return Math.max(findTheLongestSubstring(s, first + 1, end, array, 0),
                findTheLongestSubstring(s, start, last - 1, array, 0));
    }
}
