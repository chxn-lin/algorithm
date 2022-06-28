package com.sclin.class10;

import static class12.Code01_IsCBT.generateRandomBST;
import static class12.Code05_MaxSubBSTSize.maxSubBSTSize1;
import static class13.Code02_MaxSubBSTHead.maxSubBSTHead1;

public class FindSubTreeAndNum {

    // 给定一个头节点head，返回这颗树上最大的搜索二叉树的节点个数（扩展：获得最大个数的头节点）

    public static int getMaxNum(Node head){
        /*
            1、以当前节点为头节点，都为搜索二叉树

            2、当前不符合搜索二叉树
                左树最大的二叉树节点个数
                右树最大的二叉树节点个数
         */
        if (head == null) {
            return 0;
        }
        return process(head).maxSBTSize;
    }

    public static Info process(Node head){
        if (head == null) {
            return null;
        }
        Info left = process(head.left);
        Info right = process(head.right);
        // 需要考虑left和right为空的情况

        int curSize = 1 + (left != null ? left.curSize : 0) + (right != null ? right.curSize : 0);
        int max = head.value;
        int min = head.value;
        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
        }
        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
        }

        boolean isSBT = true;
        if (left != null) {
            if (!left.isSBT || left.max >= head.value) {
                isSBT = false;
            }
        }
        if (right != null) {
            if (!right.isSBT || right.min <= head.value) {
                isSBT = false;
            }
        }

        int maxSubSize = Math.max(left != null ? left.maxSBTSize : 1, right != null ? right.maxSBTSize : 1);
        maxSubSize = isSBT ? (left != null ? left.curSize : 0) + (right != null ? right.curSize : 0) + 1 : maxSubSize;

        return new Info(isSBT,curSize, maxSubSize, max, min);
    }

    public static class Info{
        boolean isSBT;
        int curSize;
        int maxSBTSize;
        int max;
        int min;

        public Info(boolean isSBT, int curSize, int maxSBTSize, int max, int min) {
            this.isSBT = isSBT;
            this.curSize = curSize;
            this.maxSBTSize = maxSBTSize;
            this.max = max;
            this.min = min;
        }
    }

    public static void main(String[] args) {
//        int maxLevel = 4;
//        int maxValue = 100;
//        int testTimes = 1000000;
//        for (int i = 0; i < testTimes; i++) {
//            Node head = generateRandomBST(maxLevel, maxValue);
//            if (maxSubBSTSize1(head) != getMaxNum(head)) {
//                System.out.println("Oops!");
//            }
//        }
//        System.out.println("finish!");

            int maxLevel = 4;
            int maxValue = 100;
            int testTimes = 1000000;
            for (int i = 0; i < testTimes; i++) {
                Node head = generateRandomBST(maxLevel, maxValue);
                if (maxSubBSTHead1(head) != getMaxSizeTree(head)) {
                    System.out.println("Oops!");
                }
            }
            System.out.println("finish!");
    }

    public static Node getMaxSizeTree(Node head){
        /*
            1、以当前节点为头节点，都为搜索二叉树

            2、当前不符合搜索二叉树
                左树最大的二叉树节点个数
                右树最大的二叉树节点个数
         */
        if (head == null) {
            return null;
        }
        Node left = getMaxSizeTree(head.left);
        Node right = getMaxSizeTree(head.right);
        Node maxNode = getSize(left) >= getSize(right) ? left : right;

        return maxNode;
    }

    public static int getSize(Node head) {
        if (head == null) {
            return 0;
        }
        return getSize(head.left) + getSize(head.right);
    }

}
