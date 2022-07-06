package com.sclin.class38;

public class Eat {

    public static String eat1(int n){
        return p(n);
    }

    private static String p(int n){
        if (n == 0) {
            return "后手获胜";
        }
        int eatNum = 1;
        while (eatNum <= n) {
            if ("后手获胜".equals(p(n - eatNum))) {
                return "先手获胜";
            }
            eatNum <<= 2;
        }
        return "后手获胜";
    }

    public static String eat2(int n){
        int i = n % 5;
        return i == 0 || i == 2 ? "后手获胜" : "先手获胜";
    }

    public static void main(String[] args) {
        for (int i = 0; i <50; i++) {
            if (eat1(i) != eat2(i)) {
                System.out.println("oops!");
            }
            System.out.println(i + " : " + eat1(i));
        }
        System.out.println("结束");
    }

}
