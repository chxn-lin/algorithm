package com.sclin.class13;

import class16.Code03_TopologicalOrderBFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TopoOrderBFS {

    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    // 提交下面的
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        if (graph == null) {
            return null;
        }
        HashMap<DirectedGraphNode, Integer> map = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            map.put(cur, 0);
        }
        for (DirectedGraphNode cur : graph) {
            for (DirectedGraphNode neighbor : cur.neighbors) {
                int newValue = map.get(neighbor) + 1;
                map.put(neighbor, newValue);
            }
        }
        Queue<DirectedGraphNode> zeroQueue = new LinkedList<>();
        ArrayList<DirectedGraphNode> retList = new ArrayList<>();
        for (DirectedGraphNode directedGraphNode : map.keySet()) {
            if (map.get(directedGraphNode) == 0) {
                zeroQueue.add(directedGraphNode);
            }
        }
        while (!zeroQueue.isEmpty()) {
            DirectedGraphNode poll = zeroQueue.poll();
            retList.add(poll);
            for (DirectedGraphNode neighbor : poll.neighbors) {
                int newValue = map.get(neighbor) - 1;
                map.put(neighbor, newValue);
                if (newValue == 0) {
                    zeroQueue.add(neighbor);
                }
            }
        }
        return retList;
    }

    public static void main(String[] args) {
        ArrayList<DirectedGraphNode> graph = new ArrayList<>();
        DirectedGraphNode a1 = new DirectedGraphNode(1);
        DirectedGraphNode a2 = new DirectedGraphNode(2);
        DirectedGraphNode a3 = new DirectedGraphNode(3);
        DirectedGraphNode a4 = new DirectedGraphNode(4);
        DirectedGraphNode a5 = new DirectedGraphNode(5);
        DirectedGraphNode a6 = new DirectedGraphNode(6);
        DirectedGraphNode a7 = new DirectedGraphNode(7);

        a1.neighbors.add(a2);
        a1.neighbors.add(a3);
        a1.neighbors.add(a4);

        a2.neighbors.add(a6);
        a3.neighbors.add(a5);
        a4.neighbors.add(a5);
        a5.neighbors.add(a7);
        a7.neighbors.add(a6);

        graph.add(a1);
        graph.add(a2);
        graph.add(a3);
        graph.add(a4);
        graph.add(a5);
        graph.add(a6);
        graph.add(a7);

        ArrayList<DirectedGraphNode> directedGraphNodes = topSort(graph);
        directedGraphNodes.forEach(t -> System.out.print(t.label + " , "));
    }

}
