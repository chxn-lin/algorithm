package com.sclin.class10;

import java.util.LinkedList;
import java.util.Queue;

import static class12.Code01_IsCBT.generateRandomBST;
import static class12.Code01_IsCBT.isCBT2;

public class IsCBT {

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT(head) != isCBTByDP(head)) {

                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
//        Node a = new Node(1);
//        Node b = new Node(2);
//        Node c = new Node(3);
//        Node d = new Node(4);
//        Node e = new Node(5);
//        Node f = new Node(6);
////        Node g = new Node(7);
////
//        a.left = b;
//        a.right = c;
//        b.left = d;
////        b.right = e;
//        c.left = f;
////        c.right = g;
//
////        d.left = f;
//
//        System.out.println(isCBTByDP(a));
    }

    // 判断二叉树是否是完全二叉树

    public static boolean isCBT(Node head){
        if (head == null) {
            return true;
        }
        Node cur = null;
        boolean isStartEmpty = false;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            cur = queue.poll();

            if (isStartEmpty && (cur.left != null || cur.right != null)) {
                return false;
            }

            if (cur.left == null && cur.right != null) {
                return false;
            }
            if (cur.right == null || cur.left == null) {
                isStartEmpty = true;
            }

            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return true;
    }

    // 递归实现
    public static boolean isCBTByDP(Node head){
        return process(head).isCBT;
    }

    public static Info process(Node head){
        if (head == null) {
            return new Info(true, true, 0);
        }
        boolean isCBT = false;
        Info left = process(head.left);
        Info right = process(head.right);

        int h = Math.max(left.h, right.h) + 1;
        boolean isFull = left.isFull && right.isFull && left.h == right.h;

        /*
            1、如果是满二叉树，那么一定是完全二叉树
            2、如果不是满的，那么前提是左右都是完全二叉树，并且左树高度不能比右树高度低
                左树满
                    右树如果满，一定是高度少一的
                    如果不满，那么高度要一样
                左树不满
                    右树高度一定要要满的，并且高度少一
         */
        if (isFull) {
            isCBT = true;
        } else {
            if (left.isCBT && right.isCBT && left.h >= right.h) {
                if (left.isFull) {
                    if (right.isFull) {
                        if (left.h - right.h == 1) {
                            isCBT = true;
                        }
                    } else {
                        if (left.h == right.h) {
                            isCBT = true;
                        }
                    }
                } else {
                    if (right.isFull && left.h - right.h == 1) {
                        isCBT = true;
                    }
                }
            }
        }

        return new Info(isCBT, isFull, h);
    }

    public static class Info {
        boolean isCBT;
        boolean isFull;
        int h;

        public Info(boolean c, boolean isF,int h){
            isCBT = c;
            isFull = isF;
            this.h = h;
        }

    }

}
