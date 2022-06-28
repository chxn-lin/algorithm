package com.sclin.class01;


import static class01.Code01_SelectionSort.*;

public class Maopao {

    // 冒泡
    public static int[] maopaoSort(int[] arr){
        if (arr.length == 0 || arr.length < 2) {
            return arr;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j] < arr[j-1])
                    sw(arr, j, j-1);
            }
        }
        return arr;

    }

    // 插入
    public static int[] insertSort(int[] arr){
        if (arr.length == 0 || arr.length < 2) {
            return arr;
        }

        int ins;
        // 0 ~ i 范围上升序
        for (int i = 1; i < arr.length; i++) {
            ins = getIns(arr, i);

            insertIndex(arr, ins, i);
        }
        return arr;
    }

    public static int getIns(int[] arr, int i){
        if (arr[i] >= arr[i - 1])
            return i;
        if (arr[i] <= arr[0])
            return 0;
        int start = 0, end = i, mid = (end + 1)/2, ins = 0;
        while (mid != end && mid != start) {
            if (arr[mid] > arr[i]) {
                end = mid;
                mid = (start + end + 1)/2;
            } else {
                start = mid;
                mid = (start + end + 1)/2;
            }

        }
        ins = end;
        return ins;
    }

    public static void sw(int[] arr, int i, int j){
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
    public static void insertIndex(int[] arr, int ins, int ind){
        int temp = arr[ind];
        for (int i = ind; i > ins; i--) {
            arr[i] = arr[i - 1];
        }
        arr[ins] = temp;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            selectionSort(arr1);
            // comparator(arr2);
//            maopaoSort(arr2);
            insertSort(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);

                printArray(arr);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

//        int[] arr = new int[]{82,22,-2,-33,77};
//        insertSort(arr);
//        insertIndex(arr, 1, 2);
//        printArray(arr);
    }

}
