package com.sclin.class40;

public class Feboqi {

    public static int method1(int n){
        if (n < 3) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int method2(int n){
        if (n < 3) {
            return 1;
        }
        int[][] base = {
                {1, 1},
                {1, 0}
        };
        int[][] ans = powBase(base, n - 2);
        return ans[0][0] + ans[1][0];
    }

    private static int[][] powBase(int[][] base, int pow) {
        int[][] temp = base;
        int[][] res = new int[base.length][base[0].length];
        for (int i = 0; i < base.length; i++) {
            res[i][i] = 1;
        }
        for (; pow != 0; pow >>= 1) {
            if ((1 & pow) != 0) {
                res = mul(res, temp);
            }
            temp = mul(temp, temp);
        }
        return res;
    }

    private static int[][] mul(int[][] arr1, int[][] arr2){
        int[][] res = new int[arr1.length][arr2[0].length];
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                for (int k = 0; k < arr2.length; k++) {
                    res[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        for (int i = 1; i < 200; i++) {
            if (method1(i) != method2(i)) {
                System.out.println("oops!");
                System.out.println(i);
                System.out.println(method1(i));
                System.out.println(method2(i));
                break;
            }
        }
        System.out.println("结束！");
    }

}
