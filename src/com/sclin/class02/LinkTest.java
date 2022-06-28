package com.sclin.class02;

public class LinkTest {

    // 单双链表反转

    public static void main(String[] args) {
//        SingleNode headNode = new SingleNode(1);
//        SingleNode node2 = new SingleNode(2);
//        SingleNode node3 = new SingleNode(3);
//        SingleNode node4 = new SingleNode(4);
//        SingleNode node5 = new SingleNode(5);
//        headNode.nextNode = node2;
//        node2.nextNode = node3;
//        node3.nextNode = node4;
//        node4.nextNode = node5;
//
//        System.out.println("单链表反转前：" + headNode);
//        System.out.println("单链表反转后：" + reserveNode(headNode));

        DoubleNode headNode = new DoubleNode(1);
        DoubleNode node2 = new DoubleNode(2);
        DoubleNode node3 = new DoubleNode(3);
        DoubleNode node4 = new DoubleNode(4);
        DoubleNode node5 = new DoubleNode(5);
        headNode.nextNode = node2;
        node2.nextNode = node3;
        node2.lastNode = headNode;

        node3.nextNode = node4;
        node3.lastNode = node2;

        node4.nextNode = node5;
        node4.lastNode = node3;

        node5.lastNode = node4;

        System.out.println("双向链表反转前：" + headNode);

        headNode = reserveNode(headNode);
        System.out.println("双向链表反转后：" + headNode);
    }

    public static SingleNode reserveNode(SingleNode headNode) {
        SingleNode pre = null;
        SingleNode next = null;

        while (headNode != null) {
            next = headNode.nextNode;
            headNode.nextNode = pre;
            pre = headNode;
            headNode = next;
        }

        return pre;
    }

    public static DoubleNode reserveNode(DoubleNode headNode) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while(headNode != null) {
            next = headNode.nextNode;
            headNode.nextNode = pre;
            headNode.lastNode = next;
            pre = headNode;
            headNode = next;
        }

        return pre;
    }

}
