package com.sclin.class43;

import class43.Code01_CanIWin;

import java.util.HashMap;
import java.util.Map;

public class CanIWin {

    public static boolean method1(int choose, int total){
        if (total == 0) {
            return true;
        }
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }
        int[] arr = new int[choose + 1];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = i;
        }

        return process(arr, total);
    }

    private static boolean process(int[] arr, int total) {
        if (total <= 0) {
            return false;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != -1) {
                int tmp = arr[i];
                arr[i] = -1;
                boolean p1 = process(arr, total - tmp);
                arr[i] = tmp;
                if (!p1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean method2(int choose, int total){
        if (total == 0) {
            return true;
        }
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }

        return process2(choose, (1 << choose + 1) - 2 , total);
    }

    private static boolean process2(int choose, int indexStatus, int total) {
        if (total <= 0) {
            return false;
        }
        for (int i = 1; i < choose + 1; i++) {
            if ((indexStatus & 1 << i) != 0) {
                indexStatus = indexStatus - (1 << i);
                boolean p1 = process2(choose, indexStatus, total - i);
                indexStatus = indexStatus | (1 << i);
                if (!p1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean method3(int choose, int total){
        if (total == 0) {
            return true;
        }
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }

        Map<Integer, Boolean> map = new HashMap<>();
        return process3(choose, (1 << choose + 1) - 2 , total, map);
    }

    private static boolean process3(int choose, int indexStatus, int total, Map<Integer, Boolean> map) {
        if (total <= 0) {
            return false;
        }
        if (map.containsKey(indexStatus)) {
            return map.get(indexStatus);
        }
        boolean res = false;
        for (int i = 1; i < choose + 1; i++) {
            if ((indexStatus & 1 << i) != 0) {
                indexStatus = indexStatus - (1 << i);
                boolean p1 = process3(choose, indexStatus, total - i, map);
                indexStatus = indexStatus | (1 << i);
                if (!p1) {
                    res = true;
                    break;
                }
            }
        }
        map.put(indexStatus, res);
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(method2(5, 11));// false
//        System.out.println(method1(5, 11));// false
//        System.out.println(method2(18,19));// false

        int times = 100000;
        int falseNum = 0;
        for (int i = 0; i < times; i++) {
            int choose = (int)(Math.random() * 20);
            int total = (int)(Math.random() * choose * 3);
//            System.out.println(choose + " " + total);
            boolean p1 = method3(choose, total);
            boolean p2 = Code01_CanIWin.canIWin2(choose, total);
            if (p1 != p2) {
                System.out.println(choose + " " + total);
                System.out.println(p1 + " " + p2);
                System.out.println("Oops");
                falseNum++;
//                break;
            }
        }
        System.out.println("flaseNum:" + falseNum);
    }

}
