package com.sclin.class15;

public class CardChoose {

    public static int method1(int[] arr){
        int f = f1(arr, 0, arr.length - 1);
        int g = g1(arr, 0, arr.length - 1);

        return Math.max(f, g);
    }

    // 返回最好结果
    public static int f1(int[] arr, int L, int R){
        if (L == R) {
            return arr[L];
        }
        int chooseL = arr[L] + g1(arr, L + 1, R);
        int chooseR = arr[R] + g1(arr, L, R - 1);
        return Math.max(chooseL, chooseR);
    }

    // 返回最坏结果
    public static int g1(int[] arr, int L, int R){
        if (L == R) {
            return 0;
        }
        int chooseL = f1(arr, L + 1, R);
        int chooseR = f1(arr, L, R - 1);
        return Math.min(chooseL, chooseR);
    }

    public static int method3(int[] arr){
        int[][] dpf = new int[arr.length][arr.length];
        int[][] dpg = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            int L = 0;
            int R = i;
            while (R < arr.length && L < arr.length) {
                if (L == R) {
                    dpf[L][R] = arr[L];
                } else {
                    int chooseL = 0;
                    if (L + 1 < arr.length) {
                        chooseL = arr[L] + dpg[L + 1][R];
                    }

                    int chooseR = arr[R] + dpg[L][R - 1];
                    dpf[L][R] = Math.max(chooseL, chooseR);

                    chooseL = 0;
                    if (L + 1 < arr.length) {
                        chooseL = dpf[L + 1][R];
                    }
                    chooseR = dpf[L][R - 1];
                    dpg[L][R] = Math.min(chooseL, chooseR);
                }
                L++;
                R++;
            }
        }

        return Math.max(dpf[0][arr.length - 1], dpg[0][arr.length - 1]);
    }

    public static int method2(int[] arr){
        int[][] dpf = new int[arr.length][arr.length];
        int[][] dpg = new int[arr.length][arr.length];

        int f = f2(arr, 0, arr.length - 1, dpf, dpg);
        int g = g2(arr, 0, arr.length - 1, dpf, dpg);

        return Math.max(f, g);
    }

    // 返回最好结果
    public static int f2(int[] arr, int L, int R, int[][] dpf, int[][] dpg){
        if (dpf[L][R] != 0) {
            return dpf[L][R];
        }
        if (L == R) {
            dpf[L][R] = arr[L];
            return arr[L];
        }
        int chooseL = arr[L] + g2(arr, L + 1, R, dpf, dpg);
        int chooseR = arr[R] + g2(arr, L, R - 1, dpf, dpg);

        dpf[L][R] = Math.max(chooseL, chooseR);
        return Math.max(chooseL, chooseR);
    }

    // 返回最坏结果
    public static int g2(int[] arr, int L, int R, int[][] dpf, int[][] dpg){
        if (dpg[L][R] != 0) {
            return dpg[L][R];
        }
        if (L == R) {
            dpg[L][R] = 0;
            return 0;
        }
        int chooseL = f2(arr, L + 1, R, dpf, dpg);
        int chooseR = f2(arr, L, R - 1, dpf, dpg);

        dpg[L][R] = Math.min(chooseL, chooseR);
        return Math.min(chooseL, chooseR);
    }

    public static void main(String[] args) {
        int arr[] = {10, 31, 11, 8};
        System.out.println(method1(arr));
        System.out.println(method2(arr));
        System.out.println(method3(arr));
    }

}
