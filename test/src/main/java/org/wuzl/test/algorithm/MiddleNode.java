package org.wuzl.test.algorithm;

import org.wuzl.test.comm.ListNode;
import org.wuzl.test.comm.ListNodeUtil;

/**
 * 链表的中间结点
 * 
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 * 
 * 如果有两个中间结点，则返回第二个中间结点。
 * 
 * @author ziliang.wu
 *
 */
public class MiddleNode {
    public static void main(String[] args) {
        MiddleNode obj = new MiddleNode();
        System.out.println(ListNodeUtil.getListNode(5));
        System.out.println(ListNodeUtil.getListNode(6));
        System.out.println(obj.middleNode(ListNodeUtil.getListNode(5)));
        System.out.println(obj.middleNode(ListNodeUtil.getListNode(6)));
    }

    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            head = head.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        return head;
    }
}
