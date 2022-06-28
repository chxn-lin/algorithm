package com.sclin.class01;

import java.util.Arrays;

import static class01.Code01_SelectionSort.printArray;
import static class01.Code04_BSExist.*;

public class BSExist {

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            boolean myRet = false;
            try {
                myRet = myExist(arr, value);
            } catch (Exception e) {
                printArray(arr);
                System.out.println(value);
                succeed = false;
                break;
            }
            if (test(arr, value) != myRet) {
                succeed = false;
                printArray(arr);
                System.out.println(value);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
//        myExist(new int[]{-63,-62,-45,1,23,31,36,44,50,62}, -62);
    }

    public static boolean myExist(int[] arr, int value){
        if (arr.length == 1) {
            return arr[0] == value;
        }
        if (arr.length < 1) {
            return false;
        }
        int L = 0, R = arr.length - 1, mid = (L + R)/2;
        while (mid != L) {
            if (arr[mid] >= value) {
                R = mid;
            } else {
                L = mid;
            }
            mid = (L + R)/2;

        }

        return arr[mid] == value || arr[mid + 1] == value;
    }

}
