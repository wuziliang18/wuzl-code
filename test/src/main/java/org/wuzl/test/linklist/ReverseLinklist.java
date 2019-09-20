package org.wuzl.test.linklist;

import java.util.Arrays;
import java.util.List;

import org.wuzl.test.comm.ListNode;
import org.wuzl.test.comm.ListNodeUtil;

/**
 * 链表翻转
 * 
 * @author ziliang.wu
 *
 */
public class ReverseLinklist {
	public <T> ListNode<T> reverse(ListNode<T> node) {
		if (node == null) {
			return node;
		}
		ListNode<T> head = new ListNode<>();
		head.setNext(node);
		while (node.hasNext()) {
//			ListNode<T> temp2=node.getNext().getNext();
//			temp.setNext(node.getNext());
//			temp.getNext().setNext(node);
//			node.setNext(temp2);
			ListNode<T> temp = head.getNext();
			ListNode<T> nextNode = node.getNext();
			head.setNext(nextNode);
			node.setNext(nextNode.getNext());
			nextNode.setNext(temp);

		}
		return head.getNext();
	}

	public static void main(String[] args) {
		ReverseLinklist obj = new ReverseLinklist();
		List<Integer> list = Arrays.asList(0, 1, 2, 3, 5, 10);
//		List<Integer> list = Arrays.asList(3);
		for (Integer count : list) {
			ListNode<Integer> listNode = ListNodeUtil.getListNode(count);
			System.out.print(listNode + " result:  ");
			System.out.println(obj.reverse(listNode));
		}

	}
}
