package org.wuzl.test.algorithm;

import java.util.LinkedList;
import java.util.Queue;

import org.wuzl.test.algorithm.support.TreeNode;

/**
 * 给出一个完全二叉树，求出该树的节点个数。
 * 
 * 说明：
 * 
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * 
 * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 * 
 * @author ziliang.wu
 *
 */
public class CountNodes {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            count++;
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return count;
    }

}
