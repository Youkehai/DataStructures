package com.ykh.sort;

import java.util.Arrays;
/**
 * 选择排序
 * 时间复杂度:o(n²)
 * 总结：依次拿一个数字和后面的所有数字进行对比 找到每一次循环的最小值放到前面
 */
public class SelectSort {

    public static void main(String[] args) {
//        int arr[]={101,46,78,96,-1,56,99};
//        selectSort(arr);
//        System.out.println(Arrays.toString(arr));
        //测试冒泡排序速度 50000数据进行排序
        //创建五万个数据
        int arr3[] =new int[50000];
        for(int i=0;i<50000;i++){
            arr3[i]= (int) (Math.random()*66666);
        }
        long startTime= System.currentTimeMillis();
        selectSort(arr3);
        long needTime=+System.currentTimeMillis()-startTime;
        System.out.println("选择排序所需时间"+needTime+"ms");//五万条数据我的电脑耗费了1.3秒左右
    }

    /**
     * 选择排序
     * @param arr 数组参数
     */
    public static void selectSort(int [] arr){
        //先简单 再复杂
        //先假定一个数是最小的数 这里指定第一层循环的这个数为最小 然后去和它后面的所有数对比
        // 第二层循环中进行对比 如果不是最小 就把比它小的数放到前面 然后再重置最小的下标和值
        for(int i=0,len=arr.length-1;i<len;i++){
            int minIndex=i;//最小的数的索引
            int min=arr[i];//假定最小的是第一个
            //在拿后面所有的去和arr[0]对比
            for(int j=i+1,len1=arr.length;j<len1;j++){
                if(min>arr[j]){//说明假定的值不是最小值 那么交换位置
                    //重置Min和minIndex
                    min=arr[j];
                    minIndex=j;
                }
            }
            //交换最小值位置 将arr[minIndex]=arr[0]
            arr[minIndex]=arr[i];
            arr[i]=min;
        }


    }
}
