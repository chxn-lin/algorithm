package com.sclin.class32;

public class IndexTree2D {

    private int rowSize;
    private int colSize;
    private int[][] sum;

    public IndexTree2D(int[][] arr) {
        rowSize = arr.length;
        colSize = arr[0].length;
        sum = new int[rowSize + 1][colSize + 1];
    }

    public int sum(int row, int col){
        int res = 0;
        while (row > 0) {
            while (col > 0){
                res += sum[row][col];
                col -= col & -col;
            }
            row -= row & -row;
        }
        return res;
    }

    public void add(int row, int col, int val){
        while (row > 0) {
            while (col > 0){
                sum[row][col] += val;
                col += col & -col;
            }
            row += row & -row;
        }
    }

}
