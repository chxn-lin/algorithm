package com.sclin.class09;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LevelTree {

    public static Tree tree = null;

    static {
        Tree a = new Tree("a");
        Tree b = new Tree("b");
        Tree c = new Tree("c");
        Tree d = new Tree("d");
        Tree e = new Tree("e");
        Tree f = new Tree("f");
        Tree g = new Tree("g");

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;

        tree = a;
    }

    public static void main(String[] args) {
//        printByLevel(tree);
//        System.out.println(getMaxWidth(tree));

        Queue<String> queue = en(tree);
        queue.forEach(s -> System.out.print(s + ","));
        Tree deTree = de(queue);
        System.out.println();
    }

    public static void printByLevel(Tree head) {
        if (head == null) {
            return ;
        }
        Queue<Tree> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Tree poll = queue.poll();
            System.out.print(poll.value + " ");
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
    }

    public static int getMaxWidth(Tree head) {
        if (head == null) {
            return 0;
        }
        int maxWidth = 0;
        int curWidth = 0;
        Tree curEnd = head;
        Tree nextEnd = null;
        Queue<Tree> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Tree poll = queue.poll();
            curWidth++;
            maxWidth = Math.max(curWidth, maxWidth);
            if (poll.left != null) {
                queue.add(poll.left);
                nextEnd = poll.left;
            }
            if (poll.right != null) {
                queue.add(poll.right);
                nextEnd = poll.right;
            }
            if (poll == curEnd) {
                curWidth = 0;
                curEnd = nextEnd;
                nextEnd = null;
            }
        }

        return maxWidth;
    }

    // 序列化
    public static Queue<String> en(Tree head) {
        if (head == null) {
            return null;
        }
        Queue<Tree> queue = new LinkedList<>();
        Queue<String> printQueue = new LinkedList<>();
        queue.add(head);
        printQueue.add(head.value);
        while (!queue.isEmpty()) {
            Tree poll = queue.poll();
            if (poll.left != null) {
                queue.add(poll.left);
                printQueue.add(poll.left.value);
            } else {
                printQueue.add(null);
            }
            if (poll.right != null) {
                queue.add(poll.right);
                printQueue.add(poll.right.value);
            } else {
                printQueue.add(null);
            }
        }
        return printQueue;
    }

    public static Tree de(Queue<String> queue){
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        Tree head = genTree(queue.poll());
        Queue<Tree> help = new LinkedList<>();
        help.add(head);
        Tree cur = null;
        while (!help.isEmpty()) {
            cur = help.poll();
            cur.left = genTree(queue.poll());
            if (cur.left != null) {
                help.add(cur.left);
            }
            cur.right = genTree(queue.poll());
            if (cur.right != null) {
                help.add(cur.right);
            }
        }
        return head;
    }

    public static Tree genTree(String str){
        if (str == null || "null".equals(str)) {
            return null;
        }
        return new Tree(str);
    }

}
