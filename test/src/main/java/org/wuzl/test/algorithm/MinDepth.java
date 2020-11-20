package org.wuzl.test.algorithm;

import org.wuzl.test.algorithm.support.TreeNode;

/**
 * 二叉树的最小深度
 * 
 * 给定一个二叉树，找出其最小深度。
 * 
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 示例:
 * 
 * 给定二叉树 [3,9,20,null,null,15,7],
 * 
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 
 * @author ziliang.wu
 *
 */
public class MinDepth {
    int minDepth;

    public int minDepth(TreeNode root) {
        minDepth = Integer.MAX_VALUE;
        return getDepth(root, 0);
    }

    public int getDepth(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }
        if (node.left == null && node.right == null) {
            return depth + 1;
        }
        if (depth + 1 >= minDepth) {
            return minDepth;
        }
        return Math.min(getDepth(node.left, depth + 1), getDepth(node.right, depth + 1));
    }
}
