package org.wuzl.test.algorithm;

import org.wuzl.test.comm.ListNode;
import org.wuzl.test.comm.ListNodeUtil;

/**
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * 
 * @author ziliang.wu
 *
 */
public class MergeTwoLists {
    public static void main(String[] args) {
        MergeTwoLists obj = new MergeTwoLists();
        System.out.println(obj.mergeTwoLists(ListNodeUtil.getListNode(new Integer[] { 1, 2, 4 }),
                ListNodeUtil.getListNode(new Integer[] { 1, 3, 4 })));
    }

    public ListNode mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode haed = null;
        if (l1.val > l2.val) {
            haed = l2;
            l2 = l2.next;
        } else {
            haed = l1;
            l1 = l1.next;
        }
        ListNode temp = haed;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        if (l1 != null) {
            temp.next = l1;
        } else {
            temp.next = l2;
        }
        return haed;
    }
}
