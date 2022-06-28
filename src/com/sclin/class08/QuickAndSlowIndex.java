package com.sclin.class08;

public class QuickAndSlowIndex {

    /**
     * 1、输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     * 2、输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     * 3、输入链表头节点，奇数长度返回中点的前一个，偶数长度返回上中点前一个
     * 4、输入链表头节点，奇数长度返回中点的前一个，偶数长度返回下中点前一个
     */
    public static Node method1(Node head){
        if (head == null) {
            return null;
        }
        Node qNode = head;
        Node sNode = head;
        while(qNode != null && qNode.next != null){
            qNode = qNode.next;
            qNode = qNode.next;
            if (qNode == null) {
                break;
            }
            sNode = sNode.next;
        }
        return sNode;
    }

    public static Node method2(Node head){
        if (head == null) {
            return null;
        }
        Node qNode = head;
        Node sNode = head;
        while(qNode != null && qNode.next != null){
            qNode = qNode.next;
            qNode = qNode.next;
            sNode = sNode.next;
        }
        return sNode;
    }

    public static Node method3(Node head){
        if (head == null) {
            return null;
        }
        Node qNode = head;
        Node sNode = head;
        Node lastNode = null;
        while(qNode != null && qNode.next != null){
            qNode = qNode.next;
            qNode = qNode.next;
            if (qNode == null) {
                break;
            }
            lastNode = sNode;
            sNode = sNode.next;
        }
        return lastNode;
    }

    public static Node method4(Node head){
        if (head == null) {
            return null;
        }
        Node qNode = head;
        Node sNode = head;
        Node lastNode = null;
        while(qNode != null && qNode.next != null){
            qNode = qNode.next;
            qNode = qNode.next;
            lastNode = sNode;
            sNode = sNode.next;
        }
        return lastNode;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Node head = new Node(1);
//        head.next = new Node(2);
//        head.next.next = new Node(3);
//        head.next.next.next = new Node(4);
//        head.next.next.next.next = new Node(5);

        Node head2 = new Node(1);
        head2.next = new Node(2);
//        head2.next.next = new Node(3);
//        head2.next.next.next = new Node(4);

        System.out.println("为单数时，method1的值：" + method1(head));
        System.out.println("为偶数时，method1的值：" + method1(head2));

        System.out.println("为单数时，method2的值：" + method2(head));
        System.out.println("为偶数时，method2的值：" + method2(head2));

        System.out.println("为单数时，method3的值：" + method3(head));
        System.out.println("为偶数时，method3的值：" + method3(head2));

        System.out.println("为单数时，method4的值：" + method4(head));
        System.out.println("为偶数时，method4的值：" + method4(head2));
        
    }
}
