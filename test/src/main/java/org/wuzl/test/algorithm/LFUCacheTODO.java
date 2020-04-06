package org.wuzl.test.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * LFU缓存 get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。 put(key, value) -
 * 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
 * 
 * 链接：https://leetcode-cn.com/problems/lfu-cache
 * 
 * @author ziliang.wu
 *
 */
public class LFUCacheTODO {
    public static void main(String[] args) {
        // TreeMap map = new TreeMap<>();
        // map.put(2, 2);
        // map.put(1, 1);
        // map.put(3, 3);
        // System.out.println(map.firstKey());
        LFUCacheTODO cache = new LFUCacheTODO(2);
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

    private final Map<Integer, ListNode> dataMap;
    private final TreeMap<Integer, ListNode> cacheMap;
    private final Map<Integer, ListNode> tailMap;
    private int currentCount = 0;

    LFUCacheTODO(int capacity) {
        this.capacity = capacity;
        this.dataMap = new HashMap<>(capacity * 2);
        this.cacheMap = new TreeMap<>();
        this.tailMap = new HashMap<>();
    }

    int get(int key) {
        ListNode node = dataMap.get(key);
        if (node == null) {
            return -1;
        }
        if (node.pre.head && node.next == null) {
            cacheMap.remove(node.count);
            tailMap.remove(node.count);
        } else {
            // 修改原有关系
            node.pre.next = node.next;
            if (node.next != null) {
                node.next.pre = node.pre;
            } else {
                tailMap.put(node.count, node.pre);
            }

        }
        node.count++;
        // 插入新的关系
        this.put(node);
        return node.value;
    }

    void put(int key, int value) {
        if (currentCount == capacity) {
            // 删除最后的
            this.removeLast();
            currentCount--;
        }
        ListNode node = new ListNode();
        node.key = key;
        node.value = value;
        node.count = 1;
        dataMap.put(key, node);
        this.put(node);
        currentCount++;
    }

    private void removeLast() {
        Map.Entry<Integer, ListNode> entity = cacheMap.firstEntry();
        ListNode tail = tailMap.get(entity.getKey());
        dataMap.remove(tail.key);
        if (tail.pre.head) {
            tailMap.remove(entity.getKey());
            cacheMap.remove(entity.getKey());
            return;
        }
        tailMap.put(entity.getKey(), tail.pre);

    }

    private void put(ListNode node) {
        ListNode head = cacheMap.get(node.count);
        if (head == null) {
            head = new ListNode();
            head.head = true;
            cacheMap.put(node.count, head);
            tailMap.put(node.count, node);
        }
        node.next = head.next;
        if (head.next != null) {
            head.next.pre = node;
        }
        head.next = node;
        node.pre = head;
    }

    static class ListNode {
        int key;
        int value;
        int count;
        boolean head;
        ListNode pre;
        ListNode next;
    }
}
