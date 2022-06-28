package com.sclin.class10;

import static class12.Code06_MaxDistance.generateRandomBST;
import static class12.Code06_MaxDistance.maxDistance1;

public class SearchMaxDis {

    // 给定一颗二叉树的头节点head，任何两个节点之间都存在距离（这个距离值最优路径）
    //返回整颗二叉树的最大距离
    public static int getMaxDis(Node head){
        /**
         1、经过x
             左最大深度 + 右最大深度 + 1
         2、不经过x
            在左树
            在右树
         */
        return process(head).maxDis;
    }

    public static Info process(Node head){
        if (head == null) {
            return new Info(0, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);

        int high = Math.max(left.high, right.high) + 1;
        int maxDis = left.high + right.high + 1;
        maxDis = Math.max(Math.max(left.maxDis, maxDis), right.maxDis);

        return new Info(high, maxDis);
    }

    public static class Info{
        int high;
        int maxDis;
        public Info(int h, int md){
            high = h;
            maxDis = md;
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxDistance1(head) != getMaxDis(head)) {
                System.out.println("Oops!");
            }
        }
//        Node head = new Node(1);
//        head.left = new Node(2);
//        System.out.println(maxDistance1(head));
        System.out.println("finish!");
    }


}
