package com.ykh.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int arr[]={3,9,-1,10,-2};
//        //排序过程
//        //1.第一趟排序就是将最大的数排在最后
//        int temp=0;//临时变量
//        for(int j=0,len=arr.length-1;j<len;j++){
//            //如果前面的数比后面的数大 则交换
//            if(arr[j]>arr[j+1]){
//                temp=arr[j];
//                arr[j]=arr[j+1];
//                arr[j+1]=temp;
//            }
//        }
//        System.out.println("第一次排序之后");
//        System.out.println(Arrays.toString(arr));
//
//        //第二趟排序 将第二大的数排到倒数第二个
//        for(int j=0,len=arr.length-2;j<len;j++){
//            //如果前面的数比后面的数大 则交换
//            if(arr[j]>arr[j+1]){
//                temp=arr[j];
//                arr[j]=arr[j+1];
//                arr[j+1]=temp;
//            }
//        }
//
//        System.out.println("第二次排序之后");
//        System.out.println(Arrays.toString(arr));
//
//        //第三趟排序 将第三大的数排到倒数第三个
//        for(int j=0,len=arr.length-3;j<len;j++){
//            //如果前面的数比后面的数大 则交换
//            if(arr[j]>arr[j+1]){
//                temp=arr[j];
//                arr[j]=arr[j+1];
//                arr[j+1]=temp;
//            }
//        }
//
//        System.out.println("第三次排序之后");
//        System.out.println(Arrays.toString(arr));
//
//        //第四趟排序 将第四大的数排到倒数第四个
//        for(int j=0,len=arr.length-4;j<len;j++){
//            //如果前面的数比后面的数大 则交换
//            if(arr[j]>arr[j+1]){
//                temp=arr[j];
//                arr[j]=arr[j+1];
//                arr[j+1]=temp;
//            }
//        }
//
//        System.out.println("第四次排序之后");
//        System.out.println(Arrays.toString(arr));
//        //简化代码版双层for
//        int arr1[]={3,9,-1,10,-2};
//        int temp1=0;
//        long starttime= System.currentTimeMillis();
//        for(int i=0,len=arr1.length-1;i<len;i++){
//            for(int j=0,len1=arr1.length-1-i;j<len1;j++){
//                //如果前面的数比后面的数大 则交换
//                if(arr1[j]>arr1[j+1]){
//                    temp1=arr1[j];
//                    arr1[j]=arr1[j+1];
//                    arr1[j+1]=temp1;
//                }
//            }
//        }
//        long needTime=+System.currentTimeMillis()-starttime;
//        System.out.println("双层for所需时间"+needTime);
//        System.out.println("双层for排序之后");
//        System.out.println(Arrays.toString(arr1));

        int arr2[]={3,9,-1,10,-2};
        bubble(arr2);
        System.out.println("双层for优化排序之后");
        System.out.println(Arrays.toString(arr2));

        //测试冒泡排序速度 50000数据进行排序
        //创建八万个数据
        int arr3[] =new int[50000];
        for(int i=0;i<50000;i++){
            arr3[i]= (int) (Math.random()*66666);
        }
        long starttime= System.currentTimeMillis();
        bubble(arr3);
        long needTime=+System.currentTimeMillis()-starttime;
        System.out.println("冒泡排序所需时间"+needTime+"ms");//五万条数据我的电脑耗费了9.5-11秒左右
    }

    /**
     * 冒泡排序
     * @param arr2 x需要排序的数组
     */
    public static void bubble(int[] arr2){
        //简化代码版双层for 增加是否排序标识
        int temp2=0;
        boolean flag=false;//是否进行过排序
        for(int i=0,len=arr2.length-1;i<len;i++){
            for(int j=0,len1=arr2.length-1-i;j<len1;j++){
                //如果前面的数比后面的数大 则交换
                if(arr2[j]>arr2[j+1]){
                    flag=true;
                    temp2=arr2[j];
                    arr2[j]=arr2[j+1];
                    arr2[j+1]=temp2;
                }
            }
            if(!flag){//表示在排序中没有交换过排序 那么直接可以结束排序
                break;
            }else{
                flag=false;//如果排序过 重置flag 进行下次的排序
            }
        }
    }
}
