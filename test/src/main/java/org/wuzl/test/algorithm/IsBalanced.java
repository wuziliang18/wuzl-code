package org.wuzl.test.algorithm;

import org.wuzl.test.algorithm.support.TreeNode;

/**
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树
 * 
 * @author ziliang.wu
 *
 */
public class IsBalanced {
    int min = 0;
    int max = 0;

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(depth(root.left) - depth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int depth(TreeNode node) {
        return node == null ? 0 : Math.max(depth(node.left), depth(node.right)) + 1;
    }
}
