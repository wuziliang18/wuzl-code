package org.wuzl.test.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉搜索树的后序遍历序列 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。//
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 * 
 * @author ziliang.wu
 *
 */
public class VerifyPostorder {
    public static void main(String[] args) {
        VerifyPostorder obj = new VerifyPostorder();
        // System.out.println(obj.verifyPostorder(new int[] { 1, 6, 3, 2, 5 }));
        // System.out.println(obj.verifyPostorder(new int[] { 1, 3, 2, 6, 5 }));
        // System.out.println(obj.verifyPostorder(new int[] { 4, 8, 6, 12, 16, 14, 10 }));
        // System.out.println(obj.verifyPostorder(new int[] { 1, 2, 3, 4, 5 }));
        System.out.println(obj.verifyPostorder(new int[] { 5, 4, 3, 2, 1 }));
        // System.out.println(obj.verifyPostorder(new int[] { 4, 8, 6, 12, 16, 14, 10 }));
        // System.out.println(obj.verifyPostorder(new int[] { 4, 6, 7, 5 }));
    }

    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length <= 2) {
            return true;
        }

        return verify(postorder, 0, postorder.length - 1);
    }

    private boolean verify(int[] postorder, int i, int j) {
        if (i >= j)
            return true;
        int root = postorder[i];
        int k = i;
        while (k < j && postorder[k] < root) {
            k++;
        }
        for (int index = k; index < j; index++) {
            if (postorder[index] < root)
                return false;
        }

        return verify(postorder, i, k - 1) && verify(postorder, k, j - 1);
    }
}
