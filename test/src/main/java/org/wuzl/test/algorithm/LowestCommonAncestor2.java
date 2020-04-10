package org.wuzl.test.algorithm;

import org.wuzl.test.algorithm.support.TreeNode;

/**
 * 二叉树的最近公共祖先
 * 
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 
 * https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 * 
 * @author ziliang.wu
 *
 */
public class LowestCommonAncestor2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        return root;
    }
}
