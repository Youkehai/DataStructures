/*
 * Copyright (c) 游克海创建于 2020 -6 -16 8:21 :26
 */

package com.ykh.sort;

import java.util.Arrays;

/**
 * 快速排序
 *思路：
 * 1.在集合中取一个中间值 把集合按照中间值进行分组
 * 2.将比这个中间值小的放在左边 大的放在右边
 * 3.然后再进行递归 左右继续按上述方式进行排序 取中间值分组
 * 4.递归完成 集合有序
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr={1,21,11,11111,-89,3,8};
//        queicSort(arr,0,arr.length-1);
//        System.out.println(Arrays.toString(arr));
        int arr4[] =new int[8000000];
        for(int i=0;i<8000000;i++){
            arr4[i]= (int) (Math.random()*6666666);
        }
        long startTime1= System.currentTimeMillis();
        queicSort(arr4,0,arr4.length-1);
        // System.out.println(Arrays.toString(arr));
        long needTime1=System.currentTimeMillis()-startTime1;
        System.out.println("快速排序所需时间"+needTime1+"ms");// 八万数据大概0.086秒左右  八十万大概0.22-0.25秒 八百万大概2.1-2.3秒
    }

    /**
     *
     * @param arr 待排序集合
     * @param left 左索引
     * @param right 右索引
     */
    public static void queicSort(int[] arr,int left,int right){
        int l=left;
        int r=right;
        int pivot=arr[(left+right)/2]; //取中间值 按这个中间值进行分组
        //是为了让比pivot小的放到左边 比pivot大的放在右边
        int temp=0;
        while (l<r){

            while (arr[l]<pivot){//往pivot左边一直找  找到大于等于pivot的值 退出
                l+=1;
            }
            while (arr[r]>pivot){//往pivot右边一直找  找到小于等于pivot的值 退出
                r-=1;
            }
            //如果l>=r 表示pivot左右两侧值已经按顺序分好 即左边比pivot小 右边比pivot大
            if(l>=r){
                break;
            }
            temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;//将左右互换
            //如果arr[l]==pivot 前移r
            if(arr[l]==pivot){
                r-=1;
            }
            if(arr[r]==pivot){
                l+=1;
            }
        }
        //如果l==r 必须l++  r--
        if(l==r){
            l+=1;
            r-=1;
        }
        //向左递归
        if(left<r){
            queicSort(arr,left,r);
        }
        //向右递归
        if(right>l){
            queicSort(arr,l,right);
        }
    }
}
