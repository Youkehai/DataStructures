/*
 * Copyright (c) 游克海创建于 2020 -9 -24 8:38 :54
 */

package com.ykh.algorithm.kruskal;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法
 * 通过邻接矩阵拿到所有的边
 * 然后将边根据权值进行从小到大的排序
 * 然后再利用克鲁斯卡尔算法进行规划
 */
public class KruskalCase {

    private int edgeNum;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//图的邻接矩阵
    private static final int INF=Integer.MAX_VALUE;//表示两个顶点不连通
    public static void main(String[] args) {
        char[] vertexs={'A','B','C','D','E','F','G'};
        //0表示自己和自己联通
        int[][] matrix={{0,12,INF,INF,INF,16,14},{12,0,10,INF,INF,7,INF},{INF,10,0,3,5,6,INF},{INF,INF,3,0,4,INF,INF},{INF,INF,5,4,0,2,8},{16,7,6,INF,2,0,9},{14,INF,INF,INF,8,9,0}};
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.showMatrix();
        EData[] edges = kruskalCase.getEdges();
        System.out.println("未排序之前"+Arrays.toString(edges));
        kruskalCase.sortEdges(edges);
        System.out.println("排序之后"+Arrays.toString(edges));
        kruskalCase.kruskal();
    }

    public KruskalCase(char[] vertexs,int[][] matrix){
        //初始化
        int vlen=vertexs.length;
        //初始化顶点
        this.vertexs=new char[vlen];
        this.vertexs=vertexs;
        //初始化边
        this.matrix=new int[vlen][vlen];
        this.matrix=matrix;
        //统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if(this.matrix[i][j]!=INF){//说明这条边是连通的
                    this.edgeNum++;
                }
            }
        }
    }

    public void kruskal(){
        int index=0;//表示最后结果的数组的索引
        //用于保存已有最小生成树中的每个顶点的终点对应的下标
        int[] ends=new int[edgeNum];//用于判断是否生成回路
        //创建结果数组 保存最后的结果
        EData[] result=new EData[edgeNum];
        //获取图中所有的边的集合
        EData[] edges=getEdges();
        //按到边的权值大小进行排序 从小到大进行排序
        sortEdges(edges);
        //遍历边的数组 将边添加到最小生成树中时 判断准备加入的边是否形成了回路 没有就加入result 有的话就不加入
        for (int i = 0; i < edgeNum; i++) {
            //一条边有两个顶点
            //获取到第i条边的第一个顶点
            int p1=getPosition(edges[i].start);
            //获取边的第二个顶点
            int p2=getPosition(edges[i].end);
           //获取p1在当前最小生成树中的终点
            int m=getEnd(ends,p1);
            //获取p2在当前最小生成树中的终点
            int n=getEnd(ends,p2);
            //判断是否构成回路
            if(m!=n){
                ends[m]=n;//记录最小生成树中的终点 如果m=1 n=2 那么就是下标为1的顶点它对应的终点的下标为2
                result[index]=edges[i];
                index++;
            }
        }
        //输出result 拿到最小生成树
        System.out.println("最小生成树："+Arrays.toString(result));
        for (int i = 0; i < index; i++) {
            System.out.println(result[i]);
        }

    }

    /**
     * 展示矩阵
     */
    public void showMatrix(){
        System.out.println("矩阵为");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%15d",this.matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对边的集合进行排序
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges){
        for (int i = 0; i < edges.length-1; i++) {
            for (int j = 0; j < edges.length-1-i; j++) {
                if(edges[j].weight>edges[j+1].weight){
                    EData tmp=edges[j];
                    edges[j]=edges[j+1];
                    edges[j+1]=tmp;
                }
            }
        }
    }



    /***
     *通过顶点的值拿到顶点的下标
     * @param ch 顶点的值
     * @return 返回顶点对应的下标
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if(vertexs[i]==ch){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边 放入EData[] 需要拿到这个边的集合去遍历
     * 通过matrix来获取
     * @return
     */
    private EData[] getEdges(){
        int index=0;
        EData[] eData=new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if(matrix[i][j]!=INF){
                    eData[index]=new EData(vertexs[i],vertexs[j],matrix[i][j]);
                    index++;
                }
            }
        }
        return eData;
    }

    /***
     * 获取下标为i的顶点的终点
     * 用于判断两个顶点的终点是否相同
     * @param ends 记录了各个顶点对应的终点，在遍历过程中逐步形成的 在形成最小路径的过程中 逐步加入逐步形成的
     * @param i 表示调用这个方法的顶点的下标
     * @return 下标为i的顶点对应的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while (ends[i]!=0){
            i=ends[i];
        }
        return i;
    }
}

/**
 * 边的实体类
 */
class EData{
    char start;//起点
    char end;//边的终点
    int weight;//权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
