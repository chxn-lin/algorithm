package com.sclin.class31;

import class31.Code01_SegmentTree;

import static class31.Code01_SegmentTree.genarateRandomArray;

// 线段树
public class SegmentTree {

    private int size;
    private int[] arr;
    private int[] sum;
    private int[] lazy;
    private boolean[] hasChange;
    private int[] change;

    public SegmentTree(int[] oriArr) {
        size = oriArr.length + 1;
        arr = new int[size << 2];
        for (int i = 1; i < size; i++) {
            arr[i] = oriArr[i - 1];
        }
        sum = new int[size << 2];
        lazy = new int[size << 2];
        hasChange = new boolean[size << 2];
        change = new int[size << 2];

        build(1, 1, size - 1);
    }

    private void pushUp(int rt) {
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    private void pushDown(int rt, int lSize, int rSize) {
        if (hasChange[rt]) {
            lazy[rt << 1] = 0;
            lazy[rt << 1 | 1] = 0;
            sum[rt << 1] = lSize * change[rt];
            sum[rt << 1 | 1] = rSize * change[rt];
            hasChange[rt << 1] = true;
            hasChange[rt << 1 | 1] = true;
            change[rt << 1] = change[rt];
            change[rt << 1 | 1] = change[rt];
            hasChange[rt] = false;
        }
        if (lazy[rt] != 0) {
            lazy[rt << 1] += lazy[rt];
            lazy[rt << 1 | 1] += lazy[rt];
            sum[rt << 1] += lSize * lazy[rt];
            sum[rt << 1 | 1] += rSize * lazy[rt];
            lazy[rt] = 0;
        }
    }

    private void build(int rt, int l, int r) {
        if (l == r) {
            sum[rt] = arr[rt];
            return;
        }
        int mid = (l + r) >> 1;
        build(rt << 1, l, mid);
        build(rt << 1 | 1, mid + 1, r);
        pushUp(rt);
    }

    public void add(int L, int R, int C) {
        add(L, R, C, 1, 1, size - 1);
    }

    private void add(int L, int R, int C, int rt, int l, int r) {
        if (L <= l && r <= R) {
            lazy[rt] += C;
            sum[rt] += (r - l + 1) * C;
            return;
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            add(L, R, C, rt << 1, l, mid);
        }
        if (R >= mid + 1) {
            add(L, R, C, rt << 1 | 1, mid + 1, r);
        }
        pushUp(rt);
    }

    public void update(int L, int R, int C) {
        update(L, R, C, 1, 1, size - 1);
    }

    private void update(int L, int R, int C, int rt, int l, int r) {
        if (L <= l && r <= R) {
            lazy[rt] = 0;
            sum[rt] = (r - l + 1) * C;
            hasChange[rt] = true;
            change[rt] = C;
            return;
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            update(L, R, C, rt << 1, l, mid);
        }
        if (R >= mid + 1) {
            update(L, R, C, rt << 1 | 1, mid + 1, r);
        }
        pushUp(rt);
    }

    public int query(int L, int R) {
        return query(L, R, 1, 1, size - 1);
    }

    private int query(int L, int R, int rt, int l, int r) {
        if (L <= l && r <= R) {
            return sum[rt];
        }
        int res = 0;
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            res += query(L, R, rt << 1, l, mid);
        }
        if (R >= mid + 1) {
            res += query(L, R, rt << 1 | 1, mid + 1, r);
        }

        return res;
    }



    // =============================以下为测试========================================

    public static void main(String[] args) {
        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);
            SegmentTree seg = new SegmentTree(origin);
            int N = origin.length;
            Code01_SegmentTree.Right rig = new Code01_SegmentTree.Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(L, R, C);
                    rig.add(L, R, C);
                } else {
                    seg.update(L, R, C);
                    rig.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

}
