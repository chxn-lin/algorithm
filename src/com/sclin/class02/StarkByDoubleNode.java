package com.sclin.class02;

public class StarkByDoubleNode {

    DoubleNode dNode = null;

    public StarkByDoubleNode(){
    }

    public int pop() throws Exception {
        if (dNode.nextNode == null) {
            throw new Exception("无数据，不允许弹出了");
        }
        int retValue = dNode.value;
        dNode = dNode.nextNode;
        dNode.lastNode = null;

        return retValue;
    }

    public StarkByDoubleNode push(int value){
        // 这边没有写完
        return this;
    }

}
