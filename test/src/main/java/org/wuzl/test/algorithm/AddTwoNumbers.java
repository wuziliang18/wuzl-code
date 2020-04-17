package org.wuzl.test.algorithm;

import org.wuzl.test.comm.ListNode;

/**
 * 两数相加 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 * @author ziliang.wu
 *
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode();
        ListNode node = head;
        int temp = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + temp;
            temp = sum / 10;
            int value = sum % 10;
            ListNode next = new ListNode();
            next.val = value;
            node.next = next;
            l1 = l1.next;
            l2 = l2.next;
            node = next;
        }
        while (l1 != null) {
            int sum = l1.val + temp;
            temp = sum / 10;
            int value = sum % 10;
            ListNode next = new ListNode();
            next.val = value;
            node.next = next;
            l1 = l1.next;
            node = next;
        }
        while (l2 != null) {
            int sum = l2.val + temp;
            temp = sum / 10;
            int value = sum % 10;
            ListNode next = new ListNode();
            next.val = value;
            node.next = next;
            l2 = l2.next;
            node = next;
        }
        if (temp > 0) {
            ListNode next = new ListNode();
            next.val = temp;
            node.next = next;
        }
        return head.next;
    }
}
