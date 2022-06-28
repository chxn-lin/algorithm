package com.sclin.class02;

import class03.Code07_TwoQueueImplementStack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TwoQueueMakeStack {

    public static void main(String[] args) {
        System.out.println("test begin");
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);

        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        myStack.push(6);
        System.out.println(myStack.pop());
    }

    private static class MyStack{
        Queue<Integer> q;
        Queue<Integer> h;

        public MyStack(){
            q = new LinkedList();
            h = new LinkedList();
        }

        // 插入
        public MyStack push(int value){
            q.add(value);
            return this;
        }

        // 弹出
        public int pop(){
            if (q.isEmpty()) {
                throw new RuntimeException("stack is empty!");
            }
            int size = q.size();
            for (int i = 0; i < size - 1; i++) {
                h.add(q.poll());
            }
            int value = q.poll();
            Queue temp = q;
            q = h;
            h = temp;
            return value;
        }

        public boolean isEmpty(){
            return q.size() == 0;
        }

    }
}
