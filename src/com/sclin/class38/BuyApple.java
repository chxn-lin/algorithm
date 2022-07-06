package com.sclin.class38;

public class BuyApple {

    public static int buyApple(int n) {
        int bage8 = n / 8;
        for (int i = bage8; i >= 0; i--) {
            int rest = n - 8 * i;
            if (rest % 6 == 0) {
                return i + rest / 6;
            }
        }
        return -1;
    }

    public static int buyAppleFast(int n) {
        if (n % 2 == 1) {
            return -1;
        }
        if (n < 18) {
            if (n == 0) {
                return 0;
            } else if (n == 6 || n == 8) {
                return 1;
            } else if (n == 12 || n == 14 || n == 16) {
                return 2;
            } else {
                return -1;
            }
        }
        return (n - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            if(buyApple(i) != buyAppleFast(i)){
                System.out.println("oops");
            }
//            System.out.println("" + i + " : " + buyApple(i));
        }
    }

}
