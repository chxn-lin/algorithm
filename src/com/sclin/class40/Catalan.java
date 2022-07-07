package com.sclin.class40;

import static class39.Code04_DifferentBTNum.num2;

public class Catalan {

    public static long c(int big, int small) {
        long res = 1;
        for (int i = 0; i < small; i++) {
            res *= big;
            big--;
        }
        while (small > 1) {
            res /= small;
            small--;
        }
        return res;
    }

    public static long num1(int i){
        return c(2*i, i) - c(2*i, i - 1) ;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        int i = 3;
            long ans1 = num1(i);
            long ans2 = num2(i);
        System.out.println(ans1);
        System.out.println(ans2);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        System.out.println("test finish");
    }
}
