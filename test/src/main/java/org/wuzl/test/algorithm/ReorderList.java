package org.wuzl.test.algorithm;

import java.util.Iterator;
import java.util.LinkedList;

import org.wuzl.test.comm.ListNode;
import org.wuzl.test.comm.ListNodeUtil;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * https://leetcode-cn.com/problems/reorder-list/
 * 
 * 没做出来
 * TODO
 * @author ziliang.wu
 *
 */
public class ReorderList {
    public static void main(String[] args) {
        ReorderList obj = new ReorderList();
        ListNode node = ListNodeUtil.getListNode(4, 1);
        obj.reorderList(node);
        node = ListNodeUtil.getListNode(5, 1);
        obj.reorderList(node);
    }

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        int count = 0;
        // 缓存
        LinkedList<ListNode> list = new LinkedList<ListNode>();
        ListNode temp = head;
        while (temp != null) {
            list.addFirst(temp);
            temp = temp.next;
            count++;
        }
        Iterator<ListNode> ite = list.iterator();
        temp = head;
        for (int i = 0; i < count / 2; i++) {
            ListNode tail = ite.next();
            tail.next = null;
            tail.next = temp.next;
            temp.next = tail;
            temp = tail.next;
        }
    }
}
