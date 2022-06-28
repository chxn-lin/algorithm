package com.sclin.class17;

import com.sclin.class13.TopoOrderDFS;

import java.util.Arrays;
import java.util.Comparator;

import static class22.Code02_MinCoinsNoLimit.*;

public class MinCoins {

//    arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim，
//    每个值都认为是一种面值，且认为张数是无限的，
//    返回组成aim的最小货币数

    private static int method1(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        if (arr == null || arr.length == 0) {
            return Integer.MAX_VALUE;
        }
        return process1(arr, 0, aim);
    }

    private static int process1(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; rest - i * arr[index] >= 0; i++) {
            int process1 = process1(arr, index + 1, rest - i * arr[index]);
            if (process1 != Integer.MAX_VALUE) {
                result = Math.min(result, process1 + i);
            }
        }

        return result;
    }

    private static int method2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        if (arr == null || arr.length == 0) {
            return Integer.MAX_VALUE;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];

        for (int i = 1; i < aim + 1; i++) {
            dp[arr.length][i] = Integer.MAX_VALUE;
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                int result = Integer.MAX_VALUE;
                for (int i = 0; rest - i * arr[index] >= 0; i++) {
                    int process1 = dp[index + 1][rest - i * arr[index]];
                    if (process1 != Integer.MAX_VALUE) {
                        result = Math.min(result, process1 + i);
                    }
                }
                dp[index][rest] = result;
            }
        }

        return dp[0][aim];
    }

    private static int method3(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        if (arr == null || arr.length == 0) {
            return Integer.MAX_VALUE;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];

        for (int i = 1; i < aim + 1; i++) {
            dp[arr.length][i] = Integer.MAX_VALUE;
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                int result = dp[index + 1][rest];
                if (rest - arr[index] >= 0
                        && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    result = Math.min(result, dp[index][rest - arr[index]] + 1);
                }
                dp[index][rest] = result;
            }
        }

        return dp[0][aim];
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = method3(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("aim:" + aim);
                System.out.println("my:" + ans1);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("功能测试结束");
    }



}
