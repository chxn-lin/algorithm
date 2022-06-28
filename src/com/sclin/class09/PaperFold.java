package com.sclin.class09;

public class PaperFold {

    public static void printWhthLevel(int level){
        subPrint(0, level, true);
    }

    public static void subPrint(int cur, int level, boolean flag){
        if (cur >= level) {
            return ;
        }
        subPrint(cur + 1, level, true);
        System.out.print(flag ? "凹" : "凸");
        subPrint(cur + 1, level, false);
    }

    public static void main(String[] args) {
        printWhthLevel(3);
    }

}
