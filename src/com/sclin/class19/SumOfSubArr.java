package com.sclin.class19;

import class26.Code01_SumOfSubarrayMinimums;

import java.util.Stack;

import static class26.Code01_SumOfSubarrayMinimums.*;

public class SumOfSubArr {

    public static int subArrayMinSum1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int result = 0;

        // 假定以index位置的数为最小数的子数组
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                int pop = stack.pop();
                int L = stack.isEmpty() ? -1 : stack.peek();
                int R = i;
                int num = (pop - L) * (R - pop);// 个数
                result += arr[pop] * num;
            }
            stack.add(i);
        }
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            int L = stack.isEmpty() ? -1 : stack.peek();
            int R = arr.length;
            int num = (pop - L) * (R - pop);// 个数
            result += arr[pop] * num;
        }

        return result;
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 50;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = subArrayMinSum1(arr);
            int ans3 = sumSubarrayMins(arr);
            if (ans1 != ans3) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans3);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
