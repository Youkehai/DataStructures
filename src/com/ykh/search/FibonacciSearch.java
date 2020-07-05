/*
 * Copyright (c) 游克海创建于 2020 -7 -2 8:11 :24
 */

package com.ykh.search;

import java.util.Arrays;

/**
 * 主要是通过斐波那契数列 找到一个黄金分割点
 * 通过下面的fib方法找到你要查找的数组的长度 如果需要用斐波那契算法查找 那么至少需要多长
 * 需要有序数组 并且需要对原数组进行改造才能进行斐波那契查找
 * 斐波那契查找算法 黄金分割法 0.618
 */
public class FibonacciSearch {
    public static  int maxSize=20;
    public static void main(String[] args) {
        int[] arr={1,8,79,4555,6666};
        System.out.println(fibSearch(arr,8));
    }

    //因为公式需要mid=low+F(k-1) 所以先获取到一个斐波那契数列

    /**
     * 得到一个斐波那契数列
     * @return
     */
    public static  int[] fib(){
        int[] f=new int[maxSize];
        f[0]=1;
        f[1]=1;
        for(int i=2;i<maxSize;i++){
            f[i]=f[i-1]+f[i-2];//斐波那契数组原理
        }
        return f;
    }

    /**
     *  斐波那契查找法
     * @param arr 数组对象
     * @param key 需要查询的值
     * @return 对应下标
     */
     public  static int fibSearch(int[] arr,int key){
        int low=0;
        int high=arr.length-1;
        int k=0;//表示斐波那契数列分割数值的下标
         int mid=0;
         int f[]=fib();//获取一个斐波那契数列 从这个斐波那契数列中拿到一个值
         //获取到斐波那契数列分割数值的下标
         //即 如果数组长度为6 那么需要一个新的数组长度为八的数组 才能用黄金分割法进行查找 因为需要满足斐波那契的那个表达式 即：f[k]=f[k-1]+f[k-2] 所以需要从拿到的斐波那契的数组中
         //获得一个值 拿到新的数组长度 在下面进行复制 才能使用斐波那契查找
         while(high>f[k]-1){
             k++;
         }
         //因为f[k]可能大于arr数组长度 因此需要使用Arrays,构造一个新数组 指向arr 不足部分会用0填充
         int[] temp= Arrays.copyOf(arr,f[k]);
         //将arr最后一个值的数据放到temp的后面 替换掉填充的0
         for(int i=high+1;i<temp.length;i++){
                temp[i]=arr[high];
         }
         //循环处理 找到key
         while(low<=high){
             mid=low+f[k-1]-1;//找到中间下标
             if(key<temp[mid]){//说明应该向数组的左边查找
                 high=mid-1;
                 //下次循环mid=f[k-1-1]-1
                 k--;
             }else if(key>temp[mid]){//向右边查找
                  low=mid+1;
                  //mid=f[k-1-2]-1
                  k-=2;
             }else{//找到
                 //需要确定返回的是哪个下标
                 if(mid<high){
                     return mid;
                 }else{
                     return high;
                 }
             }
         }
         return -1;
     }
}
