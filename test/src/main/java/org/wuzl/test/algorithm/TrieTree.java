package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * trie树 用于单词匹配和提示 简单版a-z
 * 
 * @author ziliang.wu
 *
 */
public class TrieTree {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("word");
        list.add("he");
        list.add("wo");
        list.add("HE");
        list.add("how");
        list.add("hi");
        TrieTree trieTree = new TrieTree();
        for (String word : list) {
            trieTree.addWord(word);
        }

        list.add("h");
        list.add("hij");
        list.add("hell");
        list.add("hellow");
        for (String word : list) {
            System.out.println("word: " + word + " find result: " + trieTree.find(word));
        }
    }

    private final TrieTreeNode root = new TrieTreeNode('/');

    public void addWord(String word) {
        if (word == null || word.equals("")) {
            return;
        }
        word = word.toLowerCase();
        int length = word.length();
        TrieTreeNode node = root;
        for (int i = 0; i < length; i++) {
            char c = word.charAt(i);
            int index = getIndex(c);
            if (c < 0) {
                continue;
            }
            TrieTreeNode next = node.getChildren()[index];
            if (next == null) {
                next = new TrieTreeNode(c);
                node.getChildren()[index] = next;
            }
            node = next;
        }
        node.setEnd(true);
    }

    public boolean find(String word) {
        if (word == null || word.equals("")) {
            return false;
        }
        word = word.toLowerCase();
        int length = word.length();
        TrieTreeNode node = root;
        for (int i = 0; i < length; i++) {
            char c = word.charAt(i);
            int index = getIndex(c);
            if (c < 0) {
                continue;
            }
            TrieTreeNode next = node.getChildren()[index];
            if (next == null) {
                return false;
            }
            node = next;
        }
        return node.isEnd();
    }

    private int getIndex(char c) {
        int index = c - 'a';
        if (index > 26) {
            index = -1;
        }
        return index;
    }

    static class TrieTreeNode {
        private final TrieTreeNode[] children = new TrieTreeNode[26];
        private boolean end = false;
        private final char data;

        public TrieTreeNode(char data) {
            this.data = data;
        }

        public boolean isEnd() {
            return end;
        }

        public void setEnd(boolean end) {
            this.end = end;
        }

        public char getData() {
            return data;
        }

        public TrieTreeNode[] getChildren() {
            return children;
        }

    }
}
