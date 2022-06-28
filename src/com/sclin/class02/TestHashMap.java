package com.sclin.class02;

import java.util.HashMap;

public class TestHashMap {

    public static void main(String[] args) {
        HashMap map = new HashMap();

        SingleNode node1 = new SingleNode(1);
        SingleNode node2 = new SingleNode(1);

        map.put(node1, 1);

        System.out.println(map.containsKey(node1));
        System.out.println(map.containsKey(node2));
        System.out.println(node1.equals(node2));

    }

}
