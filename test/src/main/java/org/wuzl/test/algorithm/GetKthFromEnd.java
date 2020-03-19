package org.wuzl.test.algorithm;

import org.wuzl.test.comm.ListNode;
import org.wuzl.test.comm.ListNodeUtil;

public class GetKthFromEnd {
    public static void main(String[] args) {
        GetKthFromEnd obj = new GetKthFromEnd();
        System.out.println(obj.getKthFromEnd(ListNodeUtil.getListNode(new Integer[] { 1, 2, 3, 4, 5 }), 2));
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        if (count <= k) {
            return head;
        }
        temp = head;
        while (temp != null) {
            if (count == k) {
                return temp;
            }
            count--;
            temp = temp.next;
        }
        return temp;
    }
}
