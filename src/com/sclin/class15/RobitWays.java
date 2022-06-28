package com.sclin.class15;

public class RobitWays {

    // 数组大小为size，目标位置为aim，当前位置为cur，步数为m
    public static int way1(int size, int aim, int cur, int m){
        return process1(size, aim, cur, m);
    }

    public static int process1(int size, int aim, int cur, int m){
        if (m == 0) {
            boolean onWay = aim == cur;
            return onWay ? 1 : 0;
        }
        if (cur == 1) {
            return process1(size, aim, cur + 1, m - 1);
        }
        if (cur == size) {
            return process1(size, aim, cur - 1, m - 1);
        }

        return process1(size, aim, cur - 1, m - 1) + process1(size, aim, cur + 1, m - 1);
    }

    public static int way2(int size, int aim, int cur, int m){
        int[][] dp = new int[size][m + 1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < m + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(size, aim, cur, m, dp);
    }

    public static int process2(int size, int aim, int cur, int m, int[][] dp){
        if (cur >= size) {
            return 0;
        }
        if (dp[cur][m] != -1) {
            return dp[cur][m];
        }
        int result = -1;
        if (m == 0) {
            boolean onWay = aim == cur;
            result = onWay ? 1 : 0;
        } else
        if (cur == 1) {
            result = process2(size, aim, cur + 1, m - 1, dp);
        } else
        if (cur == size) {
            result = process2(size, aim, cur - 1, m - 1, dp);
        } else {
            result = process2(size, aim, cur - 1, m - 1, dp) + process2(size, aim, cur + 1, m - 1, dp);
        }
        dp[cur][m] = result;

        return result;
    }

    public static int way3(int size, int aim, int cur, int m){
        int[][] dp = new int[size + 1][m + 1];
        for (int i = 0; i < m + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                if (i == 0) {
                    if (aim == j) {
                       dp[j][i] = 1;
                    } else {
                        dp[j][i] = 0;
                    }
                } else {
                    if (j == 1) {
                        dp[j][i] = dp[j + 1][i - 1];
                    } else if (j == size) {
                        dp[j][i] = dp[j - 1][i - 1];
                    } else {
                        dp[j][i] = dp[j + 1][i - 1] + dp[j - 1][i - 1];
                    }
                }
            }
        }
        return dp[cur][m];
    }

    public static void main(String[] args) {
        System.out.println(way1(10, 5, 4, 1));// 1
        System.out.println(way1(10, 5, 4, 3));// 3
        System.out.println(way1(10, 5, 4, 7));// 3

        System.out.println("==================");

        System.out.println(way2(10, 5, 4, 1));// 1
        System.out.println(way2(10, 5, 4, 3));// 3
        System.out.println(way2(10, 5, 4, 7));// 3
        System.out.println("==================");

        System.out.println(way3(10, 5, 4, 1));// 1
        System.out.println(way3(10, 5, 4, 3));// 3
        System.out.println(way3(10, 5, 4, 7));// 3
    }

}
