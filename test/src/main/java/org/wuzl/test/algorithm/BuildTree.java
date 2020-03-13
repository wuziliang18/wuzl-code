package org.wuzl.test.algorithm;

import org.wuzl.test.comm.TreeNode;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。 例如，给出
 * 
 * 前序遍历 preorder = [3,9,20,15,7] 中序遍历 inorder = [9,3,15,20,7] 返回如下的二叉树：
 * 
 * 3 / \ 9 20 / \ 15 7
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * 
 * @author ziliang.wu
 *
 */
public class BuildTree {
    public static void main(String[] args) {

    }
    //TODO
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        if (preorder.length != inorder.length) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[0]);
        int j = 0;
        TreeNode temp = node;
        for (int i = 1; i < preorder.length; i++) {
            if (preorder[i] == inorder[j]) {
                temp.leftNode = new TreeNode(inorder[j]);
                j += 2;
                continue;
            }

        }
        return node;
    }
}
