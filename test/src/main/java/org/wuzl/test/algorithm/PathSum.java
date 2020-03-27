package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.wuzl.test.algorithm.support.TreeNode;

public class PathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);

        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        System.out.println(new PathSum().pathSum(root, 22));
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNodeHolder> queue = new LinkedList<TreeNodeHolder>();
        queue.add(new TreeNodeHolder(root, null, root.val, 0));
        while (!queue.isEmpty()) {
            TreeNodeHolder holder = queue.poll();
            TreeNode node = holder.getNode();
            if (node.left == null && node.right == null && holder.getValue() == sum) {
                result.add(getList(holder));
                continue;
            }
            if (node.left != null) {
                queue.add(new TreeNodeHolder(node.left, holder, node.left.val + holder.getValue(),
                        holder.getLevel() + 1));
            }
            if (node.right != null) {
                queue.add(new TreeNodeHolder(node.right, holder, node.right.val + holder.getValue(),
                        holder.getLevel() + 1));
            }
        }
        return result;
    }

    private List<Integer> getList(TreeNodeHolder holder) {
        int level = holder.getLevel();
        List<Integer> list = new ArrayList<>(level);
        while (holder != null) {
            list.add(0, holder.getNode().val);// 这块会慢 list不支持第一次直接在最后插入
            holder = holder.getPre();
        }
        return list;
    }

    private static class TreeNodeHolder {
        private final TreeNode node;
        private final TreeNodeHolder pre;
        private final int value;
        private final int level;

        public TreeNodeHolder(TreeNode node, TreeNodeHolder pre, int value, int level) {
            super();
            this.node = node;
            this.pre = pre;
            this.value = value;
            this.level = level;
        }

        public TreeNode getNode() {
            return node;
        }

        public TreeNodeHolder getPre() {
            return pre;
        }

        public int getValue() {
            return value;
        }

        public int getLevel() {
            return level;
        }

    }
}
