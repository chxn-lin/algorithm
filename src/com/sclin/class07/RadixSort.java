package com.sclin.class07;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import static class08.Code04_RadixSort.*;

public class RadixSort {

    /*
        取最大位数，然后将每一个数都前面补0，从低位开始，
        将相同的按照顺序放入桶，最后依次取出（先进先出），
        然后进行下一位比较，直到全部位数都遍历结束
     */

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
//            int[] arr1 = {3,21,9};
            printArray(arr1);
            int[] arr2 = copyArray(arr1);
            myRadixSort2(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

//        int[] arr = generateRandomArray(maxSize, maxValue);
//        printArray(arr);
//        myRadixSort(arr);
//        printArray(arr);

    }

    public static void myRadixSort2(int[] arr) {
        if (arr == null || arr.length <= 1)
            return;

        int maxBit = getMaxBit(arr);
        for (int i = 1; i <= maxBit; i++) {
            int[] copyArr = new int[arr.length];
            int[] help = new int[10];
            int[] countArr = new int[10];
            for (int j = 0; j < arr.length; j++) {
                help[getCurNum(arr[j], i)]++;
            }
            countArr[0] = help[0];
            for (int j = 1; j < countArr.length; j++) {
                countArr[j] = countArr[j - 1] + help[j];
            }
            for (int k = arr.length - 1; k >= 0; k--) {
                int curNum = arr[k];
                copyArr[countArr[getCurNum(curNum, i)] - 1] = curNum;
                countArr[getCurNum(curNum, i)]--;
            }
            for (int k = 0; k < arr.length; k++) {
                arr[k] = copyArr[k];
            }
        }
    }

    public static void myRadixSort(int[] arr) {
        if (arr == null || arr.length <= 1)
            return;

        int maxBit = getMaxBit(arr);
        List<Integer>[] arrInOut = new ArrayList[10];
        for (int i = 1; i <= maxBit; i++) {
            for (int k = 0; k < 10; k++) {
                arrInOut[k] = new ArrayList<Integer>(arr.length);
            }
            for (int j = 0; j < arr.length; j++) {
                // 拿出当前这一位的数字
                int curNum = getCurNum(arr[j], i);
                // 将这个数字放入对应的桶
                arrInOut[curNum].add(arr[j]);
            }
            // 倒出桶内的数据，进行下一步
            int index = 0;
            for (List<Integer> q : arrInOut) {
                for (int j = 0; j < q.size(); j++) {
                    arr[index++] = q.get(j);
                }
            }
        }
    }

    // 获取当前的这个深度的号码
    public static int getCurNum(int num, int dight){
        int preNum = (num / (int)Math.pow(10, dight - 1)) % 10;
        return preNum;
    }

    public static int getMaxBit(int[] arr){
        int max = getMaxValue(arr);
        int count = 0;
        while(max/((int)Math.pow(10, count+1)) > 0){
            count++;
        }
        return count + 1;
    }

    public static int getMaxValue(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

}
