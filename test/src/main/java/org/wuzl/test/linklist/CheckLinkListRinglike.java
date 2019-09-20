package org.wuzl.test.linklist;

import org.wuzl.test.comm.ListNode;
import org.wuzl.test.comm.ListNodeUtil;

public class CheckLinkListRinglike {
	public <T> boolean checkRinglike(ListNode<T> node, int count) {
		if (node == null || !node.hasNext()) {
			return false;
		}
		ListNode<T> node1 = node;
		ListNode<T> node2 = node;
		//实际不用count
		for (int i = 0; i < count; i++) {
			if (node1.hasNext() && node1.getNext().hasNext() && node2.hasNext()) {
				node1 = node1.getNext().getNext();
				node2 = node2.getNext();
				if (node1.equals(node2)) {
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		CheckLinkListRinglike obj = new CheckLinkListRinglike();
		int count = 10;
		ListNode<Integer> listNode = ListNodeUtil.getListNode(count);
		System.out.println(obj.checkRinglike(listNode, count));
		int index = 3;
		int i = 1;
		ListNode<Integer> temp = null;
		ListNode<Integer> head = listNode;
		ListNode<Integer> end = null;
		while (listNode.hasNext()) {
			if (i++ == index) {
				temp = listNode;
			}
			listNode = listNode.getNext();
			end = listNode;
		}
		System.out.println(temp);
		System.out.println(end);
		end.setNext(temp);
		System.out.println(obj.checkRinglike(head, count));
		ListNode<Integer> node = new ListNode<Integer>(1);
		System.out.println(obj.checkRinglike(node, count));
		node.setNext(node);
		System.out.println(obj.checkRinglike(node, count));
	}
}
