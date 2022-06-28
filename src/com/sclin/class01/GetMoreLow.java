package com.sclin.class01;

import static class01.Code01_SelectionSort.*;

import java.util.Random;

public class GetMoreLow {

    public static void main(String[] args) {
//        int[] arr = new int[]{0, -5};
        int[] arr = getRandomArr();
        printArray(arr);


        int low = getLow(arr);
////        int low = Code06_BSAwesome.getLessIndex(arr);
//        System.out.println(low);
        System.out.println(arr[low]);

    }

    private static int getLow(int[] arr) {
        if (arr.length <= 1) {
            return -1;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int L = 1, R = arr.length - 2, mid;
        while(L < R){
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid + 1])
                L = mid;
            else if (arr[mid] > arr[mid - 1])
                R = mid;
            else
                return mid;

        }
        return L;

    }

    public static int[] getRandomArrWithOneLow(){
        Random random = new Random();
        int length = random.nextInt(100);
        int[] arr = new int[length];
        int lowIndex = random.nextInt(length);
        arr[lowIndex] = random.nextInt(100);

        for (int i = lowIndex - 1; i >= 0; i--) {
            arr[i] = arr[i + 1] + random.nextInt(10) + 1;
        }
        for (int i = lowIndex + 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + random.nextInt(10) + 1;
        }
        return arr;
    }

    public static int[] getRandomArr(){
        Random random = new Random();
        int length = random.nextInt(8) + 2;
        int[] arr = new int[length];

        arr[arr.length - 1] = random.nextInt(20) - 10;
        for (int i = arr.length - 2; i >= 0; i--) {
            int k = (int)(random.nextInt(20) - 10);
            while (k == 0) {
                k  = (int)(random.nextInt(20) - 10);
            }
            arr[i] = arr[i + 1] + k;
        }
        return arr;
    }


}
