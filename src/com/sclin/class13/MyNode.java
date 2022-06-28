package com.sclin.class13;

import java.util.ArrayList;

// 点结构
public class MyNode {

    public int value;
    public int in;
    public int out;
    public ArrayList<MyNode> nexts;
    public ArrayList<MyEdge> edges;

    public MyNode(int value, int in, int out, ArrayList<MyNode> nexts, ArrayList<MyEdge> edges) {
        this.value = value;
        this.in = in;
        this.out = out;
        this.nexts = nexts;
        this.edges = edges;
    }
}
