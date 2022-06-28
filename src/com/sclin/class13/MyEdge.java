package com.sclin.class13;

// 边结构
public class MyEdge {

    public MyNode from;
    public MyNode to;
    public int value;

    public MyEdge(MyNode from, MyNode to, int value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }
}
