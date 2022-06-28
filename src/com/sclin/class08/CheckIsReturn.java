package com.sclin.class08;


import java.util.Stack;

public class CheckIsReturn {

    /**
     * 给定一个单链表的头节点head，请判断该链表是否为回文结构（回文结构指从尾部开始和头部开始的值都一致）
     * 要求：1、哈希表方式实现
     * 2、不使用哈希表实现
     */

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(method2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(method2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(method2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(method2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(method2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(method2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(method2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(method2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(method2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }

    // 使用集合
    public static boolean method1(Node head){
        if (head == null || head.next == null) {
            // 表示只有一个，那么直接返回true
            return true;
        }
        Node curNode = head;
        Stack<Node> stack = new Stack<>();
        while (curNode != null){
            stack.add(curNode);
            curNode = curNode.next;
        }
        boolean boo = true;
        while (!stack.isEmpty()) {
            if (stack.pop().value != head.value) {
                boo = false;
                break;
            }
            head = head.next;
        }

        return boo;
    }

    public static boolean method2(Node head){
        // 不使用集合
        if (head == null || head.next == null) {
            // 表示只有一个，那么直接返回true
            return true;
        }
        // 找出中点，偶数返回上中点
        Node midNode = QuickAndSlowIndex.method1(head);// 记录当前的节点
        midNode.next = null;
        Node endHead = reserseNode(midNode.next);
        // 对比每一个节点
        Node curEndHead = endHead;
        boolean isR = true;
        while (head != null && curEndHead != null) {
            if (head.value != curEndHead.value) {
                isR = false;
                break;
            }
            head = head.next;
            curEndHead = curEndHead.next;
        }
        // 还原node
        midNode.next = reserseNode(endHead);
        return isR;
    }

    // 反转链表
    private static Node reserseNode(Node revHead) {
        if (revHead == null || revHead.next == null) {
            return revHead;
        }
        Node curNode = revHead;
        Node lastNode = null;
        Node nextNode = null;
        while (curNode != null) {
            nextNode = curNode.next;
            curNode.next = lastNode;
            lastNode = curNode;
            curNode = nextNode;
        }
        return lastNode;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    // need O(1) extra space
    public static boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) { // find mid node
            n1 = n1.next; // n1 -> mid
            n2 = n2.next.next; // n2 -> end
        }
        // n1 中点


        n2 = n1.next; // n2 -> right part first node
        n1.next = null; // mid.next -> null
        Node n3 = null;
        while (n2 != null) { // right part convert
            n3 = n2.next; // n3 -> save next node
            n2.next = n1; // next of right node convert
            n1 = n2; // n1 move
            n2 = n3; // n2 move
        }
        n3 = n1; // n3 -> save last node
        n2 = head;// n2 -> left first node
        boolean res = true;
        while (n1 != null && n2 != null) { // check palindrome
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next; // left to mid
            n2 = n2.next; // right to mid
        }
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) { // recover list
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }
}
