package com.sclin.class14;

import java.util.PriorityQueue;
import java.util.Stack;

public class OneHeapReserve {

    // 给你一个栈，请你逆序这个栈，不能申请额外的数据结构
    //只能使用递归函数，如何实现。
    public static Stack<Integer> method1(Stack<Integer> stack){
        process(stack);
        return stack;
    }

    // 逆序
    public static void process(Stack<Integer> stack){
        if (stack == null || stack.size() <= 1) {
            return ;
        }
        int cur = getstackLow(stack);
        process(stack);
        stack.add(cur);
    }

    // 直接取栈底元素
    public static int getstackLow(Stack<Integer> stack){
        if (stack.size() == 1) {
            return stack.pop();
        }
        int cur = stack.pop();
        int result = getstackLow(stack);
        stack.add(cur);
        return result;
    }

    public static void main(String[] args) {
        Stack<Integer> stack2 = new Stack<>();
        stack2.add(1);
        stack2.add(2);
        stack2.add(3);
        stack2.add(4);
        stack2.add(5);
        stack2.add(6);

        process(stack2);
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop());
        }

    }

}
