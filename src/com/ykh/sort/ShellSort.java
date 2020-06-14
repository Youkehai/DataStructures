/*
 * Copyright (c) 游克海创建于 2020 -6 -14 8:54 :14
 */

package com.ykh.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 1.即将需要排序的数组或集合按一个数量进行分组 例如100条数据 按2分组分成50组
 * 2.每次对分组里面的数据进行插入排序
 * 3.然后逐次减少分组 到最后只有一个分组时完成最后排序
 * 好处：减少交换位置 先将数据大致排好 到时候需要交换时可以尽量避免出现最小的还在最后的这种情况
 */
public class ShellSort {
    public static void main(String[] args) {
         //int arr[]={8,9,1,7,2,3,5,4,6,0};
//        shellSort(arr);
//        System.out.println(Arrays.toString(arr));
//        int arr3[] =new int[80000];
//        for(int i=0;i<80000;i++){
//            arr3[i]= (int) (Math.random()*66666);
//        }
//        long startTime= System.currentTimeMillis();
//        shellSort(arr3);
//        long needTime=+System.currentTimeMillis()-startTime;
//        System.out.println("交换希尔排序所需时间"+needTime+"ms");//五万条数据我的电脑耗费了2.2秒左右 八万数据大概6秒左右

        int arr4[] =new int[80000];
        for(int i=0;i<80000;i++){
            arr4[i]= (int) (Math.random()*66666);
        }
        long startTime1= System.currentTimeMillis();
        shellSortMove(arr4);
        //shellSortMove(arr);
       // System.out.println(Arrays.toString(arr));
        long needTime1=System.currentTimeMillis()-startTime1;
        System.out.println("移动希尔排序所需时间"+needTime1+"ms");// 八万数据大概0.013-0.020秒左右
    }

    /**
     * 希尔排序法 交换法
     * @param arr 需要排序的数组
     */
    public static void shellSort(int[] arr){
        //希尔排序 第一次排序
        //第一轮排序是将是个数据分成了五组
//        int temp=0;
//        for(int i=5,len=arr.length;i<len;i++){
//            //为了遍历五组数据中的所有元素 每组元素长度一样 所以当i第一次进来时 i=5 j=0
//            for(int j=i-5;j>=0;j-=5){
//            //如果当前元素 大于你遍历的分组了的数组里面的这个元素 那么进行交换
//                if(arr[j]>arr[j+5]){
//                    temp=arr[j];
//                    arr[j]=arr[j+5];
//                    arr[j+5]=temp;
//                }
//            }
//        }
        //第一次处理过后
       // System.out.println("第一轮希尔排序后:"+ Arrays.toString(arr));

        //完整完善代码
        int temp=0;
        for(int grap=arr.length/2;grap>0;grap/=2){//每次循环将数组分组 逐步减少分组
            for(int i=grap,len=arr.length;i<len;i++){
                //为了遍历arr.length/2(即grap)组数据中的所有元素 每组元素长度一样 所以当i第一次进来时 i=5 j=0
                for(int j=i-grap;j>=0;j-=grap){
                    //如果当前元素 大于你遍历的分组了的数组里面的这个元素 那么进行交换
                    if(arr[j]>arr[j+grap]){
                        temp=arr[j];
                        arr[j]=arr[j+grap];
                        arr[j+grap]=temp;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序法 移动法  缩小增量排序
     * @param arr 需要排序的数组
     */
    public static void shellSortMove(int[] arr){
        int temp=0;
        int j=0;
        for(int grap=arr.length/2;grap>0;grap/=2){//每次循环将数组分组
            //从第grap个元素开始 逐个对所在的组进行直接插入排序
            for(int i=grap,len=arr.length;i<len;i++){
                j=i;
                temp=arr[j]; //待插入数据
                if(arr[j]<arr[j-grap]){
                    while (j-grap>=0 && temp<arr[j-grap]){//插入排序判断逻辑
                        //移动
                        arr[j]=arr[j-grap];
                        j-=grap;//没找到插入的位置 进行移位
                    }
                    //退出while后 找到插入位置
                    arr[j]=temp;
                }
            }
        }
    }
}
