package com;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ye
 * Date: 13-4-17
 * Time: 上午9:02
 * To change this template use File | Settings | File Templates.
 */
public class TrieTree {
    class Node {
        public Node(){
        }
        public Node(char c,Node parent){
            this.parent=parent;
            this.c=c;
        }
        char c;
        Node parent;
        Map<Character, Node> child=new HashMap<Character, Node>();
        int payload;
        Node getChild(char c,boolean create){
            if(child.get(c)==null&&create){
                child.put(c,new Node(c,this));
            }
            payload++;
            return child.get(c);
        }
    }
    Node root=new Node();
    public void insert(String word){
        Node cur=root;
        for(int i=0;i<word.length();i++){
            cur=cur.getChild(word.charAt(i),true);
        }
    }
    public List<Node> associateWord(StringBuilder det){
        Node cur=root;
        int i=0;
        for(;i<det.length();i++){
            cur=cur.getChild(det.charAt(i),false);
            if(cur==null){
                return new ArrayList<Node>();
            }
        }
        print(cur.c+","+cur.child.size());
        return collectLeaf(cur,new ArrayList<Node>());
    }
    void print(String str){
        System.out.println(str);
    }
    public List<Node> collectLeaf(Node n,List<Node> list){
        Node cur=n;
        if(cur!=null) {
            for (char c:cur.child.keySet()){
                print("char:"+c);
                Node oneChild=cur.child.get(c);
                if(oneChild==null||oneChild.child.size()==0){
                    list.add(oneChild);
                }else {
                    collectLeaf(oneChild, list);
                }
            }
        }
        return list;
    }
    StringBuilder getFullString(Node n){
        StringBuilder sb=new StringBuilder();
        while (n.parent!=null){
            sb.append(n.c);
            n=n.parent;
        }
        return sb.reverse();
    }
    @Test
    public void test(){
        insert("amqjava");
        insert("amqb");
        insert("amqc");
        insert("amqd");
        List<Node> list=associateWord(new StringBuilder("amp"));
        print("list.size():"+list.size());
        for (Node e:list){
            print("fullString:"+getFullString(e));
        }
    }
}
