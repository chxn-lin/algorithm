package com.sclin.class42;

import static class42.Code01_PostOfficeProblem.*;

public class PostMake {

    // 邮局问题
    public static int method1(int[] arr, int num){
        if (arr == null || arr.length < 1 || num >= arr.length) {
            return 0;
        }
        int[][] help = new int[arr.length + 1][arr.length + 1];
        for (int L = 0; L < arr.length; L++) {
            for (int R = L + 1; R < arr.length; R++) {
                help[L][R] = arr[R] - arr[(R + L) >> 1] + help[L][R - 1];
            }
        }

        return process(arr, help,arr.length - 1, num);
    }

    private static int process(int[] arr, int[][] help, int index, int num) {
        if (num == 1) {
            return help[0][index];
        }
        if (index == 0) {
            return num >= 0 ? 0 : Integer.MAX_VALUE;
        }

        int res = Integer.MAX_VALUE;
        for (int k = 0; k <= index; k++) {
            int p1 = process(arr, help, k, num - 1);
            if (p1 != Integer.MAX_VALUE) {
                res = Math.min(res, p1 + help[k + 1][index]);
            }
        }
        return res;
    }

    public static int method2(int[] arr, int num){
        if (arr == null || arr.length < 1 || num >= arr.length) {
            return 0;
        }
        int[][] help = new int[arr.length + 1][arr.length + 1];
        for (int L = 0; L < arr.length; L++) {
            for (int R = L + 1; R < arr.length; R++) {
                help[L][R] = arr[R] - arr[(R + L) >> 1] + help[L][R - 1];
            }
        }

        int[][] dp = new int[arr.length][num + 1];
        for (int index = 0; index < arr.length; index++) {
            dp[index][1] = help[0][index];
        }

        for (int index = 1; index < arr.length; index++) {
            for (int rest = 2; rest < num + 1; rest++) {
                int res = Integer.MAX_VALUE;
                for (int k = 0; k <= index; k++) {
                    int p1 = dp[k][rest - 1];
                    if (p1 != Integer.MAX_VALUE) {
                        res = Math.min(res, p1 + help[k + 1][index]);
                    }
                }
                dp[index][rest] = res;
            }
        }

        return dp[arr.length - 1][num];
    }

    public static int method3(int[] arr, int num){
        if (arr == null || arr.length < 1 || num >= arr.length) {
            return 0;
        }
        int[][] help = new int[arr.length + 1][arr.length + 1];
        for (int L = 0; L < arr.length; L++) {
            for (int R = L + 1; R < arr.length; R++) {
                help[L][R] = arr[R] - arr[(R + L) >> 1] + help[L][R - 1];
            }
        }

        int[][] dp = new int[arr.length][num + 1];
        int[][] best = new int[arr.length][num + 1];
        for (int index = 0; index < arr.length; index++) {
            dp[index][1] = help[0][index];
            best[index][1] = -1;
        }

        for (int rest = 2; rest < num + 1; rest++) {
            for (int index = arr.length - 1; index > 0; index--) {
                int res = Integer.MAX_VALUE;
                int up = index == arr.length - 1 ? index : best[index + 1][rest];
                int down = best[index][rest - 1] != -1 ? best[index][rest - 1] : 0;
                int bestChoose = -1;
                for (int k = down; k <= up; k++) {
                    int p1 = dp[k][rest - 1];
                    if (p1 != Integer.MAX_VALUE) {
                        if (p1 + help[k + 1][index] < res) {
                            bestChoose = k;
                        }
                        res = Math.min(res, p1 + help[k + 1][index]);
                    }
                }
                dp[index][rest] = res;
                best[index][rest] = bestChoose;
            }
        }

        return dp[arr.length - 1][num];
    }

    public static void main(String[] args) {
//        int[] arr = {1,2,4,7,10,13};
//        int num = 3;
//        System.out.println(method1(arr, num));
//        System.out.println(min2(arr, num));

        int N = 10;
        int maxValue = 100;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] arr = randomSortedArray(len, maxValue);
            int num = (int) (Math.random() * N) + 1;
            int ans1 = method3(arr, num);
            int ans2 = min2(arr, num);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(num);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");

    }

}
