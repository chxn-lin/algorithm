package com.sclin.class39;

import java.util.Map;
import java.util.TreeMap;

import static class39.Code02_SnacksWays.ways3;

public class NiuNiuBages {

//    牛牛家里一共有n袋零食，第i袋零食体积为v[i] ，背包容量w，
//    牛牛想知道在总体积不超过背包容量的情况下，
//    一共有多少种零食放法，体积为0也算一种方法。
//            1 <= n <= 30, 1 <= w <= 2*10^9
//    v[i] (0 ~ 10^9)

    // 经典背包问题解法
    public static int method1(int[] v, int w){
        return process1(v, w, 0, w);
    }

    private static int process1(int[] v, int w, int index, int sum) {
        if (sum < 0) {
            return -1;
        }
        if (index == v.length) {
            return 1;
        }
        int res = process1(v, w, index + 1, sum);
        int p2 = process1(v, w, index + 1, sum - v[index]);
        res += p2 != -1 ? p2 : 0;
        return res;
    }

    public static int dp1(int[] v, int w){
        int[][] dp = new int[v.length + 1][w + 1];

        for (int sum = 0; sum < w + 1; sum++) {
            dp[v.length][sum] = 1;
        }
        for (int index = v.length - 1; index >= 0; index--) {
            for (int sum = 0; sum < w + 1; sum++) {
                int res = dp[index + 1][sum];
                int p2 = 0;
                if (sum - v[index] >= 0) {
                    p2 = dp[index + 1][sum - v[index]];
                }
                res += p2;
                dp[index][sum] = res;
            }
        }
        return dp[0][w];
    }

    public static int method2(int[] v, int w){
        int res = 1;
        int mid = v.length >> 1;
        TreeMap<Long, Long> left = new TreeMap<>();
        res += process2(v, w, 0, mid, left, 0, 0);
        TreeMap<Long, Long> right = new TreeMap<>();
        res += process2(v, w, mid + 1, v.length - 1, right, mid + 1, 0);

        TreeMap<Long, Long> newRight = new TreeMap<>();
        long num = 0;
        for (Map.Entry<Long, Long> entry : right.entrySet()) {
            num += entry.getValue();
            newRight.put(entry.getKey(), num);
        }
        // 处理整合逻辑
        // 还剩下left和right都取得情况
        for (Map.Entry<Long, Long> entry : left.entrySet()) {
            Long key = entry.getKey();
            Long value = entry.getValue();
            Long floorKey = newRight.floorKey(w - key);
            if (floorKey != null) {
                res += value * newRight.get(floorKey);
            }
        }

        return res;
    }

    // 到当前位置，累加和为sum，一共有多少种方法
    private static long process2(int[] v, int w, int start, int end, TreeMap<Long, Long> tree,
                                 int index, long sum){
        if (sum > w) {
            return 0;
        }
        if (index > end) {
            if (sum <= w && sum != 0) {
                if (tree.containsKey(sum)) {
                    tree.put(sum, tree.get(sum) + 1);
                } else {
                    tree.put(sum, 1L);
                }
                return 1;
            } else {
                return 0;
            }
        }
        long res = process2(v, w, start, end, tree, index + 1, sum);
        res += process2(v, w, start, end, tree, index + 1, sum + v[index]);

        return res;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 3, 2, 4,23,4,5,19,22 };
        int w = 30;
        System.out.println(method2(arr, w));
        System.out.println(dp1(arr, w));
        System.out.println(ways3(arr, w));

    }

}
