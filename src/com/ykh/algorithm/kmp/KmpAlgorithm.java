/*
 * Copyright (c) 游克海创建于 2020 -9 -17 9:3 :58
 */

package com.ykh.algorithm.kmp;

import java.util.Arrays;

/**
 * kmp核心思想
 * 使用部分匹配表进行快速定位 而不是像暴力匹配 全部从头再来
 * 而是根据已经匹配过得得到匹配信息 进行快速回溯移动
 */
public class KmpAlgorithm {

    public static void main(String[] args) {

        String str1="ADSADJKASKFJKASJDKLASJD";
        String str2="DJK";
        int kmp = kmp(str1, str2);
        System.out.println(kmp);

    }

    public static int kmp(String str1,String str2){
        int[] next=kmpNex(str2);
       return  kmpSearch(str1,str2,next);
    }
    /**
     *
     * @param str1 源字符串
     * @param str2 需要查找的字符
     * @param next 子串的部分匹配表
     * @return 该字符所在的位置 -1为没找到
     */
    public static int kmpSearch(String str1,String str2,int[] next){
        //遍历第一串
        for(int i=0,j=0,len=str1.length();i<len;i++){
            //当不相等时 通过部分匹配表去调整j的位置 当然也有可能从0开始 但是也可能从1，2,3等开始
            while(j>0 && str1.charAt(i)!=str2.charAt(j)){
                j=next[j-1];
            }
            if(str1.charAt(i)==str2.charAt(j)){//找到相同的字符串 则将长度++
                j++;
            }
            if(j==str2.length()){//表示找到了
                return i-j+1;
            }
        }
        return -1;
    }

    /**
     * 拿到一个字符串的部分匹配表
     * @param str
     * @return
     */
    public static int[] kmpNex(String str){
        //先创建数组
        int[] next=new int[str.length()];
        next[0]=0;//如果字符串长度为1 部分匹配值则肯定为0
        for(int i=1,j=0;i<str.length();i++){
            //当str.charAt(i)！=str.charAt(j) 从next[j-1]获取新的j
            while(j>0 && str.charAt(i)!=str.charAt(j)){
                j=next[j-1];
            }
            //满足这个条件 表示部分匹配值匹配成功
            if(str.charAt(i)==str.charAt(j)){
                j++;
            }
            next[i]=j;
        }
        return next;
    }
}
