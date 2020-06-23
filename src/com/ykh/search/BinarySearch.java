/*
 * Copyright (c) 游克海创建于 2020 -6 -23 8:0 :1
 */

package com.ykh.search;

import java.util.ArrayList;
import java.util.List;

/***
 * 二分查找
 * 使用二分法前提：数组必须有序
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr={1,2,22,22,22,45,67,899,43523,2341341,333132};
        int i = binarySearch(arr, 0, arr.length, 123);
        List<Integer> integers = binarySearchArray(arr, 0, arr.length, 22);
        System.out.println(i);
        System.out.println(integers);
    }

    /***
     * 二分查找
     * @param arr 被查找的数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 要查找的值
     * @return
     */
    public static int binarySearch(int[] arr,int left,int right,int findVal){
        //当left大于right时 说明已经查找完毕
        //但是没有找到值
        if(left>right){
            return  -1;
        }
        int mid=(left+right)/2;
        //拿到中间值
        int midVal=arr[mid];
        if(findVal>midVal){ //被查找的值大于中间值 那么按从小到大的顺序 向右递归
            return binarySearch(arr,mid+1,right,findVal);
        }else if(findVal<midVal){//被查找的值小于中间值 那么按从小到大的顺序 向左递归
            return binarySearch(arr,left,mid-1,findVal);
        }else{
            return mid;
        }
    }
    /***
     * 二分查找 查找多个值 返回一个数组
     * 找到索引值的时候 不要立刻返回
     * 继续向左扫描 将所有值等于findVal的值得下标加入一个集合
     * 同理 向右扫描 相等的下标拿出放入集合
     * @param arr 被查找的数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 要查找的值
     * @return
     */
    public static List<Integer> binarySearchArray(int[] arr, int left, int right, int findVal){
        //当left大于right时 说明已经查找完毕
        //但是没有找到值
        if(left>right){
            return  new ArrayList<>();
        }
        int mid=(left+right)/2;
        //拿到中间值
        int midVal=arr[mid];
        if(findVal>midVal){ //被查找的值大于中间值 那么按从小到大的顺序 向右递归
            return binarySearchArray(arr,mid+1,right,findVal);
        }else if(findVal<midVal){//被查找的值小于中间值 那么按从小到大的顺序 向左递归
            return binarySearchArray(arr,left,mid-1,findVal);
        }else{
            List<Integer>  indexList=new ArrayList<>();
            int temp=mid-1;
            while (true){//向左边扫描
                if(temp<0 || arr[temp]!=findVal){ //说明没找到或者找到了第一个了左边扫描完了 可以退出
                        break;
                }
                //否则 就将temp放入indexList
                indexList.add(temp);
                temp--;
            }
            indexList.add(mid);
            temp=mid+1;
            while (true){//向左边扫描
                if(temp>arr.length-1 || arr[temp]!=findVal){ //说明扫描到了最右边了 可以退出
                    break;
                }
                //否则 就将temp放入indexList
                indexList.add(temp);
                temp++;
            }
            return indexList;
        }
    }


}
