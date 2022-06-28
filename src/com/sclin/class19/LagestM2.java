package com.sclin.class19;

import java.util.Stack;

import static class25.Code03_LargestRectangleInHistogram.largestRectangleArea2;

public class LagestM2 {

    // 返回直方图的最大面积
    public static int method1(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int result = 0;

        Stack<Integer> stack = new Stack();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer pop = stack.pop();
                int L = stack.isEmpty() ? -1 : stack.peek();
                int R = i;
                result = Math.max(result, arr[pop] * (R - L - 1));
            }
            if (!stack.isEmpty() && arr[stack.peek()] == arr[i]) {
                stack.pop();
            }
            stack.add(i);
        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int L = stack.isEmpty() ? -1 : stack.peek();
            int R = arr.length;
            result = Math.max(result, arr[pop] * (R - L - 1));
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1,1};
        System.out.println("my:" + method1(arr));
        System.out.println("true:" + largestRectangleArea2(arr));
    }

}
