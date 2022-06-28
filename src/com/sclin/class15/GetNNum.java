package com.sclin.class15;

import java.util.ArrayList;

public class GetNNum {

    // 获取第n个位置的数字
    public static int getNum(int n, int[] dp){
        if (dp[n] != 0) {
            return dp[n];
        }
        if (n == 1 || n == 2) {
            dp[n] = 1;
            return 1;
        }
        return getNum(n - 1, dp) + getNum(n - 2, dp);
    }

    public static int getNum1(int n){
        if (n < 1) {
            return 0;
        }
        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int[] dp = new int[100];
        int num = 9;
        System.out.println(getNum(num, dp));
        System.out.println(getNum1(num));
    }

}
