package com.sclin.class10;

import static class12.Code04_IsFull.generateRandomBST;
import static class12.Code04_IsFull.isFull1;

public class CheckIsFull {

    // 判断二叉树是否为满二叉树
    public static boolean checkIsFull(Node head){
        Info info = process(head);
        return (1 << info.high) - 1 == info.size;
    }

    public static Info process(Node head){
        if (head == null) {
            return new Info(0, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int high = Math.max(left.high, right.high) + 1;
        int size = left.size + right.size + 1;

        return new Info(high, size);
    }

    public static class Info{
        int high;
        int size;
        public Info(int hi, int s){
            high = hi;
            size = s;
        }
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isFull1(head) != checkIsFull(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
