package org.wuzl.test.linklist;

import org.wuzl.test.comm.ListNode;
import org.wuzl.test.comm.ListNodeUtil;

/**
 * 合并链表
 * 
 * @author ziliang.wu
 *
 */
public class MergeLinkList {
	public <T extends Comparable<T>> ListNode<T> merege(ListNode<T> node1, ListNode<T> node2) {
		if (node1 == null && node2 == null) {
			return null;
		}
		ListNode<T> head = new ListNode<T>();
		ListNode<T> temp = head;
		while (node1 != null && node2 != null) {
			if (node1.getVal().compareTo((node2.getVal())) < 0) {
				temp.setNext(node1);
				temp = node1;
				node1 = node1.getNext();
			} else {
				temp.setNext(node2);
				temp = node2;
				node2 = node2.getNext();
			}
			temp.setNext(null);
		}
		if (node1 != null) {
			temp.setNext(node1);
		} else if (node2 != null) {
			temp.setNext(node2);
		}
		return head.getNext();
	}

	public static void main(String[] args) {
		MergeLinkList obj = new MergeLinkList();
		System.out.println(obj.merege(ListNodeUtil.getListNode(new Integer[] { 1, 2, 3, 7, 9, 11 }),
				ListNodeUtil.getListNode(new Integer[] { 0, 4, 5, 6, 8, 9, 10, 12, 13, 15, 16 })));
	}
}
