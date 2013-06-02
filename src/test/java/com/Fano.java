package com;

/**
 * Created with IntelliJ IDEA.
 * User: ye
 * Date: 13-6-2
 * Time: 下午1:47
 * To change this template use File | Settings | File Templates.
 */
public class Fano {
    int[] code = {9, 8, 7, 6, 5, 4};

    public int separator(Node tar) {
        return tar.arr.length / 2;
    }

    class Node {
        Node left, right;
        int[] arr;
    }

    public void split(Node tar) {
        int sep = separator(tar);
        Node left = new Node();
        left.arr = new int[sep];
        int i = 0;
        for (; i < sep; i++) {
            left.arr[i] = tar.arr[i];
        }
        Node right = new Node();
        right.arr = new int[tar.arr.length - sep];
        for (; i < tar.arr.length; i++) {
            right.arr[i - sep] = tar.arr[i];
        }
        tar.left = left;
        tar.right = right;
        tar.arr = null;
    }

    public void fano(Node tar) {
        split(tar);
        if (tar.left != null && tar.left.arr.length > 1) {
            split(tar.left);
        }
        if (tar.right != null && tar.right.arr.length > 1) {
            split(tar.right);
        }
    }
}
