package org.wuzl.test.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.wuzl.test.algorithm.support.TreeNode;

/**
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 * 
 * @author ziliang.wu
 *
 */
public class LevelOrder {
    /**
     * 第二次写
     * 
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> rows = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                rows.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(rows);
        }
        return result;
    }

    public int[] levelOrderV1(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        int count = 0;
        int maxCount = 1;
        int level = 1;
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.add(root);
        List<Integer> temp = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            count++;
            temp.add(node.val);
            if (count == maxCount) {
                result.add(temp);
                temp = new ArrayList<>();
                level++;
                maxCount = (int) Math.pow(2, level);
                count = 0;
            }
            if (node.left != null) {
                queue.add(node.left);
            } else {
                count++;
            }
            if (node.right != null) {
                queue.add(node.right);
            } else {
                count++;
            }
        }
        if (count != 0) {
            result.add(temp);
        }
        return result;
    }

    public List<List<Integer>> levelOrder3(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        boolean left = true;
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> temp = new ArrayList<>();
            result.add(temp);

            if (left) {
                left = false;
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.pollFirst();
                    temp.add(node.val);
                    if (node.left != null) {
                        deque.addLast(node.left);
                    }
                    if (node.right != null) {
                        deque.addLast(node.right);
                    }
                }
            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.pollLast();
                    temp.add(node.val);
                    if (node.right != null) {
                        deque.addFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.addFirst(node.left);
                    }

                }
                left = true;
            }

            // for (int i = 0; i < size; i++) {
            // TreeNode node = deque.poll();
            //
            // if (node.left != null) {
            // deque.push(node.left);
            // }
            // if (node.right != null) {
            // deque.push(node.right);
            // }
            // }
            // if (left) {
            // left = false;
            // } else {
            // Collections.reverse(temp);
            // left = true;
            // }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(new LevelOrder().levelOrder3(root));
    }
}
