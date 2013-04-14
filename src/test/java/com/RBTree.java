package com;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: ye
 * Date: 13-4-10
 * Time: 下午11:11
 * To change this template use File | Settings | File Templates.
 */
public class RBTree {
    Node realRoot;
    class Node{
        public Node(int value,int color){
            this.value=value;
            this.color=color;
        }
        int color;
        Node parent,left,right;
        int value;

        @Override
        public String toString() {
            return value+"";    //To change body of overridden methods use File | Settings | File Templates.
        }
    }


    boolean insertRB(Node root, Node e){
        if(root.value== e.value){
            return false;
        }else if(root.value>e.value){
            if(root.left==null){
                e.parent=root;
                root.left=e;
                adjust4RB_insert(e);
                return true;
            }else{
                insertRB(root.left, e);
            }
        }else {
            if(root.right==null){
                e.parent=root;
                root.right=e;
                adjust4RB_insert(e);
                return true;
            }else{
                insertRB(root.right, e);
            }
        }

        return false;
    }

    void deleteRB(Node tar){
        Node suc=successor4RB(tar,true);
        tar.value=suc.value;
        Node child=child4Suc(suc);
        if(suc.color==0){//删了个红节点，没影响
            print("condition waster");
            if(leftOrRight(suc)){
                suc.parent.left=child;
            }else {
                suc.parent.right=child;
            }
            child.parent=suc.parent;
            if(child.value==Integer.MIN_VALUE){//剪掉nil
                suc.parent.left=null;
                suc.parent.right=null;
            }
            return;
        }else if(suc.color==1&&child.color==0){//腹黑子红
            print("腹黑子红");
            if(leftOrRight(suc)){
                suc.parent.left=child;
            }else {
                suc.parent.right=child;
            }
            child.parent=suc.parent;
            child.color=1;
            return;
        }else{//两黑,可能包括nil
            int nil=child.value;
            Node sucCopy=child;
            if(leftOrRight(suc)){
                suc.parent.left=child;
            }else {
                suc.parent.right=child;
            }
            child.parent=suc.parent;
            deleteRB0(child);
            if(nil==Integer.MIN_VALUE){//剪掉nil
                if(leftOrRight(sucCopy)){
                    sucCopy.parent.left=null;
                }else {
                    sucCopy.parent.right=null;
                }
            }
        }

    }

    /**
     *     tar
     *         bar
     *
     *       bar
     *   tar
     * @param tar
     */
    void rotataLeft(Node tar){
        Node bar=tar.right;
        tar.right=bar.left;
        if(bar.left!=null){
            bar.left.parent=tar;
        }

        Node root=tar.parent;
        bar.parent=root;
        if(bar.parent!=null){
            hiSon(root,tar,bar);
        }

        if(root==null){
            realRoot=bar;
        }
        bar.left=tar;
        tar.parent=bar;
    }

    /**
     *      tar
     *   bar
     *
     *      bar
     *          tar
     * @param tar
     */
    void rotataRight(Node tar){
        Node bar=tar.left;
        tar.left=bar.right;
        if(bar.right!=null){
            bar.right.parent=tar;
        }

        Node root=tar.parent;
        bar.parent=root;
        if(bar.parent!=null){
            hiSon(root,tar,bar);
        }
        if(root==null){
            realRoot=bar;
        }

        bar.right=tar;
        tar.parent=bar;
    }

    void rotata(Node tar,boolean flag){
        if(flag){
            rotataLeft(tar);
        }else{
            rotataRight(tar);
        }
    }

    void hiSon(Node parent,Node oldSon,Node newSon){
       if(oldSon==parent.left){
            parent.left=newSon;
       }else{
            parent.right=newSon;
       }
    }


    void printBST(Node root){
        if(root.left!=null){
            printBST(root.left);
        }
        System.out.println(root.value+","+root.color);
        if(root.right!=null){
            printBST(root.right);
        }
    }

    void adjust4RB_insert(Node e){
//        print("adjust for one node:"+e.value);
        if(e.parent==null){//插了就插了
//            print("i'm root");
            e.color=1;
            return;
        }else if(e.parent.color==1){
//            print("parent is black");
            return;
        }else if(e.parent.color==0&&(sibling(e.parent)!=null&&(sibling(e.parent)!=null&&sibling(e.parent).color==0))){//爷爷肯定黑
//            print("condition 3");
            e.parent.color=sibling(e.parent).color=1;
            e.parent.parent.color=0;
            adjust4RB_insert(e.parent.parent);
            return;
        }else if(e.parent.color==0&&(sibling(e.parent)==null||sibling(e.parent).color==1)){
            if(leftOrRight(e)^leftOrRight(e.parent)){//情况4,一左一右
//                print("condition 4");
                rotata(e.parent,!leftOrRight(e));
                case_closed4(e);
            }else {
//                print("condition 5");
                case_closed5(e);
            }
            return;
        }
        print("bad case!!!!!!");
    }
    boolean leftOrRight(Node e){
        if(e.parent.left==e){
            return true;
        }else {
            return false;
        }
    }
    void case_closed4(Node e){
        boolean k=leftOrRight(e);
        rotata(e.parent,!leftOrRight(e));
        e.color=1;
        if(k){
            e.right.color=0;
        }else {
            e.left.color=0;
        }
    }
    void case_closed5(Node e){
        rotata(e.parent.parent,!leftOrRight(e));
        e.parent.color=1;
        sibling(e).color=0;
    }

