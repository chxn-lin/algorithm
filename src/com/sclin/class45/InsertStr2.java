package com.sclin.class45;

import class44.DC3;

import static class45.Code01_InsertS2MakeMostAlphabeticalOrder.maxCombine;
import static class45.Code01_InsertS2MakeMostAlphabeticalOrder.randomNumberString;

public class InsertStr2 {

    // 将str2插入到str1中
    public static String method1(String s1, String s2) {
        String ans = s2 + s1;
        for (int i = 1; i <= s1.length(); i++) {
            String cur = s1.substring(0, i) + s2 + s1.substring(i);
            ans = cur.compareTo(ans) > 0 ? cur : ans;
        }
        return ans;
    }

    public static String method2(String s1, String s2) {
        if (s1 == null || s1.length() == 0) {
            return s2;
        }
        if (s2 == null || s2.length() == 0) {
            return s1;
        }
        String ans = "";

        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < char1.length; i++) {
            min = Math.min(min, char1[i]);
            max = Math.max(max, char1[i]);
        }
        for (int i = 0; i < char2.length; i++) {
            min = Math.min(min, char2[i]);
            max = Math.max(max, char2[i]);
        }
        int[] all = new int[s1.length() + s2.length() + 1];
        int index = 0;
        for (int i = 0; i < char1.length; i++) {
            all[index++] = char1[i] - min + 2;
        }
        all[index++] = 1;
        for (int i = 0; i < char2.length; i++) {
            all[index++] = char2[i] - min + 2;
        }
        DC3 dc3 = new DC3(all, max - min + 2);
        int[] rank = dc3.rank;
        int comp = rank[char1.length + 1];
        int firstChoose = -1;
        for (int i = 0; i < char1.length; i++) {
            if (rank[i] < comp) {
                firstChoose = i;
                break;
            }
        }
        if (firstChoose == -1) {
            ans = s1 + s2;
        } else {
            String curComp = s1.substring(firstChoose, firstChoose + s2.length() < s1.length() ? firstChoose + s2.length() : s1.length());
            String curBest = s2 + curComp;
            for (int i = 1; i <= Math.min(s2.length(), s1.length() - firstChoose); i++) {
                String cur = curComp.substring(0, i) + s2 + curComp.substring(i);
                curBest = curBest.compareTo(cur) > 0 ? curBest : cur;
            }
            ans = s1.substring(0, firstChoose) + curBest + s1.substring(firstChoose + s2.length() < s1.length() ? firstChoose + s2.length() : s1.length());
        }

        return ans;
    }

    public static void main(String[] args) {
//        String s1 = "78911";
//        String s2 = "102";
//        System.out.println(method2(s1, s2));
//        System.out.println(maxCombine(s1, s2));

        int range = 10;
        int len = 50;
        int testTime = 100000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int s1Len = (int) (Math.random() * len);
            int s2Len = (int) (Math.random() * len);
            String s1 = randomNumberString(s1Len, range);
            String s2 = randomNumberString(s2Len, range);

            String ans1 = method2(s1, s2);
            String ans2 = maxCombine(s1, s2);
            if (!ans1.equals(ans2)) {
                System.out.println("Oops!");
                System.out.println(s1);
                System.out.println(s2);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");

        System.out.println("==========");

//        System.out.println("性能测试开始");
//        int s1Len = 1000000;
//        int s2Len = 500;
//        String s1 = randomNumberString(s1Len, range);
//        String s2 = randomNumberString(s2Len, range);
//        long start = System.currentTimeMillis();
//        maxCombine(s1, s2);
//        long end = System.currentTimeMillis();
//        System.out.println("运行时间 : " + (end - start) + " ms");
//        System.out.println("性能测试结束");
    }

}
