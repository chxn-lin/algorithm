package com.sclin.class14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintAllSort {

    // 打印一个字符串的全部排列

    // 暴力方法
    public static List<String> subs(String s) {
        List<String> list = new ArrayList<>();
        List<Character> charList = new ArrayList();
        for (char c : s.toCharArray()) {
            charList.add(c);
        }
        process(list, charList, "");
        return list;
    }

    public static void process(List<String> list, List<Character> strArr, String path) {
        if (strArr == null || strArr.size() < 1) {
            list.add(path);
            return ;
        }
        int size = strArr.size();
        for (int i = 0; i < size; i++) {
            char cur = strArr.get(i);
            strArr.remove(i);

            process(list, strArr, path + cur);

            strArr.add(i, cur);
        }
    }

    // 去重
    public static List<String> subs2(String s) {
        List<String> list = new ArrayList<>();
        List<Character> charList = new ArrayList();
        for (char c : s.toCharArray()) {
            charList.add(c);
        }
        process2(list, charList, "");
        return list;
    }

    public static void process2(List<String> list, List<Character> strArr, String path) {
        if (strArr == null || strArr.size() < 1) {
            list.add(path);
            return ;
        }
        int size = strArr.size();
        List<Character> li = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            char cur = strArr.get(i);
            if (li.contains(cur)) {
                continue;
            }
            li.add(cur);
            strArr.remove(i);

            process2(list, strArr, path + cur);

            strArr.add(i, cur);
        }
    }

    public static void main(String[] args) {
        String str = "acc";
        System.out.println(subs2(str));
    }

}
