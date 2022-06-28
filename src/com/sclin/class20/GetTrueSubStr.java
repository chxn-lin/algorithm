package com.sclin.class20;

import static class26.Code03_ZeroLeftOneStringNumber.getNum3;

public class GetTrueSubStr {

    public static int method1(int n){
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }

    private static int process(int pre, int n) {
        if (n == 1) {
            return 1;
        }
        if (n < 1) {
            return 0;
        }
        int result = 0;
        if (pre == 1) {
            result += process(0, n - 1) + process(1, n - 1);
        } else {
            result += process(1, n - 1);
        }
        return result;
    }

    public static int method2(int n){
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process2(n - 1);
    }

    private static int process2(int n) {
        if (n == 1) {
            return 2;
        }
        if (n < 1) {
            return 1;
        }
        int result = 0;

        result += process2(n - 1) + process2(n - 2);

        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i != 20; i++) {
            System.out.println(method2(i));
            System.out.println(getNum3(i));
            System.out.println("===================");
        }

    }

}
