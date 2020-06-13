
/*
 * Copyright (c) 游克海创建于 2020 -6 -13 8:44 :35
 */

package com.ykh.sort;

import java.util.Arrays;
/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int [] arr={4878,78,4,598,133};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序
     * @param arr 数组参数
     */
    public static void insertSort(int[] arr){
        //先将数组分成无序和有序
        //1.直接将第一个作为有序数组 然后从后面的无序数组数据中取出一个进行对比
        //2.找到拿出来的无序数组的数据要放入有序数组的下标位置 然后进行交换位置排序
        /*
        //推导过程：如下
        //第一轮应该得到一个{4878,78,4,598,133} =》{78,4878,4,598,133};
        //先定义一个待插入的数
        int insertNum=arr[1]; //78
        //待插入数的索引 即insertNum前面这个数的下标
        int insertIndex=1-1; //0
        //给insertNum 找到要插入的位置
        //insertIndex>=0 是为了保证给insertNum找插入位置 不越界
        //insertNum<arr[insertIndex] 这个是为了待插入的数还没找到插入位置 则需要再继续循环
        //则需要将arr[insertIndex]后移
        while(insertIndex>=0 &&insertNum<arr[insertIndex]){ //0>=0 并且 78<4878 所以进入此循环
            arr[insertIndex+1]=arr[insertIndex]; //arr[1]=arr[0] 即arr[1]=4878
            insertIndex--; //-1 不满足条件 退出循环
        }
        //当退出循环时 说明找到插入位置  位置为insertIndex+1的位置
        arr[insertIndex+1]=insertNum; //arr[0]=78 完成第一轮交换
         */

        for(int i=1,len=arr.length;i<len;i++){
            //先定义一个待插入的数
            int insertNum=arr[i]; //78
            //待插入数的索引 即insertNum前面这个数的下标
            int insertIndex=i-1; //0
            //给insertNum 找到要插入的位置
            //insertIndex>=0 是为了保证给insertNum找插入位置 不越界
            //insertNum<arr[insertIndex] 这个是为了待插入的数还没找到插入位置 则需要再继续循环
            //则需要将arr[insertIndex]后移
            while(insertIndex>=0 &&insertNum<arr[insertIndex]){
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }
            //当退出循环时 说明找到插入位置  位置为insertIndex+1的位置
            arr[insertIndex+1]=insertNum;
        }
    }
}
