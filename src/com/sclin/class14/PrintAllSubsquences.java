package com.sclin.class14;

import java.util.ArrayList;
import java.util.List;

public class PrintAllSubsquences {

    // 打印一个字符串所有的子序列
    public static List<String> subs(String s) {
        List<String> list = new ArrayList<>();
        process(list, 0, s.toCharArray(), "");
        return list;
    }

    public static void process(List<String> list, int index, char[] strArr, String path) {
        if (index > strArr.length - 1) {
            list.add(path);
            return;
        }
        process(list, index + 1, strArr, path);
        process(list, index + 1, strArr, path + strArr[index]);
    }

    public static void main(String[] args) {
        String str = "abc";
        List<String> list = subs(str);
        System.out.println(list);
    }


}
