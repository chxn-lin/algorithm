package com.sclin.class11;

import java.util.PriorityQueue;
import java.util.Stack;

import static class14.Code02_LessMoneySplitGold.generateRandomArray;
import static class14.Code02_LessMoneySplitGold.lessMoney2;

public class CutGold {

    /*
        一块金条切成两半，是需要花费和长度数值一样的铜板的。
        比如长度为20的金条，不管怎么切一刀，都要花费20个铜板，
        一群人想分整块金条，怎么分最省铜板，
        即输入一个数组，返回分割的最小代价。
     */

    public static int method1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        return process(arr, 0);
    }

    public static int process(int[] arr, int pre) {
        if (arr.length < 2) {
            return pre;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int[] newArr = genNewArr(arr, i, j);
                min = Math.min(process(newArr, pre) + arr[i] + arr[j], min);
            }
        }

        return min;
    }

    public static int[] genNewArr(int[] arr, int index1, int index2) {
        int[] newArr = new int[arr.length - 1];
        int result = arr[index1] + arr[index2];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == index1 || i == index2) {
                continue;
            }
            newArr[index++] = arr[i];
        }
        newArr[index] = result;
        return newArr;
    }

    public static int method2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        PriorityQueue<Integer> stack = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            stack.add(arr[i]);
        }
        int result = 0;
        int curResult = 0;
        while (stack.size() > 1) {
            curResult = stack.poll() + stack.poll();
            result += curResult;
            stack.add(curResult);
        }

        return result;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (method1(arr) != method2(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
