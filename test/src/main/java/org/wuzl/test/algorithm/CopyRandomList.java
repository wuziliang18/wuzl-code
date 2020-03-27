package org.wuzl.test.algorithm;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {
    public static void main(String[] args) {
        CopyRandomList obj = new CopyRandomList();
        Node head = new Node(7);
        head.next = new Node(13);
        head.next.random = new Node(0);
        ;
        System.out.println(obj.copyRandomList(head));
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        while (head != null) {
            Node node = getNode(map, head);
            node.next = getNode(map, head.next);
            node.random = getNode(map, head.random);
            map.put(head, node);
            head = head.next;
        }

        return map.get(head);
    }

    private Node getNode(Map<Node, Node> map, Node node) {
        if (node == null)
            return null;
        if (!map.containsKey(node)) {
            Node tmp = new Node(node.val);
            map.put(node, tmp);
        }
        return map.get(node);
    }
    // 做复杂了
    // public Node copyRandomList(Node head) {
    // if (head == null) {
    // return null;
    // }
    // Node result = new Node(head.val);
    // Map<Integer, List<Node>> map = new HashMap<Integer, List<Node>>();
    // addMap(map, head, result);
    // result.next = new Node(head.next.val);
    //
    // Node temp = result.next;
    // Node headTemp = head.next;
    // while (headTemp != null) {
    // addMap(map, headTemp, temp);
    // if (headTemp.next == null) {
    // break;
    // }
    // if (map.get(headTemp.val) != null) {
    // for (Node node : map.get(headTemp.val)) {
    // node.random = temp;
    // }
    // }
    // headTemp = headTemp.next;
    // temp.next = new Node(headTemp.val);
    // temp = temp.next;
    // }
    // int i = 0;
    // temp = result;
    // while (!map.isEmpty() && temp != null) {
    // if (map.get(i) != null) {
    // for (Node node : map.get(i)) {
    // node.random = temp;
    // }
    // }
    // temp = temp.next;
    // i++;
    // }
    // return result;
    // }
    //
    // private void addMap(Map<Integer, List<Node>> map, Node node, Node newNode) {
    // Node random = node.random;
    // if (random != null) {
    // List<Node> list = map.get(random.val);
    // if (list == null) {
    // list = new LinkedList<Node>();
    // map.put(random.val, list);
    // }
    // list.add(newNode);
    // }
    // }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    @Override
    public String toString() {
        String s = random == null ? "null" : String.valueOf(random.val);
        return "Node [val=" + val + ", next=" + next + ", random=" + s + "]";
    }

}