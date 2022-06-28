package com.sclin.class14;

public class Hanio {

    // 汉诺塔问题
    public static void hanoi1(int n) {
        move(n, "left", "right", "mid");
    }

    // 将n个节点，从from 移动到 to，other是其中的辅助
    private static void move(int n, String from, String to, String other) {
        if (n <= 1) {
            System.out.println("将 1 从 " + from + " 移动到 " + to + " ;");
            return ;
        }
        move(n - 1, from, other, to);
        System.out.println("将 " + n + " 从 " + from + " 移动到 " + to + " ;");
        move(n - 1, other, to, from);
    }

    public static void main(String[] args) {
        hanoi1(3);
    }

}
