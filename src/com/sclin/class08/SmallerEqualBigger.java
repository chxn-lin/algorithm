package com.sclin.class08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmallerEqualBigger {

    /**
     * 将单向链表按某值划为小，中间相等，右边大的形式
     * 实现方式：1、将链表放在数组里，在数组上做partition
     * 2、分为小，中，大三部分，再把各个部分串起来
     */

    public static void main(String[] args) {
        Node head = new Node(4);
        head.next = new Node(1);
        head.next.next = new Node(3);
        head.next.next.next = new Node(7);
        head.next.next.next.next = new Node(2);
        head.next.next.next.next.next = new Node(9);

        int value = 0;
        head = method1(head, value);
        System.out.println(head);
    }

    public static Node method1(Node head, int value){
        Node cur = head;
        int i = 0;
        while(cur != null){
            i++;
            cur = cur.next;
        }
        cur = head;
        Node[] arr = new Node[i];
        i = 0;
        while (cur != null) {
            arr[i++] = cur;
            cur = cur.next;
        }
        partation(arr, value);
        head = arr[0];
        for (int k = 1; k < arr.length; k++) {
            arr[k - 1].next = arr[k];
        }
        arr[arr.length - 1].next = null;
        return head;
    }

    private static void partation(Node[] arr, int value) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int L = -1;
        int R = arr.length;
        int index = 0;
        while (index != R) {
            Node node = arr[index];
            if (node.value < value) {
                sw(arr, index++, ++L);
            } else if (node.value == value) {
                index++;
            } else {
                sw(arr, index, --R);
            }
        }
    }

    public static void sw(Node[] arr, int index1, int index2){
        Node temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static Node method2(Node head, int value){
        // 不使用数组排序
        Node sT = null;
        Node sE = null;
        Node eT = null;
        Node eE = null;
        Node bT = null;
        Node bE = null;
        while (head != null) {
            if (head.value < value) {
                if (sT == null) {
                    sT = head;
                    sE = sT;
                } else {
                    sE.next = head;
                    sE = sE.next;
                }
            } else if (head.value == value) {
                if (eT == null) {
                    eT = head;
                    eE = eT;
                } else {
                    eE.next = head;
                    eE = eE.next;
                }
            } else {
                if (bT == null) {
                    bT = head;
                    bE = bT;
                } else {
                    bE.next = head;
                    bE = bE.next;
                }
            }
            head = head.next;
        }
        // 拼接
        head = sT != null ? sT : (eT != null ? eT : bT);
        if (sT != null) {
            sE.next = eT;
            eE = eE == null ? sE : eE;
        }
        if (eE != null) {
            eE.next = bT;
        }
        return head;
    }
}
