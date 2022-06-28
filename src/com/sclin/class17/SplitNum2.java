package com.sclin.class17;

public class SplitNum2 {

    /*
     * 给定一个正数1，裂开的方法有一种（1）， 给定一个正数2，裂开的方法有两种（1和1）、（2）， 给定一个正数4，裂开的方法有五种，
     * (1,1,1,1)(1,1,2)(1,3)(2,2)(4)
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
        for (int i = pre; i <= rest / 2; i++) {
            result += process(rest - i, i);
        }

        return result;
    }

    public static int method2(int n) {
        if (n < 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][n / 2 + 1];

        for (int i = 0; i < n / 2 + 1; i++) {
            dp[i][i] = 1;
        }
        for (int rest = 1; rest < n + 1; rest++) {
            for (int pre = 1; pre < n / 2 + 1; pre++) {
                int result = 1;
                for (int i = pre; i <= rest / 2; i++) {
                    result += dp[rest - i][i];
                }
                dp[rest][pre] = result;
            }
        }
        printArr(dp);
        return dp[n][1];
    }

    // 你的没改之前的方法，下面的zuo方法是我改对的
    // 问题出在1的“少算”，和1的“多算”
    // n = 4，会少算
    // n = 6，会多算
    // 解释起来比较繁琐，真的看了半天
    // 最后还是根据课上的代码，改的一半矩阵的实现
    // 建议把n = 4的动态规划表画出来，和正确的逐一对比
    // 建议把n = 6的动态规划表画出来，和正确的逐一对比
    public static int method3(int n) {
        if (n < 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][n / 2 + 1];
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
                if (pre + 1 < n / 2) {
                    result += dp[rest][pre + 1];
                }
                dp[rest][pre] = result;
            }
        }
        printArr(dp);
        return dp[n][1];
    }

    // 我给你改的用一半空间的方法
    // 正确的
    public static int zuo(int n) {
        if (n < 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][n / 2 + 2];
        dp[0][n / 2 + 1] = 1;
        for (int row = n / 2 + 1; row <= n; row++) {
            dp[row][n / 2 + 1] = 1;
        }
        for (int pre = 1; pre <= n / 2; pre++) {
            dp[0][pre] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n / 2; pre > 0; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[rest][pre] = dp[rest][pre + 1];
                dp[rest][pre] += dp[rest - pre][pre];
            }
        }
        printArr(dp);
        return dp[n][1];
    }

    // 课上代码
    public static int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        printArr(dp);
        return dp[1][n];
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

    public static void main(String[] args) {
        int test = 5;
        System.out.println("没改之前的:" + method2(test));
        System.out.println("没改之前的:" + method3(test));
        System.out.println("改正之后的:" + zuo(test));
        System.out.println("课上代码 : " + dp2(test));
    }

}
