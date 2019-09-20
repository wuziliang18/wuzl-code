package org.wuzl.test.comm;

import java.util.Arrays;
import java.util.List;
/**
 * wuzl未完成
 * @author ziliang.wu
 *
 */
public class TreeNodeUtil {
	public static <T> TreeNode<T> getTreeByList(List<T> list) {
		TreeNode<T> root = null;
		TreeNode<T> temp = root;
		for (T value : list) {
			TreeNode<T> node = new TreeNode<T>(value);
			if (temp == null) {
				root = node;
				temp = root;
				continue;
			}
			if (!temp.hasLeft()) {
				temp.setLeftNode(node);
				continue;
			}
			if (!temp.hasRight()) {
				temp.setRightNode(node);
				continue;
			}

		}
		return root;
	}

	public static void main(String[] args) {
		System.out.println(
				getTreeByList(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 })));
	}
}
