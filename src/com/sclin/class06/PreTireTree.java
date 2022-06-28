package com.sclin.class06;

import class08.Code02_TrieTree;

import static class08.Code01_TrieTree.generateRandomStringArray;

public class PreTireTree {

    private Node node;

    private PreTireTree(){
        node = new Node();
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);

            Code02_TrieTree.Trie2 trie1 = new Code02_TrieTree.Trie2();
            PreTireTree trie2 = new PreTireTree();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    if (ans1 != ans2) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    if (ans1 != ans2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }

    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return ;
        }
        char[] wordArr = word.toCharArray();
        Node curNode = node;
        for (char c : wordArr) {
            if (curNode.arr[c - 'a'] == null) {
                // 表示没有生成过路径，那么新生成
                curNode.arr[c - 'a'] = new Node();
            }
            curNode.pass++;
            curNode = curNode.arr[c - 'a'];
        }
        curNode.pass++;
        curNode.end++;
    }

    public void delete(String word) {
        int count = search(word);
        if (count == 0) {
            return;
        }
        char[] arr = word.toCharArray();
        Node curNode = node;
        int path = 0;
        for (char c : arr) {
            path = c - 'a';
            if (--curNode.pass == 0) {
                curNode.arr[path] = null;
                return;
            }
            curNode = curNode.arr[path];
        }
        curNode.pass--;
        curNode.end--;
    }

    public int search(String word) {
        assert word != null;
        char[] arr = word.toCharArray();
        Node curNode = node;
        for (char c : arr) {
            curNode = curNode.arr[c - 'a'];
            if (curNode == null) {
                return 0;
            }
        }
        return curNode.end;
    }

    // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
    public int prefixNumber(String pre) {
        assert pre != null;
        char[] arr = pre.toCharArray();
        Node curNode = node;
        for (char c : arr) {
            curNode = curNode.arr[c - 'a'];
            if (curNode == null) {
                return 0;
            }
        }
        return curNode.pass;
    }

    private static class Node{
        int pass;
        int end;
        Node[] arr;
        public Node(){
            pass = 0;
            end = 0;
            arr = new Node[26];
        }
    }

}
