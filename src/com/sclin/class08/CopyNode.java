package com.sclin.class08;

import java.util.HashMap;

public class CopyNode {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        // 1->2->3->4
        // 1->3->null->2
        head.random = head.next.next;// 3
        head.next.random = null; // null
        head.next.next.random = head.next;// 2

        System.out.println(head);

        Node node = method2(head);
        System.out.println(node);
    }

    // 使用容器
    public static Node method1(Node head){
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next =  map.get(cur.next);
            map.get(cur).random =  map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    // 不使用容器
    public static Node method2(Node head){
        if (head == null) {
            return null;
        }
        // 复制节点
        Node cur = head;
        Node newNode;
        Node nextNode;
        while (cur != null) {
            newNode = new Node(cur.val);
            nextNode = cur.next;

            cur.next = newNode;
            newNode.next = nextNode;
            cur = nextNode;
        }
        cur = head;
        Node copyHead = head.next;
        while (cur != null) {
            newNode = cur.next;
            nextNode = newNode.next;

            newNode.random = cur.random == null ? null : cur.random.next;
            cur = nextNode;
        }
        // 分离节点
        cur = head;
        while (cur != null) {
            newNode = cur.next;
            nextNode = newNode.next;

            cur.next = nextNode;
            newNode.next = nextNode == null ? null : nextNode.next;

            cur = nextNode;
        }
        return copyHead;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

}




