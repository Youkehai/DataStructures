package com.ykh.recursion;

public class RecursionTest {

    public static void main(String[] args) {
    //最简单递归测试
        test(5);
    }
     public static void test(int i){
        if(i>2){
            test(i-1);
        }
         System.out.println("i的值:"+i);
     }
}
