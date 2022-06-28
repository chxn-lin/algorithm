package com.sclin.class11;

import java.util.Arrays;
import java.util.Comparator;

import static class13.Code05_LowestLexicography.*;

public class GetOrderMin {

    /*
        给定一个由字符串组成的数组strs，必须把所有的字符串拼接起来，
        返回所有可能的拼接结果中，字典序最小的结果。
     */

    // 暴力破解
    public static String method1(String[] strs){
        if (strs == null) {
            return null;
        }
        return process(strs);
    }

    public static String process(String[] strs){
        if (strs == null) {
            return "";
        }
        String retStr = null;
        for (int i = 0; i < strs.length; i++) {
            String[] newStrs = removeIndexAndRetNew(strs, i);
            String process = strs[i] + process(newStrs);

            if (retStr == null) {
                retStr = process;
            } else {
                retStr = retStr.compareTo(process) < 0 ? retStr : process;
            }
        }

        return retStr;
    }

    public static String[] removeIndexAndRetNew(String[] strs, int index){
        if (strs == null || strs.length < 2) {
            return null;
        }
        String[] newStr = new String[strs.length - 1];
        int k = 0;
        for (int i = 0; i < strs.length; i++) {
            if (i == index) {
                continue;
            }
            newStr[k++] = strs[i];
        }
        return newStr;
    }

    // 贪心算法
    public static String method2(String[] strs){
        if (strs == null) {
            return "";
        }
        Arrays.sort(strs, new MyComp());

        StringBuilder retStr = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            retStr.append(strs[i]);
        }
        return retStr.toString();
    }

    private static class MyComp implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static void main(String[] args) {

        System.out.println("a".compareTo("b"));
        System.out.println(new MyComp().compare("a", "b"));

//        int arrLen = 6;
//        int strLen = 5;
//        int testTimes = 10000;
//        System.out.println("test begin");
//        for (int i = 0; i < testTimes; i++) {
//            String[] arr1 = generateRandomStringArray(arrLen, strLen);
//            String[] arr2 = copyStringArray(arr1);
//            String method1 = method1(arr2);
//            if (!lowestString2(arr1).equals(method1)) {
//                for (String str : arr1) {
//                    System.out.print(str + ",");
//                }
//                System.out.println();
//                System.out.println("Oops!");
//            }
//        }
//        System.out.println("finish!");
    }

}
