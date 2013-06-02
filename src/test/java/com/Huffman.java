package com;

/**
 * Created with IntelliJ IDEA.
 * User: ye
 * Date: 13-6-2
 * Time: 下午3:32
 * 只演示哈弗曼编码的关键步骤，二项堆的操作省略
 */
public class Huffman {
    int n=100;
    class Node{
        Node left,right;
        int key;
    }
    class Forest{
        Node[] trees=new Node[n];
    }

    /**
     * 二项堆的删除、插入操作见svn
     * @param forest
     */
    public void huf(Forest forest){
        for(int i=0;i<n;i++){
            Node left=extractMin(forest);
            Node right=extractMin(forest);
            Node newNode=new Node();
            newNode.left=left;
            newNode.right=right;
            newNode.key= left.key+right.key;
            insert(forest,newNode);
        }
    }

    /**
     * 弹出堆中根节点
     * @param heap
     * @return
     */
    public Node extractMin(Forest heap){
        return null;
    }

    /**
     * 堆中插入新节点
     * @param heap
     * @param key
     */
    public void insert(Forest heap,Node key){

    }
}
