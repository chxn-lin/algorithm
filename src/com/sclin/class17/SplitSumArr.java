package com.sclin.class17;

import java.util.Arrays;

import static class23.Code01_SplitSumClosed.*;

public class SplitSumArr {

    // 给定一个正数数组arr，请把arr中所有的数分为两个集合，
    //尽量让两个集合的累加和接近，返回：
    //最接近的情况下，较小集合的累加和

    public static int method1(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        Arrays.sort(arr);
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return process(arr, 0, sum >> 1);
    }

    // 返回从index开始，累加和最接近sum的
    private static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return 0;
        }
        // 选sum的位置
        int p1 = process(arr, index + 1, rest);
        int p2 = 0;
        if (rest - arr[index] >= 0) {
            p2 = arr[index] + process(arr, index + 1, rest - arr[index]);
        }
        return Math.max(p1, p2);
    }

    public static int method2(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int sum2 = (sum >> 1) + 1;
        int[][] dp = new int[arr.length + 1][sum2];

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < sum2; rest++) {
                int p1 = process(arr, index + 1, rest);
                int p2 = 0;
                if (rest - arr[index] >= 0) {
                    p2 = arr[index] + process(arr, index + 1, rest - arr[index]);
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }

        return dp[0][sum >> 1];
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans2 = dp(arr);
            int ans1 = method2(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
