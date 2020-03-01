package org.wuzl.test.algorithm;

import org.wuzl.test.comm.ListNode;
import org.wuzl.test.comm.ListNodeUtil;

/**
 * 链表翻转
 * 
 * @author ziliang.wu
 *
 */
public class ReverseLinkList {
    public static void main(String[] args) {
        ListNode<Integer> listNode = ListNodeUtil.getListNode(10);
        System.out.println(reverse(listNode));
        listNode = ListNodeUtil.getListNode(9);
        System.out.println(reverse(listNode));
    }

    public static <T> ListNode<T> reverse(ListNode<T> listNode) {
        if (listNode == null || !listNode.hasNext()) {
            return listNode;
        }
        ListNode<T> next = listNode;
        while (listNode.hasNext()) {
            ListNode<T> temp = next;
            next = listNode.getNext();
            listNode.setNext(next.getNext());
            next.setNext(temp);
        }
        return next;
    }
}
