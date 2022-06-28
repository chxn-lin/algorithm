package com.sclin.class02;

public class DoubleNode {

    int value;
    DoubleNode nextNode;
    DoubleNode lastNode;

    public DoubleNode(int value, DoubleNode nextNode, DoubleNode lastNode) {
        this.value = value;
        this.nextNode = nextNode;
        this.lastNode = lastNode;
    }

    public DoubleNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + " -> " + nextNode;
    }

    public String toString2() {
        return value + " -> " + (lastNode == null ? null : lastNode.toString2());
    }

}
