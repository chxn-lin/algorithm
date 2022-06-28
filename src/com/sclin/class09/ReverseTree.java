package com.sclin.class09;

import class10.Code03_UnRecursiveTraversalBT;

import java.util.Stack;

public class ReverseTree {

    public static void main(String[] args) {
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

        // [a b d e c f g]
//        firstSort(a);
        // [d b e a f c g]
//        secondSort(a);
        // [d e b f g c a]
//        endSort(a);
        pos2(a);
    }
    
    public static void pos2(Tree h){
        System.out.print("pos-order: ");
        if (h != null) {
            Stack<Tree> stack = new Stack<Tree>();
            stack.push(h);
            Tree c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && h != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    h = c;
                }
            }
        }
        System.out.println();
    }

    public static void endSort(Tree head){
        if (head == null) {
            return;
        }
        Stack<Tree> stack = new Stack<>();
        Stack<Tree> outStack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Tree top = stack.pop();
            outStack.push(top);
            if (top.left != null) {
                stack.push(top.left);
            }
            if (top.right != null) {
                stack.push(top.right);
            }
        }
        while (!outStack.isEmpty()) {
            System.out.print(outStack.pop().value + " ");
        }
    }

    public static void secondSort(Tree head){
        if (head == null) {
            return;
        }
        Stack<Tree> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head == null) {
                head = stack.pop();
                System.out.print(head.value + " ");
                head = head.right;
            } else {
                stack.push(head);
                head = head.left;
            }
        }
    }

    public static void firstSort(Tree head){
        if (head == null) {
            return;
        }
        Stack<Tree> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Tree top = stack.pop();
            System.out.print(top.value + " ");
            if (top.right != null) {
                stack.push(top.right);
            }
            if (top.left != null) {
                stack.push(top.left);
            }
        }
    }

}
