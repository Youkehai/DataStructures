/*
 * Copyright (c) 游克海创建于 2020 -6 -18 8:3 :14
 */

package com.ykh.sort;

import java.util.Arrays;

/***
 * 归并排序
 * 需要一个额外的数组开销 用于临时存储
 * 将一个大数组分成若干小数组 然后再将小数组整合起来
 * 整合完成后就会变成一个有序数组
 */
public class MegetSort {
    public static void main(String[] args) {
//        int[] arr={8,4,5,7,1,3,6,2,0};  //该算法的时间复杂度为o(n logn)
//        int[] temp=new int[arr.length];
//        mergeSort(arr,0,arr.length-1,temp);
//        System.out.println(Arrays.toString(arr));

        int arr4[] =new int[8000000];
        for(int i=0;i<8000000;i++){
            arr4[i]= (int) (Math.random()*6666666);
        }
        int[] temp=new int[arr4.length];
        long startTime1= System.currentTimeMillis();
        mergeSort(arr4,0,arr4.length-1,temp);
        // System.out.println(Arrays.toString(arr));
        long needTime1=System.currentTimeMillis()-startTime1;
        System.out.println("归并排序所需时间"+needTime1+"ms");// 八万数据大概0.013秒左右 八十万大概0.091秒 八百万差不多1秒
    }

    /**
     * 分+合
     * @param arr 待排数组
     * @param left 左边有序序列的初始索引
     * @param right 右边的索引
     * @param temp 存放数据的临时数组
     */
    public static void mergeSort(int[] arr,int left,int right,int [] temp){
        if(left<right){
            int mid=(left+right)/2;//拿到中间的位置
            //向左边递归进行分解
            mergeSort(arr,left,mid,temp);//左边的开始位置为left 结束位置为mid 进行一个一个分解 分解完成后进行合并
            //向右边递归进行分解
            mergeSort(arr,mid+1,right,temp);//右边的开始位置为mid+1 结束位置为right 进行一个一个分解 分解完成后进行合并
            //进行合并
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     *  合并的方法
     * @param arr 待排数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边的索引
     * @param temp 存放数据的临时数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int [] temp){
        int i=left;//初始化i 左边有序序列的起始索引
        int j=mid+1;//初始化J 表示右边有序序列的起始索引
        int t=0; //temp的索引 即往temp放数据时的位置索引

        //1.先将左右两边的数据按规则放入到temp中 直到左右两边有一方处理完成为止
        while (i<=mid && j<=right){//表示还没有到最左边或者最右边
            //如果左边的当前元素小于等于右边的当前元素 那么将左边的当前元素 放入到temp中
            //然后t后移 因为t已经放好数据了 所以t++ i++后移继续对比
            if(arr[i]<=arr[j]){
                temp[t]=arr[i];
                t++;
                i++;
            }else{
                //反之 将右边的数据放到temp中 和上述同理
                temp[t]=arr[j];
                t++;
                j++;
            }
        }
        //2.将有剩余数据的一方的数据依次按原来顺序填充到temp中
        while (i<=mid){ //说明左边的有序序列还剩余 依次拿出放入temp中
            temp[t]=arr[i];
            t++;
            i++;
        }
        while (j<=right){ //说明右边的有序序列还剩余 则依次拿出放入temp中
            temp[t]=arr[j];
            t++;
            j++;
        }
        //3.将temp的数据放到arr中 拿到有序集合
        //并不是每次都赋值所有
        t=0;
        int templeft=left;//每次进来的left都不一样 所以需要定义出来 每次都从传进来的最左边的位置开始赋值
        while (templeft<=right){
            arr[templeft]=temp[t];
            t++;
            templeft++;
        }
    }
}
