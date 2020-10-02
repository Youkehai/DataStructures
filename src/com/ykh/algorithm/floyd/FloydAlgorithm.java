/*
 * Copyright (c) 游克海创建于 2020 -9 -29 8:45 :58
 */

package com.ykh.algorithm.floyd;

import java.util.Arrays;

/**
 * 弗洛伊德算法求最短路径
 * 弗洛伊德算法思想:将所有顶点到每个顶点的最短路径全部拿到
 * 具体做法:
 * 1.从0开始将每个顶点作为前驱顶点也就是中点 去比较每一个顶点的距离 小的话则更新前驱顶点表和最小距离表
 * 2.三层for进行遍历 第一层为中点 第二层为出发顶点 第三次为终点
 */
public class FloydAlgorithm {

    public static void main(String[] args) {
        char[] data=new char[]{'A','B','C','D','E','F','G'};
        int[][] matrix=new int[data.length][data.length];
        final int N=65535;
        matrix[0]=new int[]{0,5,7,N,N,N,2};
        matrix[1]=new int[]{5,0,N,9,N,N,3};
        matrix[2]=new int[]{7,N,0,N,8,N,N};
        matrix[3]=new int[]{N,9,N,0,N,4,N};
        matrix[4]=new int[]{N,N,8,N,0,5,4};
        matrix[5]=new int[]{N,N,N,4,5,0,6};
        matrix[6]=new int[]{2,3,N,N,4,6,0};
        //初始化
        Graph graph=new Graph(data.length,matrix,data);
        graph.floyd();
        graph.show();
    }
}

class Graph{
    private char[] vertex;//顶点数组
    private int[][] dis;//存储每个顶点互相之间的最短路径
    private int[][] pre;//保存各个顶点到目标顶点的前驱

    /**
     *
     * @param length 长度
     * @param matrix 初始矩阵长度
     * @param vertex 顶点数组
     */
    public Graph( int length, int[][] matrix,char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //对pre进行初始化 存放的值为前驱顶点的下标
        for (int i = 0; i < length; i++) {
            //初始化为下标为多少那么你的前驱顶点下标就为多少 即初始化自己的前驱顶点下标为自己本身
            Arrays.fill(pre[i],i);
        }
    }

    /**
     * 显示pre 和dis
     */
    public  void show(){
        char[] data=new char[]{'A','B','C','D','E','F','G'};
        for (int k = 0; k < dis.length; k++) {
            //先显示Pre
            for (int i = 0; i < dis.length; i++) {
                System.out.print(data[pre[k][i]]+"    ");
            }
            System.out.println();
            for (int i = 0; i < dis.length; i++) {
                System.out.print("("+data[k]+"到"+data[i]+"的最短路径为"+dis[k][i]+"）    ");
            }
            System.out.println();
        }
    }

    /**
     * 弗洛伊德算法
     */
    public void floyd(){
        int len=0;//保存距离
        //从中间顶点进行遍历
        for (int k = 0; k < dis.length; k++) {
            //从i开始作为出发顶点去计算
            for (int i = 0; i < dis.length; i++) {
                //j为终点的下标 即以i为起点 到每个以j为终点的距离
                for (int j = 0; j < dis.length; j++) {
                    //相当于是求出从i顶点出发经过K作为中间顶点 到达j这个顶点的距离
                    len=dis[i][k]+dis[k][j];
                    if(len<dis[i][j]){//如果len小于i j之间的直连距离 那么更新dis表和前驱顶点表
                        dis[i][j]=len;
                        pre[i][j]=pre[k][j];
                    }
                }
            }
        }
    }
}
