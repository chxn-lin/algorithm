package com.sclin.class20;

import static com.sclin.class20.QuickGetPower.power;

public class GetNiuNum {

    public static int method1(int n){
        return process(n);
    }

    private static int process(int n) {
        if (n <= 3) {
            return 1;
        }
        return process(n - 1) + process(n - 3);
    }

    public static int dp(int n){
        if (n <= 3) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        for (int i = 4; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 3];
        }

        return dp[n];
    }

    private static int method2(int n) {
        if (n <= 3) {
            return 1;
        }
        int[][] base = {
                {1,1,0},
                {0,0,1},
                {1,0,0}
        };
        int[][] pow = power(base, n - 3);

        return pow[0][0] + pow[1][0] + pow[2][0];
    }

    public static void main(String[] args) {
        int n = 20;
        System.out.println(method1(n));
        System.out.println(dp(n));
        System.out.println(method2(n));
    }

}
