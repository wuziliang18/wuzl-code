package org.wuzl.test.leetcode;

import org.wuzl.test.comm.ListNode;
import org.wuzl.test.comm.ListNodeUtil;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 * 示例：
 * 
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8 原因：342 + 465 = 807
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author ziliang.wu
 *
 */
public class AddTwoNums {
	public static void main(String[] args) {
		ListNode<Integer> l1 = ListNodeUtil.getListNode(new Integer[] { 2, 4, 3 });
		ListNode<Integer> l2 = ListNodeUtil.getListNode(new Integer[] { 5, 6, 4 });
		ListNode<Integer> result = solution(l1, l2);
		System.out.println(result.toString());

		l1 = ListNodeUtil.getListNode(new Integer[] { 2, 4, 7 });
		l2 = ListNodeUtil.getListNode(new Integer[] { 5, 6, 2 });
		result = solution(l1, l2);
		System.out.println(result.toString());

		l1 = ListNodeUtil.getListNode(new Integer[] { 2, 4, 7 });
		l2 = ListNodeUtil.getListNode(new Integer[] { 5, 6, 4, 5 });
		result = solution(l1, l2);
		System.out.println(result.toString());
	}

	public static ListNode<Integer> solution(ListNode<Integer> l1, ListNode<Integer> l2) {
		int temp = 0;
		ListNode<Integer> head = new ListNode<>();
		ListNode<Integer> nowNode = head;
		while (l1 != null || l2 != null) {
			int firstValue = l1 == null ? 0 : l1.getVal();
			int secValue = l2 == null ? 0 : l2.getVal();
			int sum = firstValue + secValue + temp;
			temp = sum / 10;
			int value = sum % 10;
			nowNode.setNext(new ListNode<Integer>(value));
			nowNode = nowNode.next;
			if (l1 != null) {
				l1 = l1.next;
			}
			if (l2 != null) {
				l2 = l2.next;
			}
		}
		if (temp > 0) {
			nowNode.setNext(new ListNode<Integer>(temp));
		}
		return head.next;
	}
}
