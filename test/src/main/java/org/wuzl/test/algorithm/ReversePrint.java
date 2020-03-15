package org.wuzl.test.algorithm;

import java.util.Arrays;

import org.wuzl.test.comm.ListNode;
import org.wuzl.test.comm.ListNodeUtil;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * 
 * @author ziliang.wu
 *
 */
public class ReversePrint {
    public static void main(String[] args) {
        ListNode head = ListNodeUtil.getListNode(6);
        System.out.println(head);
        System.out.println(Arrays.toString(new ReversePrint().reversePrint(head)));
    }

    public int[] reversePrint(ListNode<Integer> head) {
        if (head == null) {
            return new int[0];
        }
        int count = 1;
        ListNode temp = head;
        while (temp.next != null) {
            count++;
            temp = temp.next;
        }
        int[] array = new int[count];
        while (head != null) {
            array[--count] = head.val;
            head = head.next;
        }
        return array;
    }
}
