package com.sclin.class15;

import static class19.Code02_ConvertToLetterString.*;

public class FindSumNum {

    /*
    规定1和A对应，2和B对应、3和c对应...
那么一个数组字符串，比如“111” 就可以转化为：
“AAA”、“KA”和“AK”，
给定一个只有数字字符串组成的字符串str，
返回有多少种转化的结果
     */
    public static int getNum(String str) {
        if (str == null || str.length() < 1){
            return 0;
        }
        char[] chars = str.toCharArray();
        return process1(chars, 0);
    }

    private static int process1(char[] chars, int index) {
        if (index == chars.length) {
            return 1;
        }
        if (chars[index] == '0') {
            return 0;
        }
        int p1 = process1(chars, index + 1);
        if (index + 1 < chars.length && ((chars[index] - '0') * 10 + chars[index + 1]) - '0' < 27) {
            p1 += process1(chars, index + 2);
        }

        return p1;
    }

    public static int myDp(String str) {
        if (str == null || str.length() < 1){
            return 0;
        }
        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length + 1];

        dp[dp.length - 1] = 1;
        for (int index = dp.length - 2; index >= 0; index--) {
            if (chars[index] == '0') {
                dp[index] = 0;
            } else {
                int p1 = dp[index + 1];
                if (index + 1 < chars.length && ((chars[index] - '0') * 10 + chars[index + 1]) - '0' < 27) {
                    p1 += dp[index + 2];
                }
                dp[index] = p1;
            }
        }

        return dp[0];
    }


    // 为了测试
    public static void main(String[] args) {

//        System.out.println(getNum("7391"));
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = getNum(s);
            int ans1 = myDp(s);
            int ans2 = dp2(s);
            if (ans0 != ans1 || ans0 != ans2) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
