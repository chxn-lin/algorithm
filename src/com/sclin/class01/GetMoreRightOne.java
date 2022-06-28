package com.sclin.class01;

public class GetMoreRightOne {

    public static void main(String[] args) {
        int a = 12;
        System.out.println(getMethod(a));
    }

    static int getMethod(int a){
        return a & (~a + 1);
    }
}
