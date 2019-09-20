package org.wuzl.test.comm;

public class ListNodeUtil {
	public static <T> ListNode<T> getListNode(T[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		ListNode<T> result = new ListNode<T>(array[0]);
		ListNode<T> temp = result;
		for (int i = 1; i < array.length; i++) {
			temp.setNext(new ListNode<T>(array[i]));
			temp = temp.next;
		}
		return result;
	}

	public static ListNode<Integer> getListNode(int count) {
		return getListNode(count, 1);
	}

	public static ListNode<Integer> getListNode(int count, int start) {
		ListNode<Integer> head = new ListNode<>();
		ListNode<Integer> temp = head;
		for (int i = 0; i < count; i++) {
			temp.setNext(new ListNode<Integer>(i + start));
			temp = temp.getNext();
		}
		return head.getNext();
	}

	public static void main(String[] args) {
		System.out.println(getListNode(new Integer[] { 1, 2, 3 }));
		System.out.println(getListNode(5));
	}
}
