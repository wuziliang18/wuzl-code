package org.wuzl.test.algorithm;

import java.util.Stack;

import org.wuzl.test.algorithm.support.TreeNode;

/**
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 * 
 * @author ziliang.wu
 *
 */
public class KthLargest {
    //TODO 
    public int kthLargest(TreeNode root, int k) {
        if (root == null || k <= 1) {
            return -1;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        boolean findEnd = false;
        while (!stack.isEmpty() && k > 0) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                stack.add(node.left);
                continue;
            }
            findEnd = true;
            if (--k == 0) {
                return node.val;
            }
            
        }
        return -1;
    }
}
