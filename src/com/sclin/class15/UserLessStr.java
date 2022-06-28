package com.sclin.class15;

import java.util.HashMap;

public class UserLessStr {

    public static void main(String[] args) {
        String[] arr = {"with","example"};
        String str = "thehat";
//        String[] arr = {"ba","c","abcd"};
//        String str = "babac";

        System.out.println(minStickers(arr, str));
    }

    public static int minStickers(String[] stickers, String target) {
        return getMinSum2(stickers, target);
    }

    /*给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
    arr每一个字符串，代表一张贴纸，贴纸可以重复选择，你可以把单个字符串剪开使用，
    目的是拼出str来，返回需要至少多少张贴纸可以完成这个任务。
    例子：str = "babac"，arr = {"ba","c","abcd"}*/
    public static int getMinSum1(String[] arr, String str){
        int i = process1(arr, str);
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    // 返回最少需要多少张，可以拼完
    private static int process1(String[] arr, String str) {
        if (str.length() == 0) {
            return 0;
        }
        int sum = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            String newStr = sub1(arr[i], str);
            if (newStr.equals(str)) {
                continue;
            }
            int subProcess = process1(arr, newStr);
            if (subProcess != Integer.MAX_VALUE) {
                subProcess = subProcess + 1;
            }
            sum = Math.min(subProcess, sum);
        }
        return sum;
    }

    // 获取使用该字符串之后的字符串
    private static String sub1(String useStr, String str) {
        int[] useArr = new int[26];
        for (char c : str.toCharArray()) {
            useArr[c - 'a']++;
        }
        for (char c : useStr.toCharArray()) {
            useArr[c - 'a']--;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < useArr.length; i++) {
            if (useArr[i] > 0) {
                for (int j = 0; j < useArr[i]; j++) {
                    result.append((char)(i + 'a'));
                }
            }
        }

        return result.toString();
    }

    public static int getMinSum2(String[] arr, String str){
        int[][] newArr = new int[arr.length][26];
        for (int i = 0; i < arr.length; i++) {
            for (char c : arr[i].toCharArray()) {
                newArr[i][c - 'a']++;
            }
        }
        int i = process2(newArr, str);
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    // 返回最少需要多少张，可以拼完
    private static int process2(int[][] newArr, String str) {
        if (str.length() == 0) {
            return 0;
        }
        int sum = Integer.MAX_VALUE;
        for (int i = 0; i < newArr.length; i++) {
            int[] useStr = newArr[i];
            if (useStr[str.toCharArray()[0] - 'a'] <= 0) {
                continue;
            }
            String newStr = sub2(useStr, str);
            if (newStr.equals(str)) {
                continue;
            }
            int subProcess = process2(newArr, newStr);
            if (subProcess != Integer.MAX_VALUE) {
                subProcess = subProcess + 1;
            }
            sum = Math.min(subProcess, sum);
        }
        return sum;
    }

    // 获取使用该字符串之后的字符串
    private static String sub2(int[] useArr, String str) {
        int[] newStrArr = new int[26];
        for (char c : str.toCharArray()) {
            newStrArr[c - 'a']++;
        }
        for (int i = 0; i < useArr.length; i++) {
            if (useArr[i] > 0) {
                newStrArr[i] -= useArr[i];
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < newStrArr.length; i++) {
            if (newStrArr[i] > 0) {
                for (int j = 0; j < newStrArr[i]; j++) {
                    result.append((char)(i + 'a'));
                }
            }
        }

        return result.toString();
    }

    public static int getMinSum3(String[] arr, String str){
        int[][] newArr = new int[arr.length][26];
        for (int i = 0; i < arr.length; i++) {
            for (char c : arr[i].toCharArray()) {
                newArr[i][c - 'a']++;
            }
        }
        HashMap<String, Integer> map = new HashMap();
        int i = process3(newArr, str, map);
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    // 返回最少需要多少张，可以拼完
    private static int process3(int[][] newArr, String str, HashMap<String, Integer> map) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        if (str.length() == 0) {
            map.put(str, 0);
            return 0;
        }
        int sum = Integer.MAX_VALUE;
        for (int i = 0; i < newArr.length; i++) {
            int[] useStr = newArr[i];
            if (useStr[str.toCharArray()[0] - 'a'] <= 0) {
                continue;
            }
            String newStr = sub2(useStr, str);
            if (newStr.equals(str)) {
                continue;
            }
            int subProcess = process3(newArr, newStr, map);
            if (subProcess != Integer.MAX_VALUE) {
                subProcess = subProcess + 1;
            }
            sum = Math.min(subProcess, sum);
        }
        map.put(str, sum);
        return sum;
    }

}
