package org.wuzl.test.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 单词的压缩编码
 * 
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 * 
 * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
 * 
 * 链接：https://leetcode-cn.com/problems/short-encoding-of-words
 * 
 * @author ziliang.wu
 *
 */
public class MinimumLengthEncoding {
    public static void main(String[] args) {
        MinimumLengthEncoding obj = new MinimumLengthEncoding();
        System.out.println(obj.minimumLengthEncoding(new String[] { "time", "me", "bell" }));
        System.out.println(obj.minimumLengthEncoding(new String[] { "time", "me", "e" }));

    }

    public int minimumLengthEncoding(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            for (int i = 1; i < word.length(); i++) {
                set.remove(word.substring(i));
            }
        }
        int count = 0;
        for (String word : set) {
            count += word.length() + 1;
        }
        return count;
    }
}
