package com.sclin.class02;

import java.util.Objects;

public class SingleNode {

    int value;

    SingleNode nextNode;

    public SingleNode(int value, SingleNode nextNode) {
        this.value = value;
        this.nextNode = nextNode;
    }

    public SingleNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + " -> " + nextNode ;
    }

}
