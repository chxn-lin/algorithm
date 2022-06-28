package com.sclin.class04;

import static class04.Code04_BiggerThanRightTwice.*;

public class BiggerThenRightTwice {

    public static void main(String[] args) {
        int testTime = 10;
        int maxSize = 10;
        int maxValue = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            printArray(arr1);
            int ret = myBiggerTwice(arr1);
            System.out.println("num:"+ret);
            if (ret != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static int myBiggerTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return myProcess(arr, 0, arr.length - 1);
    }

    private static int myProcess(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return myProcess(arr, l, mid) + myProcess(arr, mid + 1, r) + myMerge(arr, l, mid, r);
    }

    // 合并代码
    private static int myMerge(int[] arr, int L, int M, int R) {
        int ret = 0;
        int lIndex = L;
        int rIndex = M + 1;
        while (lIndex <= M && rIndex <= R) {
            if (arr[lIndex] > arr[rIndex]*2) {
                ret += R - rIndex + 1;
                lIndex++;
            } else {
                rIndex++;
            }
        }

        int[] help = new int[R - L + 1];
        lIndex = L;
        rIndex = M + 1;
        int temp = 0;
        while (lIndex <= M && rIndex <= R) {
            help[temp++] = arr[lIndex] > arr[rIndex] ? arr[lIndex++] : arr[rIndex++];
        }
        while (lIndex <= M) {
            help[temp++] = arr[lIndex++];
        }
        while (rIndex <= R) {
            help[temp++] = arr[rIndex++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

        return ret;
    }


}
