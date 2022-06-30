package com.sclin.class29;

import static class29.Code01_FindMinKth.generateRandomArray;
import static class29.Code01_FindMinKth.minKth3;
import static com.sclin.class01.KM1.printArr;

public class QuickSort {

    // 快排实现找出第K小的数
    public static int quickSort(int[] arr, int k) {
        int i = find(arr, 0, arr.length - 1, k - 1);
        return arr[i];
    }

    public static int find(int[] arr, int L, int R, int k) {
        if (L == R) {
            return L;
        }
        int key = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] retArr = partition(arr, L, R, key);
        if (k >= retArr[0] && k <= retArr[1]) {
            return retArr[0];
        } else if (k > retArr[1]) {
            return find(arr, retArr[1] + 1, R, k);
        } else {
            return find(arr, L, retArr[0] - 1, k);
        }
    }

    public static int[] partition(int[] arr, int L, int R, int key) {
        int tempL = L - 1;
        int tempR = R + 1;
        int index = L;
        while (index != tempR) {
            if (arr[index] < key) {
                sw(arr, ++tempL, index++);
            } else if (arr[index] == key) {
                index++;
            } else {
                sw(arr, --tempR, index);
            }
        }
        return new int[] {tempL + 1, tempR - 1};
    }

    public static void sw(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    public static void main(String[] args) {
//        int[] arr = {87, 39, 5, 4, 95, 7};
//        int k = 6;
//        int ans2 = quickSort(arr, k);
//        System.out.println(ans2);

        int testTime = 1000000;
        int maxSize = 6;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
//            printArr(arr);
//            System.out.println();
//            System.out.println(k);
            int ans2 = quickSort(arr, k);
            int ans3 = minKth3(arr, k);
            if (ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
