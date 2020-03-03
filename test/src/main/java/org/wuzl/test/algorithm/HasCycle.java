package org.wuzl.test.algorithm;

import org.wuzl.test.comm.ListNode;

/**
 * 环形链表 https://leetcode-cn.com/problems/linked-list-cycle/
 * 
 * @author ziliang.wu
 *
 */
public class HasCycle {
    public boolean hasCycle(ListNode<Integer> listNode) {
        if (listNode == null) {
            return false;
        }
        ListNode<Integer> fastListNode = listNode;
        while (listNode.getNext() != null) {
            listNode = listNode.getNext();
            if (fastListNode.getNext() == null || fastListNode.getNext().getNext() == null) {
                return false;
            }
            fastListNode = fastListNode.getNext().getNext();
            if (listNode == fastListNode) {
                return true;
            }
        }
        return false;
    }
}
