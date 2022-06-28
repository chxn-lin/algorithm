package com.sclin.class18;

import class24.Code03_GasStation;

import java.util.LinkedList;

public class GasCanCircuit {

    /*
     加油站的良好出发点问题
     */
    public static boolean[] goodArray(int[] g, int[] c) {
        int len = g.length;
        boolean[] result = new boolean[len];

        int[] arr1 = new int[len * 2 - 1];
        for (int i = 0; i < len * 2 - 1; i++) {
            arr1[i] = g[i % len] - c[i % len];
        }
        int[] preArr = new int[arr1.length];
        preArr[0] = arr1[0];
        for (int i = 1; i < arr1.length; i++) {
            preArr[i] = arr1[i] + preArr[i - 1];
        }
        LinkedList<Integer> qMin = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            while (!qMin.isEmpty() && preArr[qMin.peekLast()] >= preArr[i]) {
                qMin.pollLast();
            }
            qMin.add(i);
        }
        int index = 0;
        result[index++] = preArr[qMin.peekFirst()] >= 0;
        for (int i = len; i < len * 2 - 1; i++) {
            while (!qMin.isEmpty() && preArr[qMin.peekLast()] >= preArr[i]) {
                qMin.pollLast();
            }
            qMin.add(i);
            if (i - len == qMin.peekLast()) {
                qMin.pollFirst();
            }
            result[index++] = preArr[qMin.peekFirst()] - preArr[i - len] >= 0;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] g = {1,4,2,1,24,23};
        int[] c = {2,1,3,2,7,20};

        System.out.println("my:" + canCompleteCircuit(g,c));
        System.out.println("true:" + Code03_GasStation.canCompleteCircuit(g,c));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        boolean[] good = goodArray(gas, cost);
        for (int i = 0; i < gas.length; i++) {
            if (good[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void printArray(boolean[] arr){
        for (boolean b : arr) {
            System.out.print(b + ", ");
        }
    }

}
