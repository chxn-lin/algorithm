package com.sclin.class04;

import static class04.Code01_MergeSort.*;

public class MergeSort {

    // 排序
    // 归并排序的两种写法
    public static void main(String[] args) {

        int testTime = 10000;
        int maxSize = 10;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
//            int[] arr1 = {-47,-31,-61,61,0,-10,-43,37,-7,54};
            printArray(arr1);
            int[] arr2 = copyArray(arr1);
            myMergeSort2(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");

    }

    // 递归方法实现
    private static void myMergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // 排序，使得arr的left到right上有序
    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        process(arr, L, M);
        process(arr, M + 1, R);
        myMerge(arr, L, M, R);
    }

    // 合并数组L-R上的数据
    private static void myMerge(int[] arr, int L, int M, int R) {
        int[] temp = new int[R - L + 1];

        int lIndex = L, rIndex = M + 1, tempIndex = 0;
        while (lIndex <= M && rIndex <= R) {
            temp[tempIndex++] = arr[lIndex] < arr[rIndex] ? arr[lIndex++] : arr[rIndex++];
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
    }

    // 非递归方法实现
    private static void myMergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;
        int size = 1;
        while (size < len) {
            // 当前对象的第一个位置
            int L = 0;
            while (L < len) {
                int M = L + size - 1;
                if (M > len - 1) {
                    break;
                }
                int R = Math.min(M + size, len - 1);

                myMerge(arr, L, M, R);
                // 结束的时候，将L的值改变下
                L = R + 1;
            }
            if (size > len / 2) {
                break;
            }
            size <<= 1;
        }
    }

}
