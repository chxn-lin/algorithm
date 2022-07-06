package com.sclin.class38;

public class UseSerialNo {

    public static boolean check(int n){
        for (int i = 1; i < n; i++) {
            int sum = i;
            for (int j = i + 1; j < n; j++) {
                sum += j;
                if (sum == n) {
                    return true;
                } else if (sum > n) {
                    continue;
                }
            }
        }
        return false;
    }

    public static boolean checkFast(int n){
        return (n & (-n)) != n;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 200; i++) {
            if (check(i) != checkFast(i)) {
                System.out.println("oops");
            }
        }
    }

}
