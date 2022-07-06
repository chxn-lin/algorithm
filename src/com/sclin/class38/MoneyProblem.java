package com.sclin.class38;

import static class38.Code04_MoneyProblem.generateTwoRandomArray;
import static class38.Code04_MoneyProblem.minMoney2;

public class MoneyProblem {

    // int[] d d[i]：i号怪兽的武力
    // int[] p p[i]：i号怪兽要求的钱

    public static long myFunc1(int[] d, int[] p) {
        return process1(d, p, 0, 0);
    }

    // 从0....index号怪兽，花的钱，必须严格==money
    // 如果通过不了，返回-1
    // 如果可以通过，返回能通过情况下的最大能力值
    public static long myFunc2(int[] d, int[] p){
        int allMoney = 0;
        for (int i = 0; i < p.length; i++) {
            allMoney += p[i];
        }
        int N = d.length;
        for (int money = 0; money < allMoney; money++) {
            if (process2(d, p, N - 1, money) != -1) {
                return money;
            }
        }
        return allMoney;
    }

    private static long process2(int[] d, int[] p , int index, int money){
        if (index == -1) {
            return money == 0 ? 0 : -1;
        }
        // 必须要贿赂
        long p1 = process2(d, p, index - 1, money - p[index]);
        if (p1 != -1) {
            p1 += d[index];
        }
        // 不贿赂
        long p2 = process2(d, p, index - 1, money);
        if (p2 >= d[index]) {
            p1 = Math.max(p1, p2);
        }
        return p1;
    }

    private static long process1(int[] d, int[] p, int index, int ability) {
        if (index == p.length) {
            return 0;
        }
        long p1 = p[index] + process1(d, p, index + 1, ability + d[index]);
        if (ability >= d[index]) {
            p1 = Math.min(process1(d, p, index + 1, ability), p1);
        }
        return p1;
    }

    // 表大小：怪兽数量 * 能力总和 , 能力大的时候，明显不合适
    public static long myDp1(int[] d, int[] p) {
        int abilitySum = 0;
        for (int i : d) {
            abilitySum += i;
        }
        int[][] dp = new int[p.length + 1][abilitySum + 1];

        for (int index = p.length - 1; index >= 0; index--) {
            for (int ability = 0; ability < abilitySum + 1; ability++) {
                int p1 = Integer.MAX_VALUE;
                if (ability + d[index] <= abilitySum) {
                    p1 = p[index] + dp[index + 1][ability + d[index]];
                }
                if (ability >= d[index]) {
                    p1 = Math.min(dp[index + 1][ability], p1);
                }
                dp[index][ability] = p1;
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 20;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[][] arrs = generateTwoRandomArray(len, value);
            int[] d = arrs[0];
            int[] p = arrs[1];
            long ans1 = myFunc2(d, p);
            long ans2 = myDp1(d, p);
            long ans4 = minMoney2(d,p);
            if (ans1 != ans4 || ans2 != ans4) {
                System.out.println("oops!");
            }
        }

    }

}
