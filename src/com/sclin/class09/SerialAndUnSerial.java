package com.sclin.class09;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerialAndUnSerial {

    public static void main(String[] args) {
        String str = serial(tree);
        System.out.println(str);
        Queue<String> queue = new LinkedList(Arrays.asList(str.split(",")));
        Tree t = unSerial(queue);
        System.out.println(t);
    }

    /**
     * 二叉树的序列化和反序列化，先序
     */
    public static String serial(Tree head) {
        String str = "";
        if (head == null) {
            str = "#" + ",";
            return str;
        }
        str += head.value + ",";
        str += serial(head.left);
        str += serial(head.right);

        return str;
    }


    public static Tree unSerial(Queue<String> queue){
        if (queue == null || queue.size() < 1) {
            return null;
        }
        Tree head = genTree(queue.poll());
        if (head != null) {
            head.left = unSerial(queue);
            head.right = unSerial(queue);
        }

        return head;
    }

    public static Tree genTree(String str){
        if ("#".equals(str)) {
            return null;
        }
        Tree t = new Tree(str);
        return t;
    }

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
}
