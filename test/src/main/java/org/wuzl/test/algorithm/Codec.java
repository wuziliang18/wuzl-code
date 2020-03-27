package org.wuzl.test.algorithm;

import java.util.LinkedList;
import java.util.Queue;

import org.wuzl.test.algorithm.support.TreeNode;

/**
 * 序列化二叉树 请实现两个函数，分别用来序列化和反序列化二叉树。
 * 
 * https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/
 * 
 * @author ziliang.wu
 *
 */
public class Codec {
    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode root = codec.deserialize("[1,2,3,null,null,4,5]");
        System.out.println(codec.serialize(root));
    }

    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        sb.append("[").append(root.val);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                sb.append(",").append(node.left.val);
                queue.add(node.left);
            } else {
                sb.append(",null");
            }
            if (node.right != null) {
                sb.append(",").append(node.right.val);
                queue.add(node.right);
            } else {
                sb.append(",null");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.length() <= 2 || data.charAt(0) != '[' || data.charAt(data.length() - 1) != ']') {
            return null;
        }
        data = data.substring(1, data.length() - 1);
        String[] array = data.split(",");
        int length = array.length;
        TreeNode root = new TreeNode(Integer.parseInt(array[0]));
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty() && index < length) {
            TreeNode node = queue.poll();
            if (index < length) {
                if (!array[index].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(array[index]));
                    queue.add(node.left);
                }
                index++;
            }
            if (index < length) {
                if (!array[index].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(array[index]));
                    queue.add(node.right);
                }
                index++;
            }
        }
        return root;
    }
}
