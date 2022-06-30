package com.sclin.class21;

import class27.Code02_TreeEqual;

import java.util.ArrayList;
import java.util.List;

import static class27.Code02_TreeEqual.containsTree1;
import static class27.Code02_TreeEqual.generateRandomBST;
import static class27.Code02_TreeEqual.Node;

public class TreeEqual {

//    给定两棵树，t1 和 t2，判断t1上是否有那颗子树（子节点必须全要）
    public static boolean check(Node big, Node small){
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }

        String bigStr = nodeSerial(big);
//        System.out.println(bigStr);
        String smallStr = nodeSerial(small);
//        System.out.println(smallStr);

        return bigStr.indexOf(smallStr) != -1;
    }

    // 先序序列化
    public static String nodeSerial(Node node){
        StringBuilder str = new StringBuilder();
        process(node, str);
        return str.toString();
    }

    private static void process(Node node, StringBuilder str){
        if (node == null) {
            str.append("#,");
        } else {
            str.append(node.value + ",");
            process(node.left, str);
            process(node.right, str);
        }
    }

//    public static boolean check2(Node big, Node small){
//        if (small == null) {
//            return true;
//        }
//        if (big == null) {
//            return false;
//        }
//
//    }

    public static void main(String[] args) {
//        Node node1 = new Node(1);
//        Node node2 = new Node(2);
//        Node node3 = new Node(3);
////        Node node4 = new Node(4);
//        Node node5 = new Node(5);
//
//        node1.left = node2;
//        node1.right = node3;
//        node2.right = node5;
//        System.out.println(check(node1, node3));

        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean ans1 = containsTree1(big, small);
            boolean ans2 = check(big, small);
            if (ans1 != ans2) {
                System.out.println(big);
                System.out.println(small);

                System.out.println("my:" + ans2);
                System.out.println("true:" + ans1);
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");
    }
    
}
