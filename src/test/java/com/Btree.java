package com;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ye
 * Date: 13-5-30
 * Time: 下午10:22
 * B-tree:外存经典索引系列
 */
public class Btree {
    static int degree=5;
    class Record{
        int key;
    }
    class Node{
        int n;//关键字个数
        List<Record> rec=new ArrayList<Record>(2*degree-1);//完全仿照硬盘体系，一上来就刷满整个页块
        List<Node> child=new ArrayList<Node>(2*degree);
        Node parent;
        boolean leaf;
    }
    class SearchResult{
        Node n;
        int i;
        SearchResult(Node n, int i) {
            this.n = n;
            this.i = i;
        }
    }
    SearchResult search(Node x,int k){
        int i=0;
        for(;i<x.n;i++){
            if(x.rec.get(i).key>=k){
                break;
            }
        }
        if(x.rec.get(i).key==k)
            return new SearchResult(x,i);
        if(x.leaf)
            return null;
        return  search(x.child.get(i),k);
    }

    void create(Node x){
        x.leaf=true;
    }

    void split(Node x,int full){
        Node z=new Node();
        Node y=x.child.get(full);
        int i=degree+1,j=0;
        for(;i<2*degree;degree++,j++){
            z.rec.set(j,y.rec.get(i));
            z.child.set(j,y.child.get(i));
        }
        z.child.set(j,y.child.get(i));
        z.n=y.n=degree-1;
        z.leaf=y.leaf;
        //------------backup ok,let's move Node x--------------
        int k=x.n;
        for(;k>full-1;k--){
            x.rec.set(k+1,x.rec.get(k));
            x.child.set(k+2,x.child.get(k+1));
        }
        x.rec.set(k,y.rec.get(degree-1));
        x.child.set(k+1,z);
        x.n++;
    }
}
