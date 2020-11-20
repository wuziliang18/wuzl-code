package org.wuzl.test.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * 
 * 每次转换只能改变一个字母。 转换过程中的中间单词必须是字典中的单词。 说明:
 * 
 * 如果不存在这样的转换序列，返回 0。 所有单词具有相同的长度。 所有单词只由小写字母组成。 字典中不存在重复的单词。 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 
 * @author ziliang.wu
 *
 */
public class LadderLength {
    public static void main(String[] args) {
        LadderLength obj = new LadderLength();
        // System.out.println(obj.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
        // System.out.println(obj.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log")));
        System.out.println(obj.ladderLength("hit", "cog", Arrays.asList("cog")));
        System.out.println(obj.ladderLength("hog", "cog", Arrays.asList("cog")));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.isEmpty()) {
            return 0;
        }
        int[] array = new int[wordList.size()];
        Arrays.fill(array, -1);
        Queue<Integer> queue = new LinkedList<>();
        int findIndex = -1;
        for (int i = 0; i < wordList.size(); i++) {
            String findWord = wordList.get(i);
            if (findWord.equals(endWord)) {
                array[i] = 1;
                findIndex = i;
                break;
            }
        }
        if (findIndex == -1) {
            return 0;
        }
        queue.add(findIndex);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            int level = array[index];
            String findWord = wordList.get(index);
            for (int i = 0; i < wordList.size(); i++) {
                if (array[i] != -1) {
                    continue;
                }
                String word = wordList.get(i);
                if (this.check(findWord, word)) {
                    array[i] = level + 1;
                    queue.add(i);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < wordList.size(); i++) {
            String word = wordList.get(i);
            if (this.check(beginWord, word)) {
                min = Math.min(min, array[i]);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min + 1;
    }

    private boolean check(String word, String targetWord) {
        if (word.length() != targetWord.length()) {
            return false;
        }
        int errorNum = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != targetWord.charAt(i)) {
                errorNum++;
                if (errorNum > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
