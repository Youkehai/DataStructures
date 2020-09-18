/*
 * Copyright (c) 游克海创建于 2020 -9 -16 9:2 :8
 */

package com.ykh.algorithm.kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        int i = violenceMatch("我草你妈的傻逼", "傻逼");
        System.out.println(i);
    }

    /**
     * 暴力匹配字符串
     * @param str1 被匹配的字符串
     * @param str2 需要匹配的字符串
     * @return 返回第一次出现的位置
     */
    public  static int violenceMatch(String str1,String str2){
        char[] s1=str1.toCharArray();
        char[] s2=str2.toCharArray();

        int s1Len=s1.length;
        int s2Len=s2.length;

        int i=0; //s1索引
        int j=0; //s2索引

        while(i<s1Len && j<s2Len){//保证下标不越界
            if(s1[i]==s2[j]){
                i++;j++;
            }else{
                //匹配失败 继续将i回到第一次匹配成功的下一位
                i=i-(j-1);
                j=0;//s2从头开始继续匹配
            }
        }
        if(j==s2Len){//表示匹配成功
            return i-j;
        }
        return -1;
    }
}
