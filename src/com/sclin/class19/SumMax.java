package com.sclin.class19;

import java.util.Stack;

import static class25.Code02_AllTimesMinToMax.gerenareRondomArray;
import static class25.Code02_AllTimesMinToMax.max2;

public class SumMax {

    /*
    给定一个只包含正数的数组arr，arr中任何一个子数组sub，
    一定都可以算出（sub累加和）*（sub中的最小值）是什么，
    那么所有子数组中，这个值最大是多少？
     */
    public static int method1(int[] arr){
        if (arr == null) {
            return -1;
        }
        int result = -1;

        int[] sumArr = new int[arr.length];
        sumArr[0] = arr[0];
        for (int index = 1; index < arr.length; index++) {
            sumArr[index] = arr[index] + sumArr[index - 1];
        }
        Stack<Integer> stack = new Stack<>();
        for (int index = 0; index < arr.length; index++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[index]) {
                int pop = stack.pop();
                int L = stack.isEmpty() ? -1 : stack.peek();
                int R = index;
                result = Math.max(arr[pop] * (sumArr[R - 1] - (L == -1 ? 0 : sumArr[L])), result);
            }
            stack.add(index);
        }
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            int L = stack.isEmpty() ? -1 : stack.peek();
            int R = arr.length;
            result = Math.max(arr[pop] * (sumArr[R - 1] - (L == -1 ? 0 : sumArr[L])), result);
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] arr = {1,2,1,1};
//        System.out.println(method1(arr));

        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (method1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }

}
