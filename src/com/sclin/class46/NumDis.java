package com.sclin.class46;

import static class46.Code02_RemoveBoxes.removeBoxes2;

public class NumDis {

    public static int method1(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }

        return process1(arr, 0, arr.length - 1, 0);
    }

    private static int process1(int[] arr, int L, int R, int K) {
        if (L > R) {
            return 0;
        }
        if (L == R) {
            return (K + 1) * (K + 1);
        }
        int res = (K + 1) * (K + 1) + process1(arr, L + 1, R, 0);

        // 代表和 i 位置的一起消
        for (int i = L + 1; i <= R; i++) {
            if (arr[i] == arr[L]) {
                res = Math.max(res,
                        process1(arr, i, R, K + 1)
                        + process1(arr, L + 1, i - 1, 0));
            }
        }

        return res;
    }

    public static int method2(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }

        return process2(arr, 0, arr.length - 1, 0);
    }

    private static int process2(int[] arr, int L, int R, int K) {
        if (L > R) {
            return 0;
        }
        if (L == R) {
            return (K + 1) * (K + 1);
        }
        // 到第一个不为L的位置
        int curIndex = L + 1;
        while (curIndex <= R && arr[curIndex] == arr[L]) {
            curIndex++;
        }
        int num = curIndex - L;

        int res = (K + num) * (K + num) + process2(arr, L + 1, R, 0);

        // 代表和 i 位置的一起消
        for (int i = Math.max(L + 1, curIndex - 1); i <= R; i++) {
            if (arr[i] == arr[L]) {
                res = Math.max(res,
                        process2(arr, i, R, K + 1)
                                + process2(arr, L + 1, i - 1, 0));
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,2,2,2,1,3,5,2,3,5};
        System.out.println(method1(arr));
        System.out.println(removeBoxes2(arr));
    }

}
