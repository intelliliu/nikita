package com;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: ye
 * Date: 13-4-14
 * Time: 下午6:45
 * To change this template use File | Settings | File Templates.
 */
public class KMP {
    public int[] next(String pattern){
        int[] next=new int[pattern.length()];
        next[0]=-1;
        for (int i=1;i<pattern.length();i++){
            int cover=i;
            while(!isCover(pattern.substring(0,i+1),cover)){
                cover--;
            }
            next[i]=cover-1;
        }
        return next;
    }
    public boolean isCover(String pos,int length){
        int i=0;
        for (;i<length;i++){
            if(pos.charAt(i)==pos.charAt(pos.length()-length+i)){
                continue;
            }
            return false;
        }
        return true;
    }

    void println(String str){
        System.out.println(str);
    }

    void print(String str){
        System.out.print(str);
    }

    public int indexOf(String target,String pattern,int[]next){
        int i=0;
        int j=0;
        for(;i<target.length();){
            if(pattern.charAt(j)==target.charAt(i)){
                if(j==pattern.length()-1){
                    return i-pattern.length();
                }else{
                    j++;
                    i++;
                }
            }else {
                if(j==0||next[j-1]==-1){
                    j=0;
                    i++;
                }else{
                    j=next[j-1]+1;
                }
            }
        }
        return -1;
    }

    public void kmp(){

    }
    @Test
    public void test(){
        String target="annbcdanacadsannannacanna";
        String pattern="annacanna";
//        String target="aaabaaac";
//        String pattern="aaac";
        int[]next=next(pattern);
        for (int i=0;i<next.length;i++){
//            println(i+":"+next[i]);
            print(next[i]+",");
        }
        println(indexOf(target,pattern,next)+"");
    }
}
