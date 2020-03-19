package org.wuzl.test.algorithm;

import java.util.Arrays;

/**
 * 拼写单词 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 * 
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 * 
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 * 
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。 链接：https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters
 * 
 * @author ziliang.wu
 *
 */
public class CountCharacters {
    public static void main(String[] args) {
        CountCharacters obj = new CountCharacters();
        System.out.println(obj.countCharacters(new String[] { "cat", "bt", "hat", "tree" }, "atach"));
        System.out.println(obj.countCharacters(new String[] { "hello", "world", "leetcode" }, "welldonehoneyr"));
    }
    int[] array = new int[26];
    public int countCharacters(String[] words, String chars) {
        int count = 0;
        for (String word : words) {
            count += getLength(word, chars);
        }
        return count;
    }

    private int getLength(String word, String chars) {
        Arrays.fill(array, 0);
        for (int i = 0; i < chars.length(); i++) {
            int index = chars.charAt(i) - 97;
            array[index] = array[index] + 1;
        }
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 97;
            if (array[index] > 0) {
                array[index] = array[index] - 1;
                continue;
            }
            return 0;
        }
        return word.length();
    }
}
