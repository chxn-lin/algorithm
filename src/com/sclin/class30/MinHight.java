package com.sclin.class30;

import class30.Code05_MinHeight.Node;

import java.util.List;

import static class30.Code05_MinHeight.generateRandomBST;
import static class30.Code05_MinHeight.minHeight2;

public class MinHight {

    public static int morris(Node head) {
        if (head == null) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        Node cur = head;
        Node mostRight = null;

        int level = 1;
        int rightSize = 0;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
                level++;
            } else {
                mostRight = cur.left;
                rightSize = 1;
                while (mostRight.right != cur && mostRight.right != null) {
                    mostRight = mostRight.right;
                    rightSize++;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    level++;
                } else if (mostRight.right == cur) {
                    if (mostRight.left == null) {
                        res = Math.min(res, level - 1);
                    }
                    mostRight.right = null;
                    cur = cur.right;
                    level -= rightSize;
                }

            }
        }
        level = 1;
        mostRight = head;
        while (mostRight.right != null) {
            mostRight = mostRight.right;
            level++;
        }
        if (mostRight.left == null && mostRight.right == null) {
            res = Math.min(res, level);
        }

        return res;
    }

    private static void printNode(Node node){
        System.out.println();
        process2(node);
        System.out.println();
    }

    private static void process2(Node node){
        if (node == null) {
            return ;
        }
        System.out.print(node.val + " ");
        process2(node.left);
        process2(node.right);
    }

    public static void main(String[] args) {

//        Node node = new Node(1);
//        node.left = new Node(2);
//        node.left.right = new Node(3);
//        node.left.right.left = new Node(4);
//        node.left.right.left.left = new Node(5);
//        node.left.right.left.left.left = new Node(7);
//        System.out.println(morris(node));

        int treeLevel = 7;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(treeLevel, nodeMaxValue);
            int ans1 = morris(head);
            int ans2 = minHeight2(head);
            if (ans1 != ans2) {
                printNode(head);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish!");
    }

}
