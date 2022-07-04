package com.sclin.class32;

import class32.Code04_AC2.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ACAutomation {

    private Node root;

    public ACAutomation() {
        this.root = new Node();
    }

    public void insert(String str) {
        char[] chars = str.toCharArray();
        Node cur = root;
        for (char c : chars) {
            if (cur.nexts[c - 'a'] == null) {
                cur.nexts[c - 'a'] = new Node();
            }
            cur = cur.nexts[c - 'a'];
        }
        cur.end = str;
    }

    public void build() {
        Node cur = null;
        Node cFail = null;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            for (int i = 0; i < 26; i++) {
                if (cur.nexts[i] != null) {
                    cur.nexts[i].fail = root;
                    cFail = cur.fail;
                    while (cFail != null) {
                        if (cFail.nexts[i] != null) {
                            cur.nexts[i].fail = cFail;
                            break;
                        }
                        cFail = cFail.fail;
                    }
                    queue.add(cur.nexts[i]);
                }
            }
        }
    }

    public List<String> constantWords(String content) {
        List<String> res = new ArrayList<>();
        char[] chars = content.toCharArray();
        Node cur = root;
        Node follow = null;
        int index = 0;
        for (char c : chars) {
            index = c - 'a';
            if (cur.nexts[index] == null && cur != root) {
                cur = cur.fail;
            }
            cur = cur.nexts[index] == null ? root : cur.nexts[index];
            follow = cur;
            while (follow != null) {
                if (follow.endUse) {
                    break;
                }
                if (follow.end != null) {
                    res.add(follow.end);
                    follow.endUse = true;
                }
                follow = follow.fail;
            }
        }
        return res;
    }

}
