package com.sclin.class02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TwoStackMakeQueue {

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.add(1);
        queue.add(2);

        System.out.println(queue.pop());
        queue.add(3);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        queue.add(4);
        System.out.println(queue.pop());
    }

    private static class MyQueue{
        Stack<Integer> q;
        Stack<Integer> h;

        public MyQueue(){
            q = new Stack();
            h = new Stack();
        }

        // 插入
        public MyQueue add(int value){
            q.push(value);
            return this;
        }

        // 弹出
        public int pop(){
            int value = 0;
            if (!h.isEmpty()) {
                value = h.pop();
            } else {
                // 将值全部倒入h中
                moveToh();
                if (!h.isEmpty()) {
                    value = h.pop();
                } else {
                    throw new RuntimeException("queue is empty");
                }
            }
            return value;
        }

        private void moveToh() {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                h.add(q.pop());
            }
        }

    }

}