    Node sibling(Node e){
        if(e.parent==null){
            return null;
        }else{
           return sibling0(e);
        }
    }
    Node sibling0(Node e){
        if(e.parent.left==e){
            return e.parent.right;
        }else{
            return e.parent.left;
        }
    }
    void print(String str){
        System.out.println(str);
    }

    /**
     * 广度优先
     * @param e
     * @return
     */
    StringBuilder printStructure(Node e,StringBuilder sb){
//        StringBuilder sb=new StringBuilder();
        if (e.left!=null){
            sb.append(e.left.value+":"+e.left.color+",");
        }else {
            sb.append("empty");
        }
        if (e.right!=null){
            sb.append(e.right.value+":"+e.right.color+",");
        }else {
            sb.append("empty");
        }
        return sb;
    }

    void printSon(Node e){
        StringBuilder sb=new StringBuilder();
        if (e.left!=null){
            sb.append(e.left.value+":"+e.left.color+",");
        }else {
            sb.append("empty");
        }
        if (e.right!=null){
            sb.append(e.right.value+":"+e.right.color+",");
        }else {
            sb.append("empty");
        }
        print(sb.toString());
    }


    /**
     *    t
     * a       b
     *       c    d
     *      E f
     * ---------------------------
     *
     *     c
     *   b
     *  a t
     *
     * @param tar
     * @param prefer true优先返回前任，false后继,仅仅是为了测试需要
     * @return  返回tar的前任or后继节点
     */
    Node successor4RB(Node tar,boolean prefer){

        Node tmp;
        if(prefer){
            if(tar.left!=null){//前任
                tmp=tar.left;
                while (tmp.right!=null){
                    tmp= tmp.right;
                }
                return tmp;
            }
            if(tar.right!=null){//后继
                tmp=tar.right;
                while (tmp.left!=null){
                    tmp=tmp.left;
                }
                return tmp;
            }
        }else {
            if(tar.right!=null){//后继
                tmp=tar.right;
                while (tmp.left!=null){
                    tmp=tmp.left;
                }
                return tmp;
            }
            if(tar.left!=null){//前任
                tmp=tar.left;
                while (tmp.right!=null){
                    tmp= tmp.right;
                }
                return tmp;
            }
        }



        return tar;
    }



    Node child4Suc(Node n){
        if(n.left!=null){
            return n.left;
        }else if(n.right!=null){
            return n.right;
        }else{
            return new Node(Integer.MIN_VALUE,1);//nil节点,用完后剪掉
        }

    }
    void deleteRB0(Node e){
        if(sibling(e).color==0){//case2
            print("case 2");
            rotata(e.parent,leftOrRight(e));
            e.parent.color=0;
            e.parent.parent.color=1;
            case2_closed(e);
        }else{
            Node copyLeft=null,copyRight=null;
            copyLeft=addNil(e,true);
            copyRight=addNil(e,false);
            if(sibling(e).left.color==1&&sibling(e).right.color==1){
                if(e.parent.color==0){ //case 4 case_closed
                    print("case 4 case_closed");
                    e.parent.color=1;
                    sibling(e).color=0;
                }else {//case3
                    print("case3");
                    sibling(e).color=0;
                    deleteRB(e.parent);
                }
            }else{
                if((leftOrRight(e)&&sibling(e).right.color==1&&sibling(e).left.color==0)||
                        (!leftOrRight(e)&&sibling(e).left.color==1&&sibling(e).right.color==0)){//case 5
                    print("case 5 case_closed");
                    int color=e.parent.color;
                    rotata(sibling(e),!leftOrRight(e));
                    rotata(e.parent,leftOrRight(e));
                    e.parent.color=1;
                    e.parent.parent.color=color;
                }else if((leftOrRight(e)&&sibling(e).right.color==0)||
                        (!leftOrRight(e)&&sibling(e).left.color==0)){//case 6 case_closed
                    print("case 6 case_closed");
                    int color=e.parent.color;
                    rotata(e.parent,leftOrRight(e));
                    e.parent.color=1;
                    e.parent.parent.color=color;
                }
            }

            dropNil(copyLeft);
            dropNil(copyRight);
        }
    }
    void case2_closed(Node e){
        print("case2_closed");
        Node copyLeft=addNil(e,true);
        Node copyRight=addNil(e,false);

        if(sibling(e).left.color==1&&sibling(e).right.color==1){
            e.parent.color=1;
            sibling(e).color=0;
        }else if((leftOrRight(e)&&sibling(e).right.color==1&&sibling(e).left.color==0)||
                (!leftOrRight(e)&&sibling(e).left.color==1&&sibling(e).right.color==0)){
            rotata(sibling(e),!leftOrRight(e));
            rotata(e.parent,leftOrRight(e));
            e.parent.color=1;
            e.parent.parent.color=0;
        }else if((leftOrRight(e)&&sibling(e).right.color==0)||
                (!leftOrRight(e)&&sibling(e).left.color==0)){//case 6 case_closed
            rotata(e.parent,leftOrRight(e));
            e.parent.color=1;
            e.parent.parent.color=0;
        }

        dropNil(copyLeft);
        dropNil(copyRight);
    }
    Node addNil(Node e,boolean left){//为e的兄弟增加nil节点
        Node dest=left?sibling(e).left:sibling(e).right;
        if(dest==null){
            Node copy=new Node(Integer.MIN_VALUE,1);
            copy.parent=sibling(e);
            if(left){
                sibling(e).left=copy;
            }else {
                sibling(e).right=copy;
            }
            return copy;
        }
        return dest;
    }
    void dropNil(Node n){//删除nil节点
        if(n.value==Integer.MIN_VALUE){
            if(leftOrRight(n)){
                n.parent.left=null;
            }else {
                n.parent.right=null;
            }
        }
    }
    @Test
    public void testInsert(){
        int[] arr={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,10};
        Node bst=new Node(arr[0],1);
        realRoot=bst;
        for (int e:arr){
            insertRB(realRoot, new Node(e, 0));
        }
        print("realRoot： " + realRoot.value + "," + realRoot.color + ",arr.length:" + arr.length);
        printBST(realRoot);
    }

