/*
 * Copyright (c) 游克海创建于 2020 -6 -22 8:30 :45
 */

package com.ykh.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 普通顺序查找 线性查找
 */
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7,8,1};
        int i = seqSearch(arr, 1);
        System.out.println(i);
        List<Integer> integers = seqSearchArr(arr, 1);
        System.out.println((integers));
    }

    /**
     * 顺序查找 逐一对比 找到相等的就返回下标
     * 此方法是找到一个则直接返回 后面还有不会去查找 找到多个可用一个集合接收
     * @param arr 要查找的数组
     * @param value 要匹配的值
     * @return
     */
    public static int seqSearch(int[] arr,int value){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==value){
                return i;
            }
        }
        return -1;
    }

    /**
     * 线性查找 查找多个匹配的下标
     * @param arr
     * @param value
     * @return
     */
    public static List<Integer> seqSearchArr(int[] arr, int value){
        List<Integer>  indexArr=new ArrayList<>();
        int index=0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==value){
                indexArr.add(i);
                index++;
            }
        }
        return indexArr;
    }
}
