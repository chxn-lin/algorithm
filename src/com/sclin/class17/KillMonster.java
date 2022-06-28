package com.sclin.class17;

import static class22.Code01_KillMonster.dp2;

public class KillMonster {

//    给定3个参数，N,M,K
//    怪兽有N滴血，等着英雄来砍自己，
//    英雄每一个打击，都会让怪兽流失[0~N]的血量，
//    到底流失多少？每一次在[0~M]上等概率获得一个值（流失整数滴血）。
//    求K次打击之后，英雄把怪兽砍死的概率。
    private static double method1(int n, int m, int k) {
        if (n <= 0) {
            return 0;
        }
        if (m <= 0 || k <= 0) {
            return 0;
        }
        double all = Math.pow(m + 1, k);
        double dead = process(n, m, k);
        return dead / all;
    }

    private static double process(int rest, int m, int k) {
        if (k == 0) {
            return rest <= 0 ? 1 : 0;
        }
        int result = 0;
        for (int i = 0; i < m + 1; i++) {
            result += process(rest - i, m, k - 1);
        }

        return result;
    }

    private static double method2(int n, int m, int k) {
        if (n <= 0) {
            return 0;
        }
        if (m <= 0 || k <= 0) {
            return 0;
        }

        double[][] dp = new double[k + 1][n + 1];

        dp[0][0] = 1;
        for (int i = 1; i < k + 1; i++) {
            dp[i][0] = Math.pow(m + 1, i);
        }
        for (int rest = 1; rest < k + 1; rest++) {
            for (int hp = 1; hp < n + 1; hp++) {
                double result = 0;
                for (int i = 0; i < m + 1; i++) {
                    if (hp - i <= 0) {
                        result += Math.pow(m + 1, rest - 1);
                    } else {
                        result += dp[rest - 1][hp - i];
                    }
                }
                dp[rest][hp] = result;
            }
        }

        double all = Math.pow(m + 1, k);
        double dead = dp[k][n];
        return dead / all;
    }

    private static double method3(int n, int m, int k) {
        if (n <= 0) {
            return 0;
        }
        if (m <= 0 || k <= 0) {
            return 0;
        }

        double[][] dp = new double[k + 1][n + 1];

        dp[0][0] = 1;
        for (int i = 1; i < k + 1; i++) {
            dp[i][0] = Math.pow(m + 1, i);
        }
        for (int rest = 1; rest < k + 1; rest++) {
            for (int hp = 1; hp < n + 1; hp++) {
                double result = dp[rest - 1][hp];
                result += dp[rest][hp - 1];

                if (hp - m - 1 <= 0) {
                    result -= Math.pow(m + 1, rest - 1);
                } else {
                    result -= dp[rest - 1][hp - m - 1];
                }

                dp[rest][hp] = result;
            }
        }

        double all = Math.pow(m + 1, k);
        double dead = dp[k][n];
        return dead / all;
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = method3(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans3) {
                System.out.println("Oops!");
                System.out.println("N:" + N + "; m:" + M + "; k:" + K);
                System.out.println("my:" + ans1);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
