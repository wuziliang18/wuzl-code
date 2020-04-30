package org.wuzl.test.algorithm;

import org.wuzl.test.comm.ListNode;
import org.wuzl.test.comm.ListNodeUtil;

public class MergeKLists {
    public static void main(String[] args) {
        ListNode[] lists = { ListNodeUtil.getListNode(new Integer[] { 2, 5, 16 }),
                ListNodeUtil.getListNode(new Integer[] { 1, 3, 9 }),
                ListNodeUtil.getListNode(new Integer[] { 2, 3, 7 }) };
        System.out.println(mergeKLists(lists));
    }

    public static ListNode<Integer> mergeKLists(ListNode<Integer>[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private static ListNode<Integer> merge(ListNode<Integer>[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = (left + right) / 2;
        return merge(merge(lists, left, mid), merge(lists, mid + 1, right));

    }

    private static ListNode<Integer> merge(ListNode<Integer> first, ListNode<Integer> sec) {
        ListNode<Integer> head = new ListNode<Integer>();
        ListNode<Integer> temp = head;
        while (first != null && sec != null) {
            if (first.val <= sec.val) {
                temp.next = first;
                first = first.next;
            } else {
                temp.next = sec;
                sec = sec.next;
            }
            temp = temp.next;
        }
        if (first != null) {
            temp.setNext(first);
        }
        if (sec != null) {
            temp.setNext(sec);
        }
        return head.next;
    }

    public static ListNode<Integer> mergeKListsV1(ListNode<Integer>[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode<Integer> head = null;
        int index = -1;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                if (head == null) {
                    head = lists[i];
                    index = i;
                } else if (lists[i].getVal() < head.getVal()) {
                    head = lists[i];
                    index = i;
                }
            }
        }
        if (head == null) {
            return head;
        }
        lists[index] = lists[index].getNext();
        head.setNext(null);
        ListNode<Integer> node = head;
        while (true) {
            ListNode<Integer> temp = null;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    if (temp == null) {
                        temp = lists[i];
                        index = i;
                    } else {
                        if (lists[i].getVal() < temp.getVal()) {
                            temp = lists[i];
                            index = i;
                        }
                    }
                }
            }
            if (temp == null) {
                break;
            }
            lists[index] = lists[index].getNext();
            node.setNext(temp);
            node = node.getNext();
            temp.setNext(null);
        }
        return head;
    }
}
