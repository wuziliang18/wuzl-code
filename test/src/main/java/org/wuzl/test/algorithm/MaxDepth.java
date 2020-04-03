package org.wuzl.test.algorithm;

import org.wuzl.test.algorithm.support.TreeNode;

/**
 * 二叉树的深度
 * 
 * @author ziliang.wu
 *
 */
public class MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    // private int maxDepth(TreeNode node) {
    // int maxDeptch = 0;
    // while (node != null) {
    //
    // }
    // return 0;
    // }
}
