package com.sclin.class12;

// 加强版并查集，int版本
public class UnionForLand {

    int[] parent;// 对应下标的值为父亲的下标
    int[] size;// 对应下标
    int[] help;// 辅助数组

    int landSize;// 代表节点个数
    int rowSize;// 每行有多少元素
    int culSize;// 每行有多少元素

    public UnionForLand(char[][] arr){
        if (arr == null) {
            rowSize = 0;
            culSize = 0;
        } else {
            rowSize = arr.length;
            culSize = arr[0].length;
        }
        landSize = culSize * rowSize;
        parent = new int[landSize];
        // 初始化父亲节点
        size = new int[landSize];
        help = new int[landSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < culSize; j++) {
                if (arr[i][j] == '1') {
                    int index = findIndex(i, j);
                    parent[index] = index;
                    size[index] = 1;
                } else {
                    landSize--;
                }
            }
        }
    }

    // 合并
    public void union(int a, int a2, int b, int b2){
        int indexA = findFatherIndex(findIndex(a, a2));
        int indexB = findFatherIndex(findIndex(b, b2));
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
    private int findIndex(int r, int c){
        return r * culSize + c;
    }

}
