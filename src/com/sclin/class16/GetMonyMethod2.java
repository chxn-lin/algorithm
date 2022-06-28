package com.sclin.class16;


import static class21.Code03_CoinsWayNoLimit.*;

public class GetMonyMethod2 {

//    arr如果是面值数组，其中的值都是正数且没有重复。
//    再给定一个正数aim，每个值都认为是一种面值，
//    且认为张数是  无限   的。
//    返回组成aim的方法数

    public static int method1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (arr.length == index) {
            return rest == 0 ? 1 : 0;
        }
        int result = 0;

        for (int i = 0; i * arr[index] <= rest; i++) {
            result += process(arr, index + 1, rest - (i * arr[index]));
        }

        return result;
    }

    public static int method2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];

        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                int result = 0;
                for (int i = 0; i * arr[index] <= rest; i++) {
                    if (rest - (i * arr[index]) >= 0) {
                        result += dp[index + 1][rest - (i * arr[index])];
                    }
                }

                dp[index][rest] = result;
            }
        }
        return dp[0][aim];
    }

    public static int method3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];

        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                int result = dp[index + 1][rest];

                if (rest - arr[index] >= 0) {
                    result += dp[index][rest - arr[index]];
                }

                dp[index][rest] = result;
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = method3(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
