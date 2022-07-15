package com.sclin.class43;

import static class43.Code03_PavingTile.ways4;

public class PavingTile {

    // 贴砖问题
    public static int method1(int N, int M){
        if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int[] pre = new int[M];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = 1;
        }

        return process1(pre, 0, N);
    }

    // pre 表示level-1行的状态
    // level表示，正在level行做决定
    // N 表示一共有多少行 固定的
    // level-2行及其之上所有行，都摆满砖了
    // level做决定，让所有区域都满，方法数返回
    private static int process1(int[] pre, int curLevel, int n) {
        if (curLevel == n) {
            for (int i = 0; i < pre.length; i++) {
                if (pre[i] != 1) {
                    return 0;
                }
            }
            return 1;
        }
        int[] ops = getOps(pre);

        return dfs(ops, 0, curLevel, n);
    }

    private static int dfs(int[] ops, int cur, int curLevel, int n) {
        if (cur == ops.length) {
            return process1(ops, curLevel + 1, n);
        }
        int res = 0;

        // 当前位置不横摆
//        if (ops[cur] == 0) {
            res += dfs(ops, cur + 1, curLevel, n);
//        }
        // 当前位置横摆
        if (ops[cur] == 0 && cur + 1 < ops.length && ops[cur + 1] == 0) {
            ops[cur] = 1;
            ops[cur + 1] = 1;
            res += dfs(ops, cur + 2, curLevel, n);
            ops[cur] = 0;
            ops[cur + 1] = 0;
        }
        return res;
    }

    // 返回所有可选择的位置，用1表示
    private static int[] getOps(int[] pre) {
        int[] res = new int[pre.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = pre[i] ^ 1;
        }
        return res;
    }

    public static int method2(int N, int M){
        if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int pre = (1 << M) - 1;

        return process2(pre, 0, N, M);
    }

    // pre 表示level-1行的状态
    // level表示，正在level行做决定
    // N 表示一共有多少行 固定的
    // level-2行及其之上所有行，都摆满砖了
    // level做决定，让所有区域都满，方法数返回
    private static int process2(int pre, int curLevel, int n, int m) {
        if (curLevel == n) {
            if (pre != (1 << m) - 1) {
                return 0;
            }
            return 1;
        }
        int ops = getOps2(pre, m);

        return dfs2(ops, 0, curLevel, n, m);
    }

    private static int dfs2(int ops, int cur, int curLevel, int n, int m) {
        if (cur == m) {
            return process2(ops, curLevel + 1, n, m);
        }
        int res = 0;

        // 当前位置不横摆
        res += dfs2(ops, cur + 1, curLevel, n, m);
        // 当前位置横摆
        if ((ops & (1 << cur)) == 0 && cur + 1 < m && (ops & (1 << (cur + 1))) == 0) {
            res += dfs2(ops + (1 << cur) + (1 << (cur + 1)), cur + 2, curLevel, n, m);
        }
        return res;
    }

    // 返回所有可选择的位置，用1表示
    private static int getOps2(int pre, int m) {
        int res = ((1 << m) - 1) ^ pre;
        return res;
    }

    public static int method3(int N, int M){
        if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int pre = (1 << M) - 1;
        int[][] dp = new int[1 << M][N + 1];
        for (int i = 0; i < 1 << M; i++) {
            for (int j = 0; j < N + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return process3(pre, 0, N, M, dp);
    }

    // pre 表示level-1行的状态
    // level表示，正在level行做决定
    // N 表示一共有多少行 固定的
    // level-2行及其之上所有行，都摆满砖了
    // level做决定，让所有区域都满，方法数返回
    private static int process3(int pre, int curLevel, int n, int m, int[][] dp) {
        if (dp[pre][curLevel] != -1) {
            return dp[pre][curLevel];
        }
        int res = -1;
        if (curLevel == n) {
            if (pre != (1 << m) - 1) {
                res = 0;
            } else {
                res = 1;
            }
        } else {
            int ops = getOps2(pre, m);
            res = dfs3(ops, 0, curLevel, n, m, dp);
        }
        return dp[pre][curLevel] = res;

    }

    private static int dfs3(int ops, int cur, int curLevel, int n, int m, int[][] dp) {
        if (cur == m) {
            return process3(ops, curLevel + 1, n, m, dp);
        }
        int res = 0;

        // 当前位置不横摆
        res += dfs3(ops, cur + 1, curLevel, n, m, dp);
        // 当前位置横摆
        if ((ops & (1 << cur)) == 0 && cur + 1 < m && (ops & (1 << (cur + 1))) == 0) {
            res += dfs3(ops + (1 << cur) + (1 << (cur + 1)), cur + 2, curLevel, n, m, dp);
        }
        return res;
    }

    public static void main(String[] args) {

        int N = 2;
        int M = 2;
        System.out.println(method3(N, M));
        System.out.println(ways4(N, M));

        System.out.println("===========");
        N = 8;
        M = 6;
        System.out.println(method3(N, M));
        System.out.println(ways4(N, M));

        N = 10;
        M = 10;
        System.out.println("=========");
        System.out.println(method3(N, M));
        System.out.println(ways4(N, M));
    }

}
