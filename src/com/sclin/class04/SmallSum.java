package com.sclin.class04;

import static class04.Code02_SmallSum.*;

public class SmallSum {

    // 求小和
    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
//            int[] arr1 = new int[]{14,51,60};
            int[] arr2 = copyArray(arr1);
            int comparator = comparator(arr2);
            int mySum = mySmallSum(arr1);
            if (mySum != comparator) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    public static int mySmallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    // 排序，使得arr的left到right上有序
    private static int process(int[] arr, int L, int R) {
        int ret = 0;
        if (L == R) {
            return ret;
        }
        int M = L + ((R - L) >> 1);
        ret = process(arr, L, M) + process(arr, M + 1, R) + myMerge(arr, L, M, R);
        return ret;
    }

    // 合并数组L-R上的数据
    private static int myMerge(int[] arr, int L, int M, int R) {
        int ret = 0;
        int[] temp = new int[R - L + 1];

        int lIndex = L, rIndex = M + 1, tempIndex = 0;
        while (lIndex <= M && rIndex <= R) {
            if (arr[lIndex] < arr[rIndex]) {
                ret += (R - rIndex + 1) * arr[lIndex];
                temp[tempIndex++] = arr[lIndex++];
            } else {
                temp[tempIndex++] = arr[rIndex++];
            }
        }
        while (lIndex <= M) {
            temp[tempIndex++] = arr[lIndex++];
        }
        while (rIndex <= R) {
            temp[tempIndex++] = arr[rIndex++];
        }

        // 将临时数组的数据拷贝到原数组
        for (int i = 0; i < temp.length; i++) {
            arr[L + i] = temp[i];
        }

        return ret;
    }

}
