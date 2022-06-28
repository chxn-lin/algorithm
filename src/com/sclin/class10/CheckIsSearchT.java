package com.sclin.class10;

import static class12.Code02_IsBST.generateRandomBST;
import static class12.Code02_IsBST.isBST2;

public class CheckIsSearchT {

    // 判断二叉树是否是搜索二叉树

    public static boolean isSBT(Node head){
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    public static Info process(Node head){
        if (head == null) {
            return null;
        }
        Info left = process(head.left);
        Info right = process(head.right);
        boolean isBST = true;
        int max = head.value;
        int min = head.value;
        if ((left != null && !left.isBST) || (right != null && !right.isBST)) {
            isBST = false;
        }
        if (left != null) {
            if (head.value <= left.max) {
                isBST = false;
            }
            max = Math.max(left.max, max);
            min = Math.min(left.min, min);
        }
        if (right != null) {
            if (head.value >= right.min) {
                isBST = false;
            }
            max = Math.max(right.max, max);
            min = Math.min(right.min, min);
        }

        return new Info(isBST, max, min);
    }

    public static class Info{
        boolean isBST;
        int max;
        int min;

        public Info(boolean is, int ma, int mi){
            isBST = is;
            max = ma;
            min = mi;
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isSBT(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
