package com.sclin.class30;

import com.sclin.class10.Node;

import java.util.ArrayList;
import java.util.List;

import static class12.Code02_IsBST.generateRandomBST;
import static class12.Code02_IsBST.isBST2;

public class CheckIsBST {

    public static boolean method1(Node head) {
        if (head == null) {
            return true;
        }
        return process1(head).isBST;
    }

    private static Info process1(Node head) {
        if (head == null) {
            return null;
        }
        Info left = process1(head.left);
        Info right = process1(head.right);
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
        boolean isBST = true;
        if (left != null) {
            if (!left.isBST || left.max >= head.value) {
                isBST = false;
            }
        }
        if (right != null) {
            if (!right.isBST || right.min <= head.value) {
                isBST = false;
            }
        }
        return new Info(isBST, max, min);
    }

    private static class Info {
        boolean isBST;
        int max;
        int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean method2(Node head) {
        if (head == null) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        process2(head, list);
        boolean res = true;
        int pre = Integer.MIN_VALUE;
        for (Integer i : list) {
            if (i <= pre) {
                res = false;
                break;
            }
            pre = i;
        }
        return res;
    }

    private static void process2(Node node, List<Integer> list){
        if (node == null) {
            return ;
        }
        process2(node.left, list);
        list.add(node.value);
        process2(node.right, list);
    }

    public static boolean method3(Node head) {
        if (head == null) {
            return true;
        }
        boolean res = true;
        int pre = Integer.MIN_VALUE;
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            if (cur.left == null) {
                if (pre >= cur.value) {
                    res = false;
                } else {
                    pre = cur.value;
                }

                cur = cur.right;
            } else {

                mostRight = cur.left;
                while (mostRight.right != cur && mostRight.right != null) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else if (mostRight.right == cur) {
                    if (pre >= cur.value) {
                        res = false;
                    } else {
                        pre = cur.value;
                    }
                    mostRight.right = null;
                    cur = cur.right;
                }

            }
        }
        return res;
    }

    public static void main(String[] args) {
        
//        Node node = new Node(1);
//        node.left = new Node(2);
//        node.right = new Node(3);
//        node.left.left = new Node(4);
//        node.left.right = new Node(5);
//        node.right.left = new Node(6);
//        node.right.right = new Node(7);
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node node = generateRandomBST(maxLevel, maxValue);
            boolean b = method3(node);
            boolean bst2 = isBST2(node);
            if (b != bst2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
