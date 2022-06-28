package com.sclin.class20;

import static class26.Code02_FibonacciProblem.muliMatrix;
import static com.sclin.class17.SplitNum.printArr;

public class QuickGetPower {

    public static int dp(int n){
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

    public static int method1(int n){
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {
                {1, 1},
                {1, 0}
        };
        int[][] result = power(base, n - 2);

        return result[0][0] + result[1][0];
    }

    public static int[][] power(int[][] base, int n) {
        int [][] result = new int[base.length][base[0].length];
        for (int i = 0; i < base.length; i++) {
            result[i][i] = 1;
        }
        int[][] temp = base;
        for (; n != 0; n >>= 1) {
            if ((n & 1) == 1) {
                result = mul(result, temp);
            }
            temp = mul(temp, temp);
        }

        return result;
    }

    public static int[][] mul(int[][] m1, int[][] m2){
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int n = 7;
        System.out.println(dp(n));
        System.out.println(method1(n));

//        int[][] base = {
//                {1,1},
//                {1,0}
//        };
//        printArr(mul(base, base));
//        printArr(muliMatrix(base, base));
    }

}