    @Test
    public void test_delete_case2(){//注意切换方法successor4RB第二个参数为true！！！
        int[] arr={3,2,6,1,4,7,5,8};
        Node bst=new Node(arr[0],1);
        realRoot=bst;
        for (int e:arr){
            insertRB(realRoot, new Node(e, 0));
        }
        realRoot.left.left.color=1;
        realRoot.right.left.right.color=1;
        realRoot.right.right.right.color=1;
        print("realRoot： " + realRoot.value + "," + realRoot.color + ",arr.length:" + arr.length);
        printBST(realRoot);
        print("---------------");
        deleteRB(realRoot);
        print("realRoot： " + realRoot.value + "," + realRoot.color + ",arr.length:" + arr.length);
        printBST(realRoot);
    }

    @Test
    public void test_delete_case3(){//注意切换方法successor4RB第二个参数为true！！！
        int[] arr={3,2,5,1,4,6};
        Node bst=new Node(arr[0],1);
        realRoot=bst;
        for (int e:arr){
            insertRB(realRoot, new Node(e, 0));
        }
        realRoot.left.left.color=1;
        realRoot.right.left.color=1;
        realRoot.right.right.color=1;
        print("realRoot： " + realRoot.value + "," + realRoot.color + ",arr.length:" + arr.length);
        printBST(realRoot);
        print("---------------");
        deleteRB(realRoot);
        print("realRoot： " + realRoot.value + "," + realRoot.color + ",arr.length:" + arr.length);
        printBST(realRoot);
    }

    @Test
    public void test_delete_case4(){//跟case2共用一个条件，注意切换方法successor4RB第二个参数为false！！！
        int[] arr={3,2,6,1,4,7,5,8};
        Node bst=new Node(arr[0],1);
        realRoot=bst;
        for (int e:arr){
            insertRB(realRoot, new Node(e, 0));
        }
        realRoot.left.left.color=1;
        realRoot.right.left.right.color=1;
        realRoot.right.right.right.color=1;
        print("realRoot： " + realRoot.value + "," + realRoot.color + ",arr.length:" + arr.length);
        printBST(realRoot);
        print("---------------");
        deleteRB(realRoot);
        print("realRoot： " + realRoot.value + "," + realRoot.color + ",arr.length:" + arr.length);
        printBST(realRoot);
    }

    @Test
    public void test_delete_case5(){//注意切换方法successor4RB第二个参数为true！！！
        int[] arr={2,1,4,3};
        Node bst=new Node(arr[0],1);
        realRoot=bst;
        for (int e:arr){
            insertRB(realRoot, new Node(e, 0));
        }
        print("realRoot： " + realRoot.value + "," + realRoot.color + ",arr.length:" + arr.length);
        printBST(realRoot);
        print("---------------");
        deleteRB(realRoot);
        print("realRoot： " + realRoot.value + "," + realRoot.color + ",arr.length:" + arr.length);
        printBST(realRoot);
    }
    @Test
    public void test_delete_case6(){//注意切换方法successor4RB第二个参数为true！！！
        int[] arr={2,1,3,4};
        Node bst=new Node(arr[0],1);
        realRoot=bst;
        for (int e:arr){
            insertRB(realRoot, new Node(e, 0));
        }
        print("realRoot： " + realRoot.value + "," + realRoot.color + ",arr.length:" + arr.length);
        printBST(realRoot);
        print("---------------");
        deleteRB(realRoot);
        print("realRoot： " + realRoot.value + "," + realRoot.color + ",arr.length:" + arr.length);
        printBST(realRoot);
    }

}
