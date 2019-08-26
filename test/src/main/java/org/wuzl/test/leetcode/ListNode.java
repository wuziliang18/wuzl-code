package org.wuzl.test.leetcode;

public class ListNode<T> {
	T val;
	ListNode<T> next;

	ListNode() {
	}

	ListNode(T x) {
		val = x;
	}

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}

	public ListNode<T> getNext() {
		return next;
	}

	public void setNext(ListNode<T> next) {
		this.next = next;
	}

	public boolean hasNext() {
		return next != null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(val);
		ListNode<T> temp = next;
		while (temp != null) {
			sb.append("->").append(temp.val);
			temp = temp.next;
		}
		return sb.toString();
	}
}
