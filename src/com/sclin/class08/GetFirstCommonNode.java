package com.sclin.class08;

public class GetFirstCommonNode {

    /**
     * 给定两个可能有环也可能无环的单链表，
     * 头节点head1和head2,请实现一个函数，
     * 如果两个链表相交，请返回相交的第一个节点，
     * 如果不相交，返回null
     */

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }

    public static Node getIntersectNode(Node head1, Node head2){
        if (head1 == null || head2 == null) {
            return null;
        }
        Node lop1 = getLoop(head1);
        Node lop2 = getLoop(head2);
        // 如果都无环
        if (lop1 == null && lop2 == null) {
            return noLoop(head1, head2);
        } else if (lop1 != null && lop2 != null) {
            // 如果都有环
            return doubleLoop(head1, lop1, head2, lop2);
        }
        return null;
    }

    public static Node noLoop(Node head1, Node head2){
        int len = 0;
        Node cur = head1;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        cur = head2;
        while (cur != null) {
            len--;
            cur = cur.next;
        }
        cur = len > 0 ? head1 : head2;
        head2 = cur == head1 ? head2 : head1;
        for (int i = 0; i < Math.abs(len); i++) {
            cur = cur.next;
        }
        while (cur != head2) {
            cur = cur.next;
            head2 = head2.next;
        }
        return cur;
    }

    public static Node doubleLoop(Node head1, Node lop1, Node head2, Node lop2){
        // 分为两种情况
        if (lop1 == lop2)
            return lop1;
        // 1、相交于环内
        Node cur = lop1.next;
        while (cur != lop1) {
            if (cur == lop2) {
                return cur;
            }
            cur = cur.next;
        }
        // 2、相交于环外
        int len = 0;
        cur = head1;
        while (cur != lop1) {
            len++;
            cur = cur.next;
        }
        cur = head2;
        while (cur != lop2) {
            len--;
            cur = cur.next;
        }
        cur = len > 0 ? head1 : head2;
        head2 = cur == head1 ? head2 : head1;
        for (int i = 0; i < Math.abs(len); i++) {
            cur = cur.next;
        }
        while (cur != lop1 && cur != lop2) {
            if (cur == head2) {
                return cur;
            }
            cur = cur.next;
            head2 = head2.next;
        }
        return null;
    }

    // 获取第一个入环节点
    public static Node getLoop(Node head){
        if (head == null) {
            return null;
        }
        Node quick = head;
        Node slow = head;
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
            if (quick == slow) {
                break;
            }
        }
        if (quick != null && quick.next != null) {
            quick = head;
            while (quick != slow) {
                quick = quick.next;
                slow = slow.next;
            }
            return slow;
        }
        return null;
    }

}
