package com.sclin.class16;

import static class20.Code02_HorseJump.dp;

public class MoveHorse {

    //把整个象棋盘放入第一象限，棋盘的最左下角是（0,0）位置，
    //那么整个棋盘就是横左边上9条线，纵坐标上10条线的区域，
    //给你三个参数 x,y,k,返回 “马” 从 （0,0）位置触发，必须走k步

    public static int methodSum1(int x, int y, int k){
        if (x >= 9 || y >=10 || k < 0) {
            return 0;
        }
        return process1(0, 0, k, x, y);
    }

    private static int process1(int x, int y, int k, int aimX, int aimY) {
        if (x > 8 || y > 9 || x < 0 || y < 0) {
            return 0;
        }
        if (k == 0) {
            return x == aimX && y == aimY ? 1 : 0;
        }

        int result = process1(x + 1, y + 2, k - 1, aimX, aimY);
        result += process1(x + 1, y - 2, k - 1, aimX, aimY);
        result += process1(x - 1, y + 2, k - 1, aimX, aimY);
        result += process1(x - 1, y - 2, k - 1, aimX, aimY);
        result += process1(x + 2, y + 1, k - 1, aimX, aimY);
        result += process1(x + 2, y - 1, k - 1, aimX, aimY);
        result += process1(x - 2, y + 1, k - 1, aimX, aimY);
        result += process1(x - 2, y - 1, k - 1, aimX, aimY);

        return result;
    }

    public static int methodSum2(int x, int y, int k){
        if (x >= 9 || y >=10 || k < 0) {
            return 0;
        }
        int[][][] dp = new int[k + 1][9][10];

        dp[0][x][y] = 1;
        for (int k1 = 1; k1 <= k; k1++) {
            for (int x1 = 0; x1 < 9; x1++) {
                for (int y1 = 0; y1 < 10; y1++) {
                    int result = checkIsOut(dp, k1 - 1, x1 + 1, y1 + 2);
                    result += checkIsOut(dp, k1 - 1, x1 + 1, y1 - 2);
                    result += checkIsOut(dp, k1 - 1, x1 - 1, y1 + 2);
                    result += checkIsOut(dp, k1 - 1, x1 - 1, y1 - 2);
                    result += checkIsOut(dp, k1 - 1, x1 + 2, y1 + 1);
                    result += checkIsOut(dp, k1 - 1, x1 + 2, y1 - 1);
                    result += checkIsOut(dp, k1 - 1, x1 - 2, y1 + 1);
                    result += checkIsOut(dp, k1 - 1, x1 - 2, y1 - 1);
                    dp[k1][x1][y1] = result;
                }
            }
        }
        return dp[k][0][0];
    }

    private static int checkIsOut(int[][][] dp, int k, int x, int y){
        if (x > 8 || y > 9 || x < 0 || y < 0) {
            return 0;
        } else {
            return dp[k][x][y];
        }
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(dp(x, y, step));
        System.out.println(methodSum1(x, y, step));
        System.out.println(methodSum2(x, y, step));
    }

}
