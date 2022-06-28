package com.sclin.class11;

import class14.Code03_BestArrange.Program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static class14.Code03_BestArrange.bestArrange2;
import static class14.Code03_BestArrange.generatePrograms;

public class GetMaxRoom {

    /*
        一些项目要占用一个会议室演讲，会议室不能同时容纳两个项目的宣讲。
        给你每一个项目开始的时间和结束的时间，你来安排宣讲的日程，
        要求会议室进行的宣讲的场次最多，返回最多的宣讲场次
     */

    // 暴力
    public static int method1(Program[] programs) {
        return process(programs, 0);
    }

    public static int process(Program[] programs, int start) {
        if (programs == null || programs.length < 1) {
            return 0;
        }
        Program pr = null;
        int max = 0;
        for (int i = 0; i < programs.length; i++) {
            pr = programs[i];
            if (pr.start < start) {
                continue;
            }
            Program[] newArr = copyAndRemoveByTime(programs, pr.end);
            max = Math.max(max, 1 + process(newArr, pr.end));
        }
        return max;
    }

    // 排除掉不符合的
    public static Program[] copyAndRemoveByTime(Program[] programs, int end) {
        if (programs == null || programs.length < 2) {
            return null;
        }
        List<Program> list = new ArrayList<>();
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= end) {
                list.add(new Program(programs[i].start, programs[i].end));
            }
        }
        return list.toArray(new Program[list.size()]);
    }

    public static int method2(Program[] programs) {
        Arrays.sort(programs, new MyComp());
        int end = 0;
        int result = 0;
        Program program = null;
        for (int i = 0; i < programs.length; i++) {
            program = programs[i];
            if (end > program.start) {
                continue;
            }
            result++;
            end = program.end;
        }

        return result;
    }

    private static class MyComp implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }

    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 100000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (method2(programs) != method1(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
