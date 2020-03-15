package org.wuzl.test.comm;

import java.io.Serializable;

public class TreeNode<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public TreeNode<T> leftNode;
    public TreeNode<T> rightNode;
    public T data;

    public TreeNode() {
    }

    public TreeNode(T data) {
        super();
        this.data = data;
    }

    public TreeNode<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode<T> leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode<T> rightNode) {
        this.rightNode = rightNode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean hasLeft() {
        return leftNode != null;
    }

    public boolean hasRight() {
        return rightNode != null;
    }

    public boolean hasChildren() {
        return hasLeft() && hasRight();
    }

    @Override
    public String toString() {
        return "TreeNode [data=" + data + "]";
    }

}
