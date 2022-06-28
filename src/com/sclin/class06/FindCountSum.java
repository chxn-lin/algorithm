package com.sclin.class06;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FindCountSum {

    /**
     * 给定很多线段，每个线段都有两个数组[start, end]，
     * 表示线段开始位置和结束位置，左右都是闭区间
     * 规定：
     * 1）线段的开始和结束位置，一定都是整数值
     * 2）线段重合区域的长度必须>=1
     * 返回线段最多重合区域中，包含了几条线段
     */

    public static void main(String[] args) {
        Segment[] arr = {
                new Segment(1, 5)
                , new Segment(6, 8)
                , new Segment(2, 5)
        };

//        System.out.println(math1(arr));
        System.out.println(math2(arr));
    }

    // 暴力
    private static int math1(Segment[] arr) {
        int min = arr[0].start;
        int max = arr[0].end;
        for (int i = 1; i < arr.length; i++) {
            min = arr[i].start < min ? arr[i].start : min;
            max = arr[i].end > max ? arr[i].end : max;
        }
        int maxSum = 0;
        int curSum = 0;
        for (float i = min + 0.5f; i < max; i++) {
            curSum = 0;
            for (int j = 0; j < arr.length; j++) {
                curSum += arr[j].start < i && arr[j].end > i ? 1 : 0;
            }
            maxSum = maxSum > curSum ? maxSum : curSum;
        }

        return maxSum;
    }

    // 利用heap
    private static int math2(Segment[] arr) {
        // 将数组按照start排序
        Arrays.sort(arr, (s1, s2) -> s1.start - s2.start);
        PriorityQueue<Integer> heap = new PriorityQueue();
        int maxCount = 0;
        for (Segment s : arr) {
            int start = s.start;
            int end = s.end;

            while (heap.peek() != null && heap.peek() <= start) {
                heap.poll();
            }
            heap.add(end);
            maxCount = maxCount > heap.size() ? maxCount : heap.size();
        }

        return maxCount;
    }

    private static class Segment {
        private int start;
        private int end;

        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Segment{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

    }

}
