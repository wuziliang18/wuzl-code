package org.wuzl.test.algorithm;

import java.util.LinkedList;
import java.util.Queue;

import org.wuzl.test.algorithm.support.TreeNode;

/**
 * 另一个树的子树
 * 
 * 
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
 * 
 * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 * 
 * @author ziliang.wu
 *
 */
public class IsSubtree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return t == null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            boolean checkResult = this.check(node, t);
            if (checkResult) {
                return true;
            }
        }
        return false;
    }

    private boolean check(TreeNode node, TreeNode t) {
        if (node == null) {
            return t == null;
        }
        if (t == null) {
            return false;
        }
        if (node.val == t.val) {
            return this.check(node.left, t.left) && this.check(node.right, t.right);
        }
        return false;
    }
}
