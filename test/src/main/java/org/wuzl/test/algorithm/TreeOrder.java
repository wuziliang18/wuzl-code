package org.wuzl.test.algorithm;

import java.util.Arrays;

import org.wuzl.test.comm.ListNode;
import org.wuzl.test.comm.TreeNode;
import org.wuzl.test.comm.TreeNodeUtil;

/**
 * 树遍历
 * 
 * @author ziliang.wu
 *
 */
public class TreeOrder {
	/**
	 * 前序遍历
	 * 
	 * @param treeNode
	 */
	public static <T> void preOrder(TreeNode<T> treeNode) {
		if (treeNode != null) {
			System.out.print(treeNode.getData() + ",");
			preOrder(treeNode.getLeftNode());
			preOrder(treeNode.getRightNode());
		}
	}

	/**
	 * 中序遍历
	 * 
	 * @param treeNode
	 */
	public static <T> void inOrder(TreeNode<T> treeNode) {
		if (treeNode != null) {
			inOrder(treeNode.getLeftNode());
			System.out.print(treeNode.getData() + ",");
			inOrder(treeNode.getRightNode());
		}
	}

	/**
	 * 后序遍历
	 * 
	 * @param treeNode
	 */
	public static <T> void postOrder(TreeNode<T> treeNode) {
		if (treeNode != null) {
			postOrder(treeNode.getLeftNode());
			postOrder(treeNode.getRightNode());
			System.out.print(treeNode.getData() + ",");

		}
	}

	/**
	 * 层遍历
	 * 
	 * @param treeNode
	 */
	public static <T> void levelOrder(TreeNode<T> treeNode) {
		ListNode<TreeNode<T>> header = new ListNode<>();
		ListNode<TreeNode<T>> temp = header;
		// ListNode<TreeNode<T>> childTemp = new ListNode<>();
		header.setNext(new ListNode<TreeNode<T>>(treeNode));
		while (temp.hasNext()) {
			temp = temp.getNext();
			TreeNode<T> node = temp.getVal();
			System.out.print(node.getData()+",");
			if (node.hasLeft()) {
				header.getNext().setNext(new ListNode<TreeNode<T>>(node.getLeftNode()));
				header=header.getNext();
			}
			if (node.hasRight()) {
				header.getNext().setNext(new ListNode<TreeNode<T>>(node.getRightNode()));
				header=header.getNext();
			}

		}
	}

	public static void main(String[] args) {
		TreeNode<Integer> treeNode = TreeNodeUtil
				.getTreeByList(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 }));
		preOrder(treeNode);
		System.out.println(">>>>>>");
		inOrder(treeNode);
		System.out.println(">>>>>>");
		postOrder(treeNode);
		System.out.println(">>>>>>");
		levelOrder(treeNode);
	}
}
