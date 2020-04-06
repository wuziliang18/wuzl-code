package org.wuzl.test.algorithm;

/**
 * 编辑距离
 * 
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 
 * 你可以对一个单词进行如下三种操作：
 * 
 * 插入一个字符 删除一个字符 替换一个字符
 * 
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 
 * @author ziliang.wu
 *
 */
public class MinDistance {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word1.isEmpty()) {
            return word2 == null ? 0 : word2.length();
        }
        if (word2 == null) {
            return word1 == null ? 0 : word1.length();
        }
        int[][] array = new int[word1.length() + 1][word2.length() + 1];// 保存word1的前i个字符到word2的前j个字符的最短距离
        for (int i = 0; i <= word1.length(); i++) {
            array[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            array[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    array[i][j] = array[i - 1][j - 1];
                } else {
                    array[i][j] = 1 + Math.min(Math.min(array[i - 1][j], array[i][j - 1]), array[i - 1][j - 1]);
                }
            }
        }
        return array[word1.length()][word2.length()];
    }
}
