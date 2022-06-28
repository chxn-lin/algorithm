package com.sclin.class02;

import java.util.Stack;

public class GetMixValue {

    public static void main(String[] args) {
        MyMixstack stack1 = new MyMixstack();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());
    }

    private static class MyMixstack{
        private Stack<Integer> norStack;
        private Stack<Integer> mixStack;

        public MyMixstack(){
            norStack = new Stack<>();
            mixStack = new Stack<>();
        }

        // 插入
        public MyMixstack push(int value){
            norStack.push(value);
            if (mixStack.empty()) {
                mixStack.push(value);
            } else {
                mixStack.push(mixStack.peek() >= value ? value : mixStack.peek());
            }
            return this;
        }

        // 弹出
        public int pop(){
            int value = norStack.pop();
            mixStack.pop();
            return value;
        }

        public int getMin(){
            int value = mixStack.peek();
            return value;
        }

    }

}
