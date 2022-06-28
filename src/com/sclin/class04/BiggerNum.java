package com.sclin.class04;

import static class04.Code03_ReversePair.*;

public class BiggerNum {

    // 求逆序数量
    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
//            int[] arr1 = {52, -41, -63, 68};
            int[] arr2 = copyArray(arr1);
            if (myReverPairNumber(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static int myReverPairNumber(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return myProcess(arr, 0, arr.length - 1);
    }

    private static int myProcess(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return myProcess(arr, L, M)
                + myProcess(arr, M + 1, R)
                + myMerge(arr, L, M, R);
    }

    private static int myMerge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int lIndex = L;
        int rIndex = M + 1;
        int temp = 0;
        int ret = 0;
        while (lIndex <= M && rIndex <= R) {
            ret += arr[lIndex] > arr[rIndex] ? R - rIndex + 1 : 0;
            help[temp++] = arr[lIndex] > arr[rIndex] ? arr[lIndex++] : arr[rIndex++];
        }
        while (lIndex <= M) {
            help[temp++] = arr[lIndex++];
        }
        while (rIndex <= R) {
            help[temp++] = arr[rIndex++];
        }
        // 拷贝数组
        for (int i = 0; i < help.length; i++) {
            arr[i + L] = help[i];
        }

        return ret;
    }


}
