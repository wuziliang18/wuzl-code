package org.wuzl.test.leetcode;

public class ListNodeUtil {
	public static <T> ListNode<T> getListNode(T[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		ListNode<T> result = new ListNode<T>(array[0]);
		ListNode<T> temp = result;
		for (int i = 1; i < array.length; i++) {
			temp.setNext(new ListNode<T>(array[i]));
			temp=temp.next;
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(getListNode(new Integer[]{1,2,3}));
	}
}
