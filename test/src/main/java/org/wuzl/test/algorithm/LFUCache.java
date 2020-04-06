package org.wuzl.test.algorithm;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {
    public static void main(String[] args) {
        // TreeMap map = new TreeMap<>();
        // map.put(2, 2);
        // map.put(1, 1);
        // map.put(3, 3);
        // System.out.println(map.firstKey());
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 返回 1
        cache.put(3, 3); // 去除 key 2
        System.out.println(cache.get(2)); // 返回 -1 (未找到key 2)
        System.out.println(cache.get(3)); // 返回 3
        cache.put(4, 4); // 去除 key 1
        System.out.println(cache.get(1)); // 返回 -1 (未找到 key 1)
        System.out.println(cache.get(3)); // 返回 3
        System.out.println(cache.get(4)); // 返回 4

    }

    private final int capacity;
    private int minFreq = 1;
    private final Map<Integer, Node> cache = new HashMap<>();
    private final Map<Integer, LinkedHashSet<Node>> freqMap = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        this.updateFreq(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            this.updateFreq(node);
        } else {
            node = new Node(key, value);
            node.freq = 1;
            if (cache.size() == capacity) {
                // 删除最后
                this.remove();
            }
            this.addNode(node);
            minFreq = 1;
        }

    }

    private void updateFreq(Node node) {
        LinkedHashSet<Node> set = freqMap.get(node.freq);
        set.remove(node);
        node.freq++;
        if (set.isEmpty()) {
            minFreq++;
        }
        set = freqMap.get(node.freq);
        if (set == null) {
            set = new LinkedHashSet<>();
            freqMap.put(node.freq, set);
        }
        set.add(node);
    }

    private void addNode(Node node) {
        cache.put(node.key, node);
        LinkedHashSet<Node> set = freqMap.get(node.freq);
        if (set == null) {
            set = new LinkedHashSet<>();
            freqMap.put(node.freq, set);
        }
        set.add(node);
    }

    private void remove() {
        LinkedHashSet<Node> set = freqMap.get(minFreq);
        Node node = set.iterator().next();
        cache.remove(node.key);
        set.remove(node);
        if (set.isEmpty()) {
            minFreq++;
        }
    }

    static class Node {
        int key;
        int value;
        int freq = 1;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
