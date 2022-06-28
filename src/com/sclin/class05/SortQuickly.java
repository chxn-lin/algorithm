package com.sclin.class05;

import static class05.Code02_PartitionAndQuickSort.*;

public class SortQuickly {

    public static void main(String[] args) {

        // 跑大样本随机测试（对数器）
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            question3(arr1);
            quickSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed);
//        question1(arr, key);
//        question2(arr, key);

    }

    /**
     * 1、给定一个数，将数组<=当前数的放在左边，>的放在右边
     * 要求：空间复杂度O(1)，时间复杂度O(n)
     * 思路：划定一个区域，从-1开始，从左往右遍历，如果小于等于，那么将该数和区域下标的下一个位置交换，否则遍历继续。
     * <p>
     * 2、< 的放左边，=的放中间，>的放右边
     */

    private static void question1(int[] arr, int key) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int L = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= key) {
                sw(arr, ++L, i);
            }
        }
    }

    private static void question2(int[] arr, int key) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int L = -1;
        int R = arr.length;
        int index = 0;
        while (index < R) {
            if (arr[index] < key) {
                sw(arr, ++L, index++);
            } else if (arr[index] == key) {
                index++;
            } else {
                sw(arr, --R, index);
            }
        }
    }

    private static void question3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        myProcess(arr, 0, arr.length - 1);

    }

    private static void myProcess(int[] arr, int L, int R){
        if (L >= R) {
            return;
        }
        sw(arr, R, (int)(Math.random() * (R - L + 1)) + L);
        int[] retArr = nei(arr, L, R);
        myProcess(arr, L, retArr[0] - 1);
        myProcess(arr, retArr[1] + 1, R);
    }

    private static int[] nei(int[] arr, int L, int R){
        int start = L - 1;
        int end = R;
        int index = L;
        while (index < end) {
            if (arr[index] < arr[R]) {
                sw(arr, index++, ++start);
            } else if (arr[index] == arr[R]) {
                index++;
            } else {
                sw(arr, index, --end);
            }
        }
        sw(arr, R, end);

        int[] retArr = new int[2];
        retArr[0] = ++start;
        retArr[1] = end;

        return retArr;
    }

    private static void sw(int[] arr, int ind1, int ind2) {
        int temp = arr[ind1];
        arr[ind1] = arr[ind2];
        arr[ind2] = temp;
    }

}
