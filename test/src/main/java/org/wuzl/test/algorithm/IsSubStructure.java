package org.wuzl.test.algorithm;

import org.wuzl.test.algorithm.support.TreeNode;

/**
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * 
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。 https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
 * 
 * @author ziliang.wu
 *
 */
public class IsSubStructure {
    public boolean isSubStructure(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return false;
        }
        boolean check = isSubStructureChild(a, b);
        if (check) {
            return check;
        }
        return isSubStructure(a.right, b) || isSubStructure(a.left, b);
    }

    public boolean isSubStructureChild(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return false;
        }
        if (a.val == b.val) {
            if (b.left == null && b.right == null) {
                return true;
            }
            if (b.left == null && b.right != null) {
                if (isSubStructureChild(a.right, b.right)) {
                    return true;
                }
            } else if (b.left != null && b.right == null) {
                if (isSubStructureChild(a.left, b.left)) {
                    return true;
                }
            } else {
                if (isSubStructureChild(a.right, b.right) && isSubStructureChild(a.left, b.left)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

}
