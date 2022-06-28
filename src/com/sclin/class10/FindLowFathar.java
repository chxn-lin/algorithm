package com.sclin.class10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static class13.Code03_lowestAncestor.*;

public class FindLowFathar {

    // 给定一颗二叉树的头节点head，和另外两个节点a和b，返回a和b的最低公共祖先

    // 递归
    public static Node find2(Node head, Node a, Node b) {
        return process(head, a, b).ans;
    }
    public static Info process(Node head, Node a, Node b){
        if (head == null) {
            return new Info(null, false, false);
        }
        Info left = process(head.left, a, b);
        Info right = process(head.right, a, b);

        Node ans = null;
        boolean hasA = left.hasA || right.hasA || head == a;
        boolean hasB = left.hasB || right.hasB || head == b;
        if (left.ans != null) {
            ans = left.ans;
        } else
        if (right.ans != null) {
            ans = right.ans;
        } else
        if (hasA && hasB) {
            ans = head;
        }

        return new Info(ans, hasA, hasB);
    }

    private static class Info{
        Node ans;
        boolean hasA;
        boolean hasB;

        public Info(Node ans, boolean ha, boolean hb){
            this.ans = ans;
            hasA = ha;
            hasB = hb;
        }
    }


    // 非递归
    public static Node find1(Node head, Node a, Node b) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        map.put(head, null);
        putIntoMap(map, head);

        Node cur = a;
        Set<Node> set = new HashSet<>();
        while (cur != null) {
            set.add(cur);
            cur = map.get(cur);
        }
        cur = b;
        while (cur != null) {
            if (set.contains(cur)) {
                break;
            }
            cur = map.get(cur);
        }

        return cur;
    }

    public static void putIntoMap(Map<Node, Node> map, Node head) {
        if (head == null) {
            return;
        }
        if (head.left != null) {
            map.put(head.left, head);
            putIntoMap(map, head.left);
        }
        if (head.right != null) {
            map.put(head.right, head);
            putIntoMap(map, head.right);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != find2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
