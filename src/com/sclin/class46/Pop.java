package com.sclin.class46;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static class46.Code01_BurstBalloons.maxCoins1;

/**
 * das
 */
public class Pop {

    public static int method1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] newArr = new int[arr.length + 2];
        newArr[0] = 1;
        newArr[newArr.length - 1] = 1;
        for (int i = 0; i < arr.length; i++) {
            newArr[i + 1] = arr[i];
        }

        return process1(newArr, 1, newArr.length - 2);
    }

    private static int process1(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L] * arr[L - 1] * arr[L + 1];
        }
        // 最后打爆左边的
        int res = arr[L] * arr[L - 1] * arr[R + 1] + process1(arr, L + 1, R);
        // 打爆右边的
        res = Math.max(res, arr[R] * arr[R + 1] * arr[L - 1] + process1(arr, L, R - 1));
        // 枚举打爆中间的所有情况
        for (int i = L + 1; i < R; i++) {
            int cur = process1(arr, L, i - 1)
                    + process1(arr, i + 1, R)
                    + arr[i] * arr[L - 1] * arr[R + 1];
            res = Math.max(res, cur);
        }
        return res;
    }

    public static int method2(int[] oldArr) {
        if (oldArr == null || oldArr.length < 1) {
            return 0;
        }
        int[] arr = new int[oldArr.length + 2];
        arr[0] = 1;
        arr[arr.length - 1] = 1;
        for (int i = 0; i < oldArr.length; i++) {
            arr[i + 1] = oldArr[i];
        }
        int[][] dp = new int[arr.length][arr.length];

        for (int i = 1; i < arr.length - 2; i++) {
            dp[i][i] = arr[i] * arr[i - 1] * arr[i + 1];
        }
        for (int L = arr.length - 2; L >= 1; L--) {
            for (int R = L + 1; R <= arr.length - 2; R++) {
                int res = arr[L] * arr[L - 1] * arr[R + 1] + dp[L + 1][R];
                // 打爆右边的
                res = Math.max(res, arr[R] * arr[R + 1] * arr[L - 1] + dp[L][R - 1]);
                // 枚举打爆中间的所有情况
                for (int i = L + 1; i < R; i++) {
                    int cur = dp[L][i - 1]
                            + dp[i + 1][R]
                            + arr[i] * arr[L - 1] * arr[R + 1];
                    res = Math.max(res, cur);
                }
                dp[L][R] = res;
            }
        }

        return dp[1][arr.length - 2];
    }

    public static void main(String[] args) {
        int[] arr = {5,3,4,5,2};
        System.out.println(method2(arr));
        System.out.println(maxCoins1(arr));
    }

}
