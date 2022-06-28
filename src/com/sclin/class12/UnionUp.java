package com.sclin.class12;

// 加强版并查集，int版本
public class UnionUp {

    int[] parent;// 对应下标的值为父亲的下标
    int[] size;// 对应下标
    int[] help;// 辅助数组

    int landSize;// 代表节点个数
    int rowSize;// 每行有多少元素

    public UnionUp(int[][] arr){
        if (arr == null) {
            rowSize = 0;
        } else {
            rowSize = arr.length;
        }
        landSize = rowSize;
        parent = new int[rowSize];
        // 初始化父亲节点
        size = new int[rowSize];
        for (int i = 0; i < rowSize; i++) {
            int index = findIndex(i);
            parent[index] = index;
            size[index] = 1;
        }
        help = new int[rowSize];
        // 初始化最左边的，和第一行
        for (int i = 0; i < rowSize; i++) {
            for (int j = i + 1; j < rowSize; j++) {
                if (arr[i][j] == 1) {
                    union(i, j);
                }
            }
        }
    }

    // 合并
    private void union(int a, int b){
        int indexA = findFatherIndex(a);
        int indexB = findFatherIndex(b);
        if (indexA != indexB) {
            int sizeA = size[indexA];
            int sizeB = size[indexB];
            if (sizeA < sizeB) {
                int temp = indexA;
                indexA = indexB;
                indexB = temp;
            }
            size[indexB] = -1;
            landSize--;
            size[indexA] = sizeA + sizeB;
            parent[indexB] = indexA;
        }
    }

    public int getSize(){
        return landSize;
    }

    private int findFatherIndex(int ind){
        int index = 0;
        while (ind != parent[ind]) {
            help[index++] = ind;
            ind = parent[ind];
        }
        for (int i = 0; i < index; i++) {
            parent[help[i]] = ind;
        }
        return ind;
    }

    // r 行，c 列
    private int findIndex(int r){
        return r;
    }

}
