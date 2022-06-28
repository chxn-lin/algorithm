package com.sclin.class10;

import class12.Code03_IsBalanced;

import static class12.Code03_IsBalanced.generateRandomBST;
import static class12.Code03_IsBalanced.isBalanced1;

public class IsBalanceT {

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBT(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    /**
     * 给定一颗二叉树的头节点head，返回这颗二叉树是不是平衡二叉树
     */

    public static boolean isBT(Node head){
        return process(head).isBST;
    }

    public static Info process(Node head){
        if (head == null) {
            return new Info(true, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        boolean isBST = true;
        int high = Math.max(left.high, right.high) + 1;

        if (!left.isBST || !right.isBST) {
            isBST = false;
        }
        if (Math.abs(left.high - right.high) > 1) {
            isBST = false;
        }

        return new Info(isBST, high);
    }

    public static class Info{
        boolean isBST;
        int high;

        public Info(boolean is, int h){
            isBST = is;
            high = h;
        }
    }

}
