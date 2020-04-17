package org.wuzl.test.algorithm;

/**
 * 两数相加 II
 * 
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 
 * 进阶：
 * 
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * 
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 * 
 * @author ziliang.wu
 *
 */
public class AddTwoNumbersV2 {
    public static void main(String[] args) {
        AddTwoNumbersV2 obj = new AddTwoNumbersV2();
        ListNode l1 = getListNode(new int[] { 1, 8, 3 });
        ListNode l2 = getListNode(new int[] { 7, 1 });
        System.out.println(obj.addTwoNumbers(l1, l2));

    }

    private int tempVal = 0;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int length1 = length(l1);
        int length2 = length(l2);
        if (length1 < length2) {
            ListNode tempNode = l1;
            l1 = l2;
            l2 = tempNode;
            int tempLength = length1;
            length1 = length2;
            length2 = tempLength;
        }
        int surplus = length1 - length2;
        ListNode l1Temp = l1;
        while (surplus-- > 0) {
            l1Temp = l1Temp.next;
        }
        ListNode tempNode = addNode(l1Temp, l2);
        surplus = length1 - length2;
        if (surplus == 0) {
            return getResult(tempNode);
        }
        ListNode head = getNode(l1, surplus, tempNode);

        return getResult(head);
    }

    private ListNode getResult(ListNode node) {
        if (tempVal > 0) {
            ListNode result = new ListNode();
            result.val = tempVal;
            result.next = node;
            return result;
        }
        return node;
    }

    private ListNode getNode(ListNode node, int surplus, ListNode tempNode) {
        ListNode result = new ListNode();
        surplus--;
        if (surplus > 0) {
            result.next = getNode(node.next, surplus, tempNode);
        }
        if (surplus == 0) {
            result.next = tempNode;
        }
        int val = node.val + tempVal;
        tempVal = val / 10;
        result.val = val % 10;

        return result;
    }

    private ListNode addNode(ListNode l1, ListNode l2) {
        ListNode node = new ListNode();
        if (l1.next != null) {
            node.next = addNode(l1.next, l2.next);
        }
        int val = l1.val + l2.val + tempVal;
        tempVal = val / 10;
        node.val = val % 10;
        return node;
    }

    private int length(ListNode node) {
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    public static class ListNode {
        public ListNode() {

        }

        public ListNode(int val) {
            super();
            this.val = val;
        }

        public int val;
        public ListNode next;

        @Override
        public String toString() {
            return "ListNode [val=" + val + ", next=" + next + "]";
        }

    }

    public static ListNode getListNode(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        ListNode result = new ListNode(array[0]);
        ListNode temp = result;
        for (int i = 1; i < array.length; i++) {
            temp.next = new ListNode(array[i]);
            temp = temp.next;
        }
        return result;
    }
}
