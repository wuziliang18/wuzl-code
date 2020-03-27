package org.wuzl.test.algorithm;

import org.wuzl.test.comm.ListNode;

/**
 * 输入两个链表，找出它们的第一个公共节点。
 * 
 * @author ziliang.wu
 *
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node1 = headA;
        ListNode node2 = headB;

        while (node1 != node2) {
            node1 = node1 == null ? headB : node1.next;
            node2 = node2 == null ? headA : node2.next;
        }
        return node1;
    }
}
