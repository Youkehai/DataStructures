/*
 * Copyright (c) 游克海创建于 2020 -7 -24 8:5 :6
 */

package com.ykh.tree;

import java.util.Arrays;

/**
 * 时间复杂度:O(nlogn) 线性对数阶
 * 大顶推：升序 从小到大
 * 小顶堆：降序 从大到小
 * 堆排序核心
 * 1:从左边的最后一个非叶子结点的左右开始比 把堆比作一个平衡二叉树
 * 2:左边叶子结点和右边比较 如果大就交换(如果是降序排列则小就交换)
 * 3:依次从左到右继续对比 就能得到一个排列好的顺序
 */
public class HeapSort {
    public static void main(String[] args) {
//        int[] arr={4,6,8,5,9};
//        heapSort(arr);
        int arr4[] =new int[8000000];
        for(int i=0;i<8000000;i++){
            arr4[i]= (int) (Math.random()*66666666);
        }
        long startTime1= System.currentTimeMillis();
        heapSort(arr4);
        // System.out.println(Arrays.toString(arr));
        long needTime1=System.currentTimeMillis()-startTime1;
        System.out.println("堆排序所需时间"+needTime1+"ms");// 八万数据大概0.02秒左右  八十万大概0.22-0.25秒 八百万大概3.2秒
    }

    /**
     * 堆排序
     * @param arr
     */
    public  static void heapSort(int[] arr){

//        adjust(arr,1,arr.length);
//        System.out.println("第一次排序过后"+ Arrays.toString(arr));//{4,9,8,5,6}
//        adjust(arr,0,arr.length);
//        System.out.println("第二次排序后"+Arrays.toString(arr));//{9,6,8,5,4}
        for(int i=arr.length/2-1;i>=0;i--){//拿到最大的数
            adjust(arr,i,arr.length);
        }
        //将最大的数放到数组末端 通过上面的交换 arr[0]肯定是最大值
        int temp=0;
        for(int i=arr.length-1;i>0;i--){
                temp=arr[i];
                arr[i]=arr[0];
                arr[0]=temp;
                //继续交换 每次交换完之后i-- 调用大顶堆方法 然后再进入循环 将第一个元素和末尾i进行换位置 重复交换 直到完成循环 直接变成有序数列
                adjust(arr,0,i);
        }
        //System.out.println("排序后"+Arrays.toString(arr));//{9,6,8,5,4}
    }

    /**
     * 将一个数组 调整成一个大顶堆
     * int[] arr={4,6,8,5,9}
     * 举例：第一个非叶子结点下标i=1 方法完成后为=>{4,9,8,5,6}
     * 第二次调用i=0 方法完成后为=>{9,6,8,5,4}
     * @param arr  需要排序的数组
     * @param i 非叶子结点在数组中的索引
     * @param length 表示对多少个元素进行调整 因为第一次调整完之后，会将最大的移到想要的位置 length会每次排完之后减少
     */
    public  static void adjust(int[] arr,int i,int length){
        //取出当前保存
        int temp=arr[i];
        //k为i的左子节点 下一次遍历则遍历k的左子节点 则k=k*2+1
        for(int k=i*2+1;k<length;k=k*2+1){
            if(k+1<length &&  arr[k]<arr[k+1]){//如果arr[i]的左子节点小于右子节点
                k++;//k指向右子节点
            }
            if(arr[k]>temp){
                //如果左子节点或右子节点大于父节点
                //那么交换子节点和父节点的位置
                arr[i]=arr[k];
                //让i指向k 继续往下找
                i=k;
            }else{
                break;//跳出 没找到大的 因为是从最后一个非叶子结点开始对比的 所以不需要继续往下比较 直接跳出当前循环
            }
        }
        //循环结束后 已经将i这个树中最大的数放在了下标为i的位置 因为已经将i和左右节点进行了对比
        //因为上面i=k 所以arr[i]=temp类似于将temp的值赋值给了arr[k]
        arr[i]=temp;
    }
}
