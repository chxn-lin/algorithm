package com.sclin.class19;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import static class25.Code01_MonotonousStack.*;

public class SingleStack {

    public static int[][] singleStackNoSame(int[] arr){
        if (arr == null) {
            return null;
        }
        int[][] result = new int[arr.length][2];
        if (arr.length == 0) {
            return result;
        }
        Stack<Integer> stack = new Stack<>();// 栈从上到下，大到小
        for (int index = 0; index < arr.length; index++) {// 下标
            while (!stack.isEmpty() && arr[stack.peek()] > arr[index]) {
                int pop = stack.pop();
                result[pop][0] = stack.isEmpty() ? -1 : stack.peek();
                result[pop][1] = index;
            }
            stack.add(index);
        }
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            result[pop][0] = stack.isEmpty() ? -1 : stack.peek();
            result[pop][1] = -1;
        }
        return result;
    }

    public static int[][] singleStack(int[] arr){
        if (arr == null) {
            return null;
        }
        int[][] result = new int[arr.length][2];
        if (arr.length == 0) {
            return result;
        }
        Stack<LinkedList<Integer>> stack = new Stack<>();// 栈从上到下，大到小
        for (int index = 0; index < arr.length; index++) {// 下标
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[index]) {
                LinkedList<Integer> popArr = stack.pop();
                for (Integer pop : popArr) {
                    result[pop][0] = stack.isEmpty() ? -1 : stack.peek().getLast();
                    result[pop][1] = index;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[index]) {
                stack.peek().add(index);
            } else {
                LinkedList<Integer> linked = new LinkedList<>();
                linked.add(index);
                stack.add(linked);
            }
        }
        while (!stack.isEmpty()) {
            LinkedList<Integer> popArr = stack.pop();
            for (Integer pop : popArr) {
                result[pop][0] = stack.isEmpty() ? -1 : stack.peek().getLast();
                result[pop][1] = -1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
//            if (!isEqual(getNearLessNoRepeat(arr1), singleStackNoSame(arr1))) {
//                System.out.println("Oops!");
//                printArray(arr1);
//                break;
//            }
            if (!isEqual(getNearLess(arr2), singleStack(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
