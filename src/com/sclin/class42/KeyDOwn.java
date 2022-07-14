package com.sclin.class42;

import static class42.Code02_ThrowChessPiecesProblem.superEggDrop5;

public class KeyDown {

//    一座大楼有0~N层，地面算作第0层，最高的一层为第N层，
//    已知棋子从第0层掉落肯定不会摔碎，从第i层掉落可能会摔碎；
//    也可能不会（1<=i<=N）。给定整数N作为楼层数，再给定整数K作为棋子数，
//    返回如果想找到不会摔碎的最高层数，即使再最差的情况下人的最少次数，
//    一次只能扔一个棋子。

    public static int method1(int N, int K){
        if (N < 1 || K < 1) {
            return 0;
        }
        return process(N, K);
    }

    private static int process(int restLevel, int keyNum) {
        if (restLevel == 0) {
            return 0;
        }
        if (keyNum == 1) {
            return restLevel;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= restLevel; i++) {
            // 如果碎了
            int p1 = process(i - 1, keyNum - 1);
            int p2 = process(restLevel - i, keyNum);
            res = Math.min(res, Math.max(p1, p2));
        }
        return res + 1;
    }

    public static int method2(int N, int K){
        if (N < 1 || K < 1) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];

        for (int rest = 1; rest < N + 1; rest++) {
            dp[rest][1] = rest;
        }

        for (int restLevel = 1; restLevel < N + 1; restLevel++) {
            for (int keyNum = 2; keyNum < K + 1; keyNum++) {
                int res = Integer.MAX_VALUE;
                for (int i = 1; i <= restLevel; i++) {
                    int p1 = dp[i - 1][keyNum - 1];
                    int p2 = dp[restLevel - i][keyNum];
                    res = Math.min(res, Math.max(p1, p2));
                }
                dp[restLevel][keyNum] = res + 1;
            }
        }

        return dp[N][K];
    }

    public static int method3(int N, int K){
        if (N < 1 || K < 1) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];
        int[][] best = new int[N + 1][K + 1];

        for (int rest = 1; rest < N + 1; rest++) {
            dp[rest][1] = rest;
            best[rest][1] = rest;
        }
        for (int key = 1; key < K + 1; key++) {
            dp[1][key] = 1;
            best[1][key] = 1;
        }

        for (int restLevel = 2; restLevel < N + 1; restLevel++) {
            for (int keyNum = K; keyNum >= 2; keyNum--) {
                int res = Integer.MAX_VALUE;
                int up = keyNum == K ? restLevel : best[restLevel][keyNum + 1];
                int down = best[restLevel - 1][keyNum];
                int choose = 0;
                for (int i = down; i <= up; i++) {
                    int p1 = dp[i - 1][keyNum - 1];
                    int p2 = dp[restLevel - i][keyNum];
                    if (Math.max(p1, p2) <= res) {
                        res = Math.max(p1, p2);
                        choose = i;
                    }
                }
                dp[restLevel][keyNum] = res + 1;
                best[restLevel][keyNum] = choose;
            }
        }

        return dp[N][K];
    }

    public static void main(String[] args) {
//        System.out.println(method1(1, 4));

        int maxN = 100;
        int maxK = 20;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxN) + 1;
            int K = (int) (Math.random() * maxK) + 1;
            int ans2 = method3(N, K);
            int ans5 = superEggDrop5(K, N);
            if (ans2 != ans5) {
                System.out.println(N);
                System.out.println(K);
                System.out.println("my:"+ans2);
                System.out.println("true:"+ans5);
                System.out.println("出错了!");
                System.out.println("=============");
            }
        }
        System.out.println("测试结束");
    }

}
