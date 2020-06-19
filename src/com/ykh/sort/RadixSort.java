/*
 * Copyright (c) 游克海创建于 2020 -6 -19 8:34 :54
 */

package com.ykh.sort;

import java.util.Arrays;

/**
 * 基数排序 是桶排序的扩展  时间复杂度O(d(n+k))  d为位数大小
 * 注意:如果数组里面有负数 需要特殊处理
 * 负数思路:求绝对值 然后进行反转放入
 * 是使用空间换时间的一个算法  很快 但是需要大量内存 八千万直接内存溢出OOM
 * 步骤:
 * 1.十个桶 依次拿出数组中的最后一位数进行对比 是1就放入第二个桶 是0就放入第一个桶 以此类推
 * 2.将放好的元素依次按顺序再次放入原数组 再将百位数拿出来进行对比放入 数位不够放入第一个桶 0
 * 3.以此类推 拿到最后一位数摆好之后 再赋值给原数组 即可得到有序数组
 */
public class RadixSort {

    public static void main(String[] args) {
//        int[] arr={54,58,6,89,478};
//        int[] arr1={6,8,9,7,4,1,2,5,3};
//        radixSort(arr1);
//        System.out.println(Arrays.toString(arr1));

        //创建八万个数据
        int arr3[] =new int[80000000];
        for(int i=0;i<80000000;i++){
            arr3[i]= (int) (Math.random()*66666666);
        }
        long startTime= System.currentTimeMillis();
        radixSort(arr3);
        long needTime=+System.currentTimeMillis()-startTime;
        System.out.println("基数排序所需时间"+needTime+"ms");//八万数据大概0.017-0.021秒 八十万0.052s-0.062s 八百万大概0.4s-0.5s 比快速排序还快
    }

    /**
     * 基数排序
     * @param arr
     */
    public static  void radixSort(int[] arr){
        //分步骤解析
        //第一轮排序
        //先根据个位数进行处理
        //先定义十个桶  一个二维数组 里面的一个一维数组表示一个桶
        //包含十个一维数组 为了防止桶可以足够大放入所以数据 将每个桶的大小定义为arr.length
        int[][] bucket=new int[10][arr.length];
        //为了记录每个桶中实际的存放数据数量 定义一个数组用来存放
        //bucketNum[0]的值就是第一个桶的数据量大小
        int[] bucketNum=new int[10];
        //开始处理第一轮个位数比较大小
//        for(int j=0;j<arr.length;j++){
//            //取出个位数
//            int digitOfElement=arr[j]%10;
//            //放入对应的桶
//            //bucketNum[digitOfElement]表示计数 也就是在digitOfElement个桶中的第bucketNum[digitOfElement]个位置放入arr[j]这个数据 第一次进来bucketNum[digitOfElement]为0 如果第二次还是这个桶 那么bucketNum[digitOfElement]就是1
//            bucket[digitOfElement][bucketNum[digitOfElement]]=arr[j];
//            //表示计数 每次完成后++ 主要用于保存第digitOfElement个桶中有多少数据 然后用于下面的放值便利使用
//            bucketNum[digitOfElement]++;
//        }
//        int index=0;
//        //遍历桶 并将桶中数据放入原来的数组
//        for(int k=0;k<bucketNum.length;k++){
//            //如果桶中有数据 就放入
//            if(bucketNum[k]!=0){
//                for(int l=0;l<bucketNum[k];l++){
//                    arr[index]=bucket[k][l];
//                    index++;
//                }
//            }
//            bucketNum[k]=0;//重置 用于每一次存放不重复数据
//        }

        //总方法
        //1.先拿到最大数
        int max=arr[0];//假设第一个数最大
        for(int j=0;j<arr.length;j++){
            if(arr[j]>max){
                max=arr[j];
            }
        }
        //2.拿到位数
        int maxLength=(max+"").length();
        for(int i=0,n=1;i<maxLength;i++,n*=10){//依次扩大位数 个-》十-》百
            for(int j=0;j<arr.length;j++){
                //依次取出对应的位数 第一次个位 第二次十位等
                int digitOfElement=arr[j]/n  % 10;
                //放入对应的桶
                //bucketNum[digitOfElement]表示计数 也就是在digitOfElement个桶中的第bucketNum[digitOfElement]个位置放入arr[j]这个数据 第一次进来bucketNum[digitOfElement]为0 如果第二次还是这个桶 那么bucketNum[digitOfElement]就是1
                bucket[digitOfElement][bucketNum[digitOfElement]]=arr[j];
                //表示计数 每次完成后++ 主要用于保存第digitOfElement个桶中有多少数据 然后用于下面的放值便利使用
                bucketNum[digitOfElement]++;
            }
            int index=0;
            //遍历桶 并将桶中数据放入原来的数组
            for(int k=0;k<bucketNum.length;k++){
                //如果桶中有数据 就放入
                if(bucketNum[k]!=0){
                    for(int l=0;l<bucketNum[k];l++){
                        arr[index]=bucket[k][l];
                        index++;
                    }
                }
                bucketNum[k]=0;//重置 用于每一次存放不重复数据
            }
        }
    }
}
