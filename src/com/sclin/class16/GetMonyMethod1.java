package com.sclin.class16;

import static class21.Code02_CoinsWayEveryPaperDifferent.*;

public class GetMonyMethod1 {

//    arr是货币数组，其中的值都是正数。再给定一个正数aim，
//    每个值都认为是一张货币，
//    即便是值相同的货币也认为每一张都是不同的，
//    返回组成aim的方法数

    public static int method1(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (arr.length == index) {
            return rest == 0 ? 1 : 0;
        }
        int result = 0;
        result += process(arr, index + 1, rest - arr[index]);
        result += process(arr, index + 1, rest);

        return result;
    }

    public static int method2(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];

        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                int result = 0;
                if (rest - arr[index] >= 0) {
                    result += dp[index + 1][rest - arr[index]];
                }
                result += dp[index + 1][rest];

                dp[index][rest] = result;
            }

        }

        return dp[0][aim];
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = method2(arr, aim);
            int ans2 = method1(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
