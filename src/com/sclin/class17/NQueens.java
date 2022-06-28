package com.sclin.class17;

import static class23.Code03_NQueens.num2;

public class NQueens {

//    N皇后问题是指N*N的棋盘上，要摆N个皇后，
//    要求任何两个皇后都不同行，不同列，也不在同一条斜线上，
//    给定一个整数n，返回n皇后的摆法有多少种。
    public static int method1(int n){
        if (n < 1) {
            return 0;
        }

        int[] useArr = new int[n];
        return process(useArr, n, 0);
    }

    private static int process(int[] useArr, int n, int index) {
        if (index == n) {
            return 1;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (check(useArr, index, i)) {
                useArr[index] = i;
                result += process(useArr, n, index + 1);
            }
        }
        return result;
    }

    public static int method2(int n){
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = (1 << n) - 1;

        return process2(limit, 0, 0, 0);
    }

    private static int process2(int limit, int chooseIndex, int left, int right) {
        if (chooseIndex == limit) {
            return 1;
        }

        int result = 0;

        int mostRightOne = 0;
        int canUse = limit ^ (chooseIndex | left | right);
        while (canUse != 0) {
            mostRightOne = canUse & (~canUse + 1);
            int newChoose = mostRightOne | chooseIndex;
            int newLeft = limit & ((left | mostRightOne) << 1);
            int newRight = limit & ((right | mostRightOne) >>> 1);
            result += process2(limit, newChoose, newLeft, newRight);

            canUse = canUse - mostRightOne;
        }

        return result;
    }

    public static boolean check(int[] arr, int index, int insertValue){
        boolean result = true;
        for (int i = index - 1; i >= 0; i--) {
            if (arr[i] == insertValue
                    || Math.abs(i - index) == Math.abs(arr[i] - insertValue))
                result = false;
        }
        return result;
    }

    public static void main(String[] args) {

        int n = 7;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(method2(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }

}
