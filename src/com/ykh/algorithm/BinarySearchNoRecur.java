/*
 * Copyright (c) 游克海创建于 2020 -9 -13 4:28 :30
 */

package com.ykh.algorithm;


/**
 * 二分查找算法
 * 非递归实现
 */
public class BinarySearchNoRecur {

    public static void main(String[] args) {
        int[] arr=new int[101];
        for(int i=0;i<=100;i++){
            arr[i]=i;
        }
        int search = binarySearch(arr, 1000);
        System.out.println(search);
    }

    /***
     *
     * @param arr 需要查找的数组
     * @param target 需要查找的数据
     * @return 对应下标 -1 为没找到
     */
    public  static int binarySearch(int[] arr,int target){
         int left=0;
         //默认arr为从小到大 升序
         int right =arr.length-1;
         while (left<=right){
             int mid=(left+right)/2;
             if(arr[mid]==target){
                 return mid;
             }else if(arr[mid]>target){
                 //往左边查找
                 right=mid-1;
             }else{
                 //往右边查找
                 left=mid+1;
             }
         }
         return -1;
    }
}
