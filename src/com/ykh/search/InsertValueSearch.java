/*
 * Copyright (c) 游克海创建于 2020 -6 -29 8:35 :0
 */

package com.ykh.search;

/**
 * 插值查找算法 通过公式
 * int mid=left+(right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
 * 上述公式举例：
 * 查找1-100中的100
 * 公式则变为: int mid=0+(99-0)*(100-1)/(100-1)=0+99*99/99=99    99则为100的下标
 * 特点：数据要是连续性比较高的话 使用插值查找算法比二分查找要快 如果连续性很差 二分查找和插值查找就差别不会很大
 */
public class InsertValueSearch {
    public static int count=0;
    public static void main(String[] args) {
        int[] arr=new int[100];
       for(int i=0;i<100;i++){
           arr[i]=i+1;
       }
        int[] arr1={1,2,22,22,22,45,67,899,43523,2341341,333132};
        //int i = insertValueSearch(arr, 0, arr.length - 1, 84);
        int i = insertValueSearch(arr1, 0, arr1.length - 1, 899);
        System.out.println(i);
    }

    /**
     * 要求数组是有序的
     * @param arr 数组对象
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 要查找的值
     * @return
     */
    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        count++;
        System.out.println("方法进来了"+count+"次");
        //表示没找到 返回-1  小于最小的 大于最大的 直接返回-1
        //这两个findVal<arr[0] || findVal>arr[arr.length-1] 为必要条件 一是为了优化 二是为了不越界 万一findval很大 那么arr[mid]很可能越界
        if(left>right || findVal<arr[0] || findVal>arr[arr.length-1]){
            return -1;
        }
        //通过这个公式 可快速定位要查找的元素所在的位置 自适应公式
        int mid=left+(right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
        int midVal=arr[mid];
        if(findVal>midVal){//说明应该向右查找
            return  insertValueSearch(arr,mid+1,right,findVal);
        }else if(findVal<midVal){//说明向左查找
            return  insertValueSearch(arr,left,mid-1,findVal);
        }else{
            return mid;
        }
    }
}
