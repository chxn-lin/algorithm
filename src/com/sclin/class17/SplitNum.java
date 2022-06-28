package com.sclin.class17;

import static class22.Code03_SplitNumber.dp2;

public class SplitNum {

    /*
    给定一个正数1，裂开的方法有一种（1），
    给定一个正数2，裂开的方法有两种（1和1）、（2），
    给定一个正数4，裂开的方法有五种，
    (1,1,1,1)(1,1,2)(1,3)(2,2)(4)
     */
    public static int method1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(n, 1);
    }

    private static int process(int rest, int pre) {
//        if (rest < pre) {
//            return 0;
//        }
        if (rest == pre) {
            return 1;
        }
        int result = 1;
        for (int i = pre; i <= rest/2; i++) {
            result += process(rest - i, i);
        }

        return result;
    }

    public static int method2(int n) {
        if (n < 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][n/2 + 1];

        for (int i = 0; i < n / 2 + 1; i++) {
            dp[i][i] = 1;
        }
        for (int rest = 1; rest < n + 1; rest++) {
            for (int pre = 1; pre < n / 2 + 1; pre++) {
                int result = 1;
                for (int i = pre; i <= rest/2; i++) {
                    result += dp[rest - i][i];
                }
                dp[rest][pre] = result;
            }
        }
        printArr(dp);
        return dp[n][1];
    }

    public static void printArr(int[][] dp) {
        System.out.println("{");

        for (int[] ints : dp) {
            System.out.print("{");
            for (int anInt : ints) {
                System.out.print(anInt + ", ");
            }
            System.out.print("},");
            System.out.println();
        }
        System.out.println("}");
    }

    public static int method3(int n) {
        if (n < 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][n/2 + 1];

        for (int i = 0; i < n / 2 + 1; i++) {
            dp[i][i] = 1;
        }
        for (int rest = 1; rest < n + 1; rest++) {
            for (int pre = n / 2; pre > 0; pre--) {
                int result = 0;
                if (rest >= pre) {
                    result = 1;
                }
                if (rest - pre > 0) {
                    result += dp[rest - pre][pre];
                }
                if (pre + 1 < n/2) {
                    result += dp[rest][pre + 1];
                }
                dp[rest][pre] = result;
            }
        }

        printArr(dp);
        return dp[n][1];
    }

    public static void main(String[] args) {
        int test = 4;
        System.out.println("my:" + method3(test));
        System.out.println("mytrue:" + method2(test));
        System.out.println("true:" + dp2(test));
    }

}
