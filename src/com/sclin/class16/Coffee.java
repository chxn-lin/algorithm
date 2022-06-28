package com.sclin.class16;

import java.util.PriorityQueue;

import static class20.Code03_Coffee.*;

public class Coffee {

    public static int coffeeClear1(int[] arr, int n, int a, int b){
        int[] clearArr = getLessClearTimeArr(arr, n);
        return process1(clearArr, a, b, 0, 0);
    }

    public static int coffeeClear2(int[] arr, int n, int a, int b){
        int result = -1;
        try {
            int[] clearArr = getLessClearTimeArr(arr, n);
            int maxFreeTime = 0;
            int size = clearArr.length;
            for (int i = 0; i < clearArr.length; i++) {
                maxFreeTime = Math.max(arr[i], maxFreeTime) + a;
            }
            int[][] dp = new int[size + 1][maxFreeTime + 1];

            for (int index1 = size - 1; index1 >= 0; index1--) {
                for (int free = 0; free <= maxFreeTime; free++) {
                    int chooseClear = Math.max(free, clearArr[index1]) + a;
                    if (chooseClear > maxFreeTime) {
                        break;
                    }
                    int chooseClearRest = dp[index1 + 1][chooseClear];
                    chooseClear = Math.max(chooseClear, chooseClearRest);
                    // 选择挥发
                    int chooseNoClear = b + clearArr[index1];
                    int chooseNoClearRest = dp[index1 + 1][free];
                    chooseNoClear = Math.max(chooseNoClear, chooseNoClearRest);

                    dp[index1][free] = Math.min(chooseClear, chooseNoClear);
                }
            }
            result = dp[0][0];
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    // free 机器空闲时间
    // wash 单杯洗干净的时间（串行）
    // air 挥发干净的时间(并行)
    private static int process1(int[] clearArr, int wash, int air, int index, int free) {
        if (index == clearArr.length) {
            return 0;
        }
        // 选择洗
        int chooseClear = Math.max(free, clearArr[index]) + wash;
        int chooseClearRest = process1(clearArr, wash, air, index + 1, chooseClear);
        chooseClear = Math.max(chooseClear, chooseClearRest);
        // 选择挥发
        int chooseNoClear = air + clearArr[index];
        int chooseNoClearRest = process1(clearArr, wash, air, index + 1, free);
        chooseNoClear = Math.max(chooseNoClear, chooseNoClearRest);

        return Math.min(chooseClear, chooseNoClear);
    }

    public static int[] getLessClearTimeArr(int[] arr, int n){
        int[] retArr = new int[n];
        class InnerClass{
            public int startTime;
            public int xl;
            public InnerClass(int startTime, int xl) {
                this.startTime = startTime;
                this.xl = xl;
            }
        }
        PriorityQueue<InnerClass> heap = new PriorityQueue<InnerClass>((a, b) -> {
            return (a.startTime + a.xl) - (b.startTime + b.xl);
        });
        for (int i = 0; i < arr.length; i++) {
            heap.add(new InnerClass(0, arr[i]));
        }
        for (int i = 0; i < n; i++) {
            InnerClass poll = heap.poll();
            poll.startTime += poll.xl;
            retArr[i] = poll.startTime;
            heap.add(poll);
        }
        return retArr;
    }

    public static void main(String[] args) {
//        int[] arr = {3, 5, 2};
//        int n = 1;
//        int a = 1;
//        int b = 5;
//        // out 3
//        System.out.println(minTime2(arr, n, a, b));
//        System.out.println(coffeeClear2(arr, n, a, b));

        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans3 = minTime2(arr, n, a, b);
            int ans1 = coffeeClear2(arr, n, a, b);
            if (ans1 != ans3) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans3);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");

    }

}
