package org.wuzl.test.algorithm;

/**
 * 二叉搜索树与双向链表 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 * 
 * @author ziliang.wu
 *
 */
public class TreeToDoublyList {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }
        Node head = null;
        Node tail = null;
        while (root != null) {
            Node left = root.left;
            Node right = root.right;
        }
        return head.right;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
