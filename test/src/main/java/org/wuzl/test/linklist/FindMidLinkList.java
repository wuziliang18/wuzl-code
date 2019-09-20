package org.wuzl.test.linklist;

import java.util.Arrays;
import java.util.List;

import org.wuzl.test.comm.ListNode;
import org.wuzl.test.comm.ListNodeUtil;

public class FindMidLinkList {
	public double findMid(ListNode<Integer> linkList) {
		if (linkList == null) {
			return 0;
		}
		ListNode<Integer> linkList2 = linkList;
		while (linkList.hasNext()) {
			linkList2 = linkList2.getNext();
			if (!linkList2.hasNext()) {// 偶数
				return (linkList.getVal() + linkList.getNext().getVal()) / 2.0;
			}
			linkList2 = linkList2.getNext();
			linkList = linkList.getNext();
			if (!linkList2.hasNext()) {// 奇数
				return linkList.getVal();
			}
		}
		return linkList.getVal();
	}

	public static void main(String[] args) {
		FindMidLinkList obj = new FindMidLinkList();
		List<Integer> list = Arrays.asList(0, 1, 2, 3, 5, 10);
//		List<Integer> list = Arrays.asList(3);
		for (Integer count : list) {
			ListNode<Integer> listNode = ListNodeUtil.getListNode(count);
			System.out.print(listNode + " result:  ");
			System.out.println(obj.findMid(listNode));
		}
	}
}
