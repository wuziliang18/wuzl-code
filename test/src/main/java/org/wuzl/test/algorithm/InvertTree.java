package org.wuzl.test.algorithm;

import org.wuzl.test.comm.TreeNode;

/**
 * 翻转一棵二叉树。 https://leetcode-cn.com/problems/invert-binary-tree/
 * 
 * @author ziliang.wu
 *
 */
public class InvertTree {
    // public static void main(String[] args) {
    // InvertTree obj=new InvertTree();
    // System.out.println(obj.invertTree(root));
    // }
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.getLeftNode();
        TreeNode right = root.getRightNode();
        root.setLeftNode(right);
        root.setRightNode(left);
        if (left != null) {
            invertTree(left);
        }
        if (right != null) {
            invertTree(right);
        }
        return root;
    }
}
