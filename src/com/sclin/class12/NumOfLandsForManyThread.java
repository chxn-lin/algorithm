package com.sclin.class12;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NumOfLandsForManyThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        char[][] arr = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println(new NumOfLandsForManyThread().numIslands(arr));
    }

    public int numIslands(char[][] grid){
        int s = 0;
        try {
            s = getSize2(grid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static int getSize2(char[][] arr) throws ExecutionException, InterruptedException {
        if (arr == null) {
            return 0;
        }
        int rowSize = arr.length;
        int culSize = arr[0].length;
        UnionForLand uni = new UnionForLand(arr);
        // 如果按列分
        Queue<Integer[]> q1 = null;
        Queue<Integer[]> q2 = null;
        FutureTask<Queue<Integer[]>> task = new FutureTask(new Callable<Queue<Integer[]>>(){
            @Override
            public Queue<Integer[]> call() throws Exception {
                Queue<Integer[]> q = dealArr(arr, uni, 0, culSize - 1, 0, (rowSize - 1)/2);
                return q;
            }
        });
        FutureTask<Queue<Integer[]>> task2 = new FutureTask(new Callable<Queue<Integer[]>>(){
            @Override
            public Queue<Integer[]> call() throws Exception {
                Queue<Integer[]> q = dealArr(arr, uni, 0, culSize - 1, (rowSize - 1)/2 + 1, rowSize - 1);
                return q;
            }
        });

        Thread t = new Thread(task);
        Thread t2 = new Thread(task2);
        t.start();
        t2.start();

        q1 = task.get();
        q2 = task.get();

        while (!q1.isEmpty() && !q2.isEmpty()) {
            Integer[] poll = q1.peek();
            Integer[] poll2 = q2.peek();
            if (poll[0] < poll2[0]) {
                q1.poll();
            } else if (poll[0] < poll2[0]){
                q2.poll();
            } else {
                if (Math.abs(poll[1] - poll2[1]) <= 1) {
                    uni.union(poll[0], poll[1], poll2[0], poll2[1]);
                    q1.poll();
                    q2.poll();
                } else if (poll[1] < poll2[1]) {
                    q1.poll();
                } else if (poll[1] < poll2[1]){
                    q2.poll();
                }
            }
        }

        return uni.getSize();
    }

    public static Queue<Integer[]> dealArr(char[][] arr, UnionForLand uni, int culStart, int culEnd, int rowStart, int rowEnd){
        if (rowStart > rowEnd) {
            return new PriorityQueue<>();
        }
        Queue<Integer[]> q1 = new PriorityQueue(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });
        for (int i = culStart + 1; i <= culEnd; i++) {
            if (arr[culStart][i] == '1' && arr[culStart][i - 1] == '1') {
                uni.union(culStart, i, culStart, i - 1);
            }
        }
        for (int i = rowStart + 1; i <= rowEnd; i++) {
            if (arr[i][rowStart] == '1' && arr[i - 1][rowStart] == '1') {
                uni.union(i, rowStart, i - 1, rowStart);
            }
        }
        for (int i = rowStart + 1; i <= rowEnd; i++) {
            for (int j = culStart + 1; j <= culEnd; j++) {
                if (arr[i][j] == '1' && arr[i - 1][j] == '1') {
                    uni.union(i, j, i - 1, j);
                }
                if (arr[i][j] == '1' && arr[i][j - 1] == '1') {
                    uni.union(i, j, i, j - 1);
                }
            }
        }
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = culStart; j <= culEnd; j++) {
                if (i == rowStart || i == rowEnd || j == culStart || j == culEnd) {
                    if (arr[i][j] == '1') {
                        q1.add(new Integer[]{i, j});
                    }
                }
            }
        }
        return q1;
    }

}
