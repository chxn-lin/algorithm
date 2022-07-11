package com.sclin.class41;

import static class41.Code03_StoneMerge.min3;
import static class41.Code03_StoneMerge.randomArray;
import static com.sclin.class01.KM1.printArr;

public class StoneMerge {

    public static int min(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] sumArr = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            sumArr[i + 1] = sumArr[i] + arr[i];
        }

        return process(arr, sumArr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int[] sumArr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int i = L; i < R; i++) {
            int p1 = process(arr, sumArr, L, i);
            int p2 = process(arr, sumArr, i + 1, R);
            res = Math.min(res, p1 + p2);
        }

        return sumArr[R + 1] - sumArr[L] + res;
    }

    public static int min_2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] sumArr = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            sumArr[i + 1] = sumArr[i] + arr[i];
        }
        int[][] dp = new int[arr.length][arr.length];

        for (int L = arr.length - 2; L >= 0; L--) {
            for (int R = L + 1; R < arr.length; R++) {
                int res = Integer.MAX_VALUE;
                for (int i = L; i < R; i++) {
                    int p1 = dp[L][i];
                    int p2 = dp[i + 1][R];
                    res = Math.min(res, p1 + p2);
                }

                dp[L][R] = sumArr[R + 1] - sumArr[L] + res;
            }
        }
        return dp[0][arr.length - 1];
    }

    public static int min_3(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] sumArr = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            sumArr[i + 1] = sumArr[i] + arr[i];
        }
        int[][] dp = new int[arr.length][arr.length];
        int[][] best = new int[arr.length][arr.length];

        for (int R = 0; R < arr.length - 1; R++) {
            dp[R][R + 1] = arr[R] + arr[R + 1];
            best[R][R + 1] = R;
        }

        for (int L = arr.length - 3; L >= 0; L--) {
            for (int R = L + 2; R < arr.length; R++) {
                int res = Integer.MAX_VALUE;
                int choose = -1;
                for (int i = best[L][R - 1]; i <= best[L + 1][R]; i++) {
                    int p1 = dp[L][i];
                    int p2 = dp[i + 1][R];
                    if (p1 + p2 <= res) {
                        choose = i;
                        res = Math.min(res, p1 + p2);
                    }
                }

                dp[L][R] = sumArr[R + 1] - sumArr[L] + res;
                best[L][R] = choose;
            }
        }
        return dp[0][arr.length - 1];
    }

    public static void main(String[] args) {
//        int[] arr = {42};
//        int[] arr = {1,2};
//        int min = min_3(arr);
//        int i = min3(arr);
//        System.out.println(min);
//        System.out.println(i);

        int N = 15;
        int maxValue = 100;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, maxValue);
            int ans1 = min_3(arr);
            int ans3 = min3(arr);
            if (ans1 != ans3) {
                printArr(arr);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

}
