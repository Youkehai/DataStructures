/*
 * Copyright (c) 游克海创建于 2020 -9 -23 8:4 :9
 */

package com.ykh.algorithm.prim;

import com.ykh.graph.Graph;

import java.util.Arrays;

/***
 * 普利姆最小生成树算法
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data=new char[]{'A','B','C','D','E','F','G'};
        int verx=data.length;
        //邻接矩阵使用二维数组  使用一万连接是因为互相不连通 所以使用10000来表示 这就是ABCDEFG每个顶点互相连接的方式
        int[][] weight=new int[][]{{10000,5,7,10000,10000,10000,2},{5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},{10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},{10000,10000,10000,4,5,10000,6},{2,3,10000,10000,4,6,10000},};
        MGraph mGraph = new MGraph(verx);
        //创建一个MinTree
        MinTree minTree = new MinTree();
        minTree.createGrap(mGraph,verx,data,weight);
        minTree.showGrap(mGraph);

        minTree.prim(mGraph,1);
    }
}

/**
 * 最小生成树
 */
class MinTree{
    //创建图的邻接矩阵

    /***
     *
     * @param mGraph 图对象
     * @param verx 图对应的顶点个数
     * @param data 图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public  void createGrap(MGraph mGraph,int verx,char[] data,int[][] weight){
        int i,j;
        for (i = 0; i < verx; i++) {
            mGraph.data[i]=data[i];
            for (j = 0; j < verx; j++) {
                mGraph.weight[i][j]=weight[i][j];
            }
        }
    }

    /**
     * 显示图的邻接举证
     * @param mGraph 图元素
     */
    public void showGrap(MGraph mGraph){
        for (int[] ints : mGraph.weight) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     *
     * @param mGraph 图元素
     * @param v 表示从图的第几个顶点开始生成
     */
    public void prim(MGraph mGraph,int v){
        //代表某个节点已被访问
        int[] visited = new int[mGraph.verx];
        //visited默认都是0 表示没有访问过
        //标记当前节点为已访问
        visited[v]=1;
        //用h1和h2记录两个顶点的下标
        int h1=-1;
        int h2=-1;
        int minWeight=10000;//初始化两个顶点之间的权值 初始为最大 只要两个顶点之间的权值比10000小 就替换
        //有mGraph.verx个-1条边 所以从1开始
        for (int k = 1; k < mGraph.verx; k++) {
            //确定每一次生成的子图和哪个结点的距离最近、
            //所以下面两层for 就是拿邻接矩阵里面的每一个值去对比minWeight
            for (int i = 0; i < mGraph.verx; i++) { //i表示访问过的节点
                //拿访问过的节点去和每一个没被访问过的节点的权值去和最小权值对比 如果比最小权值小 那么久直接替换
                for (int j = 0; j < mGraph.verx; j++) { //j表示没被访问过的节点
                    //拿到已访问过得节点和每一个未访问过得节点的权值和minWeight进行对比 如果比他小 那么就替换minWeight的值 拿到这个最小路径
                    if(visited[i]==1 && visited[j]==0 && mGraph.weight[i][j]<minWeight){
                        //替换minWeight
                        minWeight=mGraph.weight[i][j];
                        h1=i;h2=j;
                    }
                }
            }
            System.out.println("边<"+mGraph.data[h1]+","+mGraph.data[h2]+">权值"+minWeight);
            visited[h2]=1;//标记为已访问 因为你在上面将j的值赋值给h2 而j的值是未访问过的节点 那么需要将j对应的顶点设置成已访问状态
            //重新设置minWeight再遍历
            minWeight=10000;
        }
    }
}

class MGraph{
    int verx;//表示图的节点数据
    char[] data;//存放节点数据
    int[][] weight;//邻接矩阵

    public MGraph(int verxs) {//初始化节点构造器
        this.verx = verxs;
        this.data=new char[verxs];
        this.weight=new int[verxs][verxs];
    }
}