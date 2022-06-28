package com.sclin.class15;

import static class19.Code01_Knapsack.maxValue;

public class PackageValue {

    /*
    给定一个重量 w[] 和 价值 v[] ，背包容量bag，返回能装的最大价值
重量为非负数。
     */
    public static int method1(int[] w, int[] v, int bag) {
        return process1(w, v, 0, bag);
    }

    private static int process1(int[] w, int[] v, int index, int bag) {
        if (bag < 0 || index == w.length) {
            return 0;
        }
        int p1 = process1(w, v, index + 1, bag);
        int p2 = 0;
        if (bag - w[index] >= 0) {
            p2 = v[index] + process1(w, v, index + 1, bag - w[index]);
        }
        return Math.max(p1, p2);
    }

    public static int method2(int[] w, int[] v, int bag) {
        int[][] dp = new int[w.length + 1][bag + 1];

        for (int i = w.length - 1; i >= 0; i--) {
            for (int j = 0; j < bag + 1; j++) {
                int p1 = dp[i + 1][j];
                int p2 = v[i] + dp[i + 1][j - w[i]];
                if (j - w[i] >= 0) {
                    p2 = v[i] + dp[i + 1][j - w[i]];
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }

        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 3, 1};
        int[] values = { 5, 6, 3, 12, 4};
        int bag = 8;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(method1(weights, values, bag));
        System.out.println(method2(weights, values, bag));
    }

}
