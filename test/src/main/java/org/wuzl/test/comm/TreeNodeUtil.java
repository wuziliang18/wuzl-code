package org.wuzl.test.comm;

import java.util.Arrays;
import java.util.List;

/**
 * wuzl
 * 
 * @author ziliang.wu
 *
 */
public class TreeNodeUtil {
	public static <T> TreeNode<T> getTreeByList(List<T> list) {
		return addSort(list);
	}

	/**
	 * 按顺序插入
	 * 
	 * @param list
	 * @return
	 */
	public static <T> TreeNode<T> addSort(List<T> list) {
		TreeNode<T> root = null;
		ListNode<TreeNode<T>> parentLinkHeader = new ListNode<>();
		ListNode<TreeNode<T>> parentLinkTemp = parentLinkHeader;
		ListNode<TreeNode<T>> childLinkHeader = new ListNode<>();
		ListNode<TreeNode<T>> childLinkTemp = childLinkHeader;
		for (T value : list) {
			TreeNode<T> node = new TreeNode<T>(value);
			if (root == null) {
				root = node;
				parentLinkTemp.next = new ListNode<>(node);
				continue;
			}
			childLinkTemp.next = new ListNode<>(node);
			childLinkTemp = childLinkTemp.next;
			ListNode<TreeNode<T>> temp = parentLinkTemp.next;
			if (setLeaf(temp.getVal(), node)) {
				if (temp.getVal().hasRight()) {
					parentLinkTemp = parentLinkTemp.next;
					if (!parentLinkTemp.hasNext()) {
						parentLinkHeader.next = childLinkHeader.next;
						parentLinkTemp = parentLinkHeader;
						childLinkHeader.next = null;
						childLinkTemp = childLinkHeader;
					}
				}
			}

		}
		return root;
	}

	private static <T> boolean setLeaf(TreeNode<T> temp, TreeNode<T> node) {
		if (temp == null) {
			return false;
		}
		// System.out.print(temp.getData()+">>>>");
		if (!temp.hasLeft()) {
			// System.out.println("lfet:"+node.getData());
			temp.setLeftNode(node);
			return true;
		}
		if (!temp.hasRight()) {
			// System.out.println("right:"+node.getData());
			temp.setRightNode(node);
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		TreeNode<Integer> tree = getTreeByList(
				Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 }));
		System.out.println(tree);
	}
}
