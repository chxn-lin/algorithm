package com.sclin.class41;

import static class41.Code04_SplitArrayLargestSum.*;

public class FinishPrint {

    // 画家画画
    public static int method1(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] sumArr = new int[arr.length + 1];
        for (int i = 1; i < arr.length + 1; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i - 1];
        }
        return process(arr, sumArr, 0, num);
    }

    private static int process(int[] arr, int[] sumArr, int index, int peo) {
        if (peo < 0) {
            return -1;
        }
        if (index == arr.length) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= arr.length - index; i++) {
            int cur = sumArr[index + i] - sumArr[index];
            int p1 = process(arr, sumArr, index + i, peo - 1);
            if (p1 != -1) {
                cur = Math.max(cur, p1);
                res = Math.min(res, cur);
            }
        }
        return res;
    }

    public static int method2(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] sumArr = new int[arr.length + 1];
        for (int i = 1; i < arr.length + 1; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i - 1];
        }
        int[][] dp = new int[arr.length + 1][num + 1];

        for (int index = 0; index < arr.length; index++) {
            dp[index][0] = -1;
        }

        for (int peo = 1; peo <= num; peo++) {
            for (int index = arr.length - 1; index >= 0; index--) {
                int res = Integer.MAX_VALUE;
                for (int i = 0; i <= arr.length - index; i++) {
                    int cur = sumArr[index + i] - sumArr[index];
                    int p1 = dp[index + i][peo - 1];
                    if (p1 != -1) {
                        cur = Math.max(cur, p1);
                        res = Math.min(res, cur);
                    }
                }
                dp[index][peo] = res;
            }
        }

        return dp[0][num];
    }

    public static int method3(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] sumArr = new int[arr.length + 1];
        for (int i = 1; i < arr.length + 1; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i - 1];
        }
        int[][] dp = new int[arr.length + 1][num + 1];

        for (int index = 0; index < arr.length; index++) {
            dp[index][0] = -1;
        }

        for (int peo = 1; peo <= num; peo++) {
            for (int index = arr.length - 1; index >= 0; index--) {
                int res = Integer.MAX_VALUE;
                // 待优化
                for (int i = 0; i <= arr.length - index; i++) {
                    int cur = sumArr[index + i] - sumArr[index];
                    int p1 = dp[index + i][peo - 1];
                    if (p1 != -1) {
                        cur = Math.max(cur, p1);
                        res = Math.min(res, cur);
                    }
                }
                dp[index][peo] = res;
            }
        }

        return dp[0][num];
    }

    public static void main(String[] args) {
//        int[] arr = {1,2};
//        int M = 1;
//        System.out.println(method2(arr, M));

        int N = 10;
        int maxValue = 100;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int M = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(len, maxValue);
            int ans2 = method3(arr, M);
            int ans3 = splitArray3(arr, M);
            if (ans2 != ans3) {
                System.out.print("arr : ");
                printArray(arr);
                System.out.println("M : " + M);
                System.out.println("my : " + ans2);
                System.out.println("ans3 : " + ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
