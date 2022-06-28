package com.sclin.class02;

public class DeleteOneKey {

    // 把给定值都删除

    public static void main(String[] args) {
        DoubleNode headNode = new DoubleNode(1);
        DoubleNode node2 = new DoubleNode(2);
        DoubleNode node3 = new DoubleNode(3);
        DoubleNode node4 = new DoubleNode(4);
        DoubleNode node5 = new DoubleNode(3);
        headNode.nextNode = node2;
        node2.nextNode = node3;
        node2.lastNode = headNode;

        node3.nextNode = node4;
        node3.lastNode = node2;

        node4.nextNode = node5;
        node4.lastNode = node3;

        node5.lastNode = node4;

        System.out.println("双向链表处理前：" + headNode);
        headNode = deleteNode(headNode, 3);
        System.out.println("双向链表处理后：" + headNode);
    }

    public static DoubleNode deleteNode(DoubleNode head, int value) {
        // 第一步，找出第一个，不是需要删除值的节点
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head.value == value) {
            head.lastNode = pre;
            pre = head;
            head = head.nextNode;
        }
        // 遍历一遍节点，删除值为value的
        DoubleNode cur = head;
        while (cur != null) {
            next = cur.nextNode;
            if (cur.value == value) {// 相等，那么就需要删除该节点
                pre = cur.lastNode;
                cur = next;
                pre.nextNode = next;
                if (cur != null)
                    cur.lastNode = pre;
            } else {
                // 到下一个节点
                pre = cur;
                cur = next;
            }
            cur = next;
        }

        return head;
    }
}
