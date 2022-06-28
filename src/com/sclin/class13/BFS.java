package com.sclin.class13;

import class16.Node;

import java.util.*;

public class BFS {

    // 从node出发，进行宽度优先遍历
    public static void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while(!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.print(poll.value + " ");
            for (Node nd : poll.nexts) {
                if (!set.contains(nd)) {
                    queue.add(nd);
                    set.add(nd);
                }
            }
        }
    }

}
