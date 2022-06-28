package com.sclin.class12;

import class15.Code01_FriendCircles;

public class FriendCyclic {

    public static void main(String[] args) {
//        int[][] arr = {
//                {1,0,0,1},
//                {0,1,1,0},
//                {0,1,1,1},
//                {1,0,1,1}
//        };
//        System.out.println(new FriendCyclic().findCircleNum(arr));
    }

    public int findCircleNum(int[][] isConnected) {
//        int ret = getSize3(isConnected);
        int ret = 0;
        return ret;
    }

    public static int getSize2(int[][] arr){
        UnionUp up = new UnionUp(arr);
        return up.landSize;
    }



}
