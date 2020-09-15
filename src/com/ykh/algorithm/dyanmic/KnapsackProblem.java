/*
 * Copyright (c) 游克海创建于 2020 -9 -15 8:15 :58
 */

package com.ykh.algorithm.dyanmic;

import java.util.logging.Level;

/**
 * 动态规划算法解决背包问题
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        int[] w={1,4,3,10};//保存物品重量
        int[] val={1500,3000,2000,10000};//物品价值
        int m=14;//背包的容量
        int n=val.length;//物品的个数

        //创建二维数组 表示前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v=new int[n+1][m+1]; //行表示物品 列表示背包容量
        int[][] path=new int[n+1][m+1]; //存放哪行哪列的商品被存放
        //将第一行第一列设置0
        for (int i = 0; i < v.length; i++) {
            v[i][0]=0;//第一列设置为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i]=0;//第一行设置为0
        }
        //从1开始为跳过第一行第一列
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if(w[i-1]>j){//这个判断是要放入的物品的重量大于背包剩余重量 则表示放不进去
                    //直接将上一行的同一列的这个单元格的值填入进来
                    v[i][j]=v[i-1][j];
                }else{//当前放入的物品的重量小于当前背包的容量 则表示可以放进去
                    //拿到上一个单元格的值 和当前物品的价值加上上一行重量小于剩余重量 即j为当前背包容量 w[i]为当前物品重量 拿剩余空间再去比对
                    //v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    //为了记录是哪些商品被存放 需要增加判断
                    if(v[i-1][j]< (val[i-1]+v[i-1][j-w[i-1]])){
                        v[i][j]=val[i-1]+v[i-1][j-w[i-1]];
                        //记录当前物品情况
                        path[i][j]=1;
                    }else{
                        v[i][j]=v[i-1][j];
                    }
                }
            }
        }
        //查看最大值
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j]+"  ");
            }
            System.out.println();
        }

        int i=path.length-1;
        int j=path[0].length-1;
        while (i>0 && j>0){//从后往前遍历 因为最后的才是最优解
            if(path[i][j]==1){
                System.out.println("第"+i+"个物品放入背包");
                j-=w[i-1];//这是因为我已经放入了w[i-1]的物品 然后我的背包容量肯定要减去该容量
            }
            i--;
        }
    }
}
