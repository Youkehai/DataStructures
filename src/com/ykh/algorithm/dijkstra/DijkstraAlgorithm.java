/*
 * Copyright (c) 游克海创建于 2020 -9 -27 8:38 :10
 */

package com.ykh.algorithm.dijkstra;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法规划最短路径 使用了图的广度优先算法
 */
public class DijkstraAlgorithm {

    public static void main(String[] args) {
        char[] data=new char[]{'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] matrix=new int[data.length][data.length];
        final int N=65535;
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        Graph graph = new Graph(data, matrix);
        graph.showGraph();
        graph.dijkstra(6);
        graph.showDjs();
    }
}

class Graph{
    private char[] vertex;//顶点数组
    private int[][] matrix;//邻接矩阵
    private VisitedVertex vv;//已经访问过的顶点的集合的边

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    /**
     * 打印矩阵
     */
    public void showGraph(){
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 打印矩阵
     */
    public void showDjs(){
       vv.showResult();
    }

    /**
     * 迪杰特斯拉算法
     * @param index 出发顶点对应的下标
     */
    public void dijkstra(int index){
        vv=new VisitedVertex(vertex.length,index);
        update(index);//更新以index为下标的顶点到周围顶点的距离和前驱顶点的下标
        for (int i = 1; i < vertex.length; i++) {
            index=vv.updateArr();//选择并返回一个新的访问顶点
            update(index);
        }
    }

    /**
     * 更新以index为下标的顶点到周围顶点的距离和周围顶点的前驱顶点
     * @param index
     */
    public void update(int index){
        int len=0;//表示出发顶点到以index为下标的顶点的举例 + 从以index为下标的顶点到i顶点的距离 即通过前驱节点两条路加起来的距离
        //遍历邻接矩阵 matrix[index]
        for (int i = 0; i < matrix[index].length; i++) {
            len=vv.getDis(index)+matrix[index][i];
            //如果i这个顶点没被访问 并且len的权值小于当前顶点到出发顶点的距离 那么就要更新这个路径 找到这个最短路径
            if(!vv.in(i) && len<vv.getDis(i)){
                vv.updatePre(i,index);//更新以i为下标的顶点的前驱顶点
                vv.updateDis(i,len); //更新出发顶点到以i为下标的顶点的距离
            }
        }
    }
}

/**
 * 已访问的顶点的集合
 */
class VisitedVertex{
    public int[] alerady_arr;//记录顶点是否访问过 0为未访问
    public int[] pre_visited;//每个下标对应的值 为该下标对应的顶点的前驱节点 每次会动态去更新
    public int[] dis;//记录从出发顶点到其他顶点的距离 比如以A为出发顶点 就会记录A到每个顶点的距离 每次动态更新 如果动态拿到的值比当前存放的值要小 那么就会替换掉当前的值

    /**
     *
     * @param length 表示顶点个数
     * @param index 表示出发顶点对应的下标 例如A为出发顶点 那么Index=0
     */
    public VisitedVertex(int length,int index) {
        this.alerady_arr =new int[length];
        this.pre_visited =new int[length];
        this.dis =new int[length];
        Arrays.fill(dis,65535);//将dis数组里面所有的值全部设置为最大值65535
        this.alerady_arr[index]=1;//设置以Index为下标的顶点为已访问过 1
        this.dis[index]=0;//在将自己和自己之间的距离设置为0
    }

    /**
     * 判断该下标的顶点 是否被访问过
     * @param index
     * @return true为访问过 false为没被访问过
     */
    public boolean in(int index){
        return this.alerady_arr[index]==1;
    }

    /**
     * 更新出发顶点到以Index为下标的顶点的距离
     * @param index
     * @param len
     */
    public void updateDis(int index,int len){
        dis[index]=len;
    }

    /**
     * 更新以pre为下标的顶点的前驱顶点为 以Index为下标的顶点 只需要index的值就行 通过下标可以找到顶点是哪个
     * @param pre
     * @param index
     */
    public void updatePre(int pre,int index){
        pre_visited[pre]=index;
    }

    /**
     * 拿到出发顶点到以Index为下标的顶点的距离 用于判断是否小于新的值 小于则不用替换 大于则需要替换这个出发顶点到以Index为下标的顶点的距离
     * @param index
     * @return
     */
    public int getDis(int index){
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点 例如G为出发顶点 但是G到不了D 那么就以A以出发顶点
     * @return
     */
    public int updateArr(){
        int min=65535,index=0;
        for (int i = 0; i < alerady_arr.length; i++) {
            //如果该顶点没被访问 并且这个顶点的权值小于Min 那么替换权值
            if(alerady_arr[i]==0 && dis[i]<min){
                min=dis[i];
                index=i;//下一个新的访问顶点为index
            }
        }
        //更新以index为下标的顶点为已访问过
        this.alerady_arr[index]=1;
        return index;
    }

    /**
     * 显示最后结果 即输出这三个集合
     */
    public void showResult(){
        System.out.println("访问节点集合");
        for (int i = 0; i < alerady_arr.length; i++) {
            System.out.print(alerady_arr[i]+"  ");
        }
        System.out.println();
        System.out.println("前驱节点集合");
        for (int i = 0; i < pre_visited.length; i++) {
            System.out.print(pre_visited[i]+"  ");
        }
        System.out.println();
        System.out.println("节点距离集合");
        for (int i = 0; i < dis.length; i++) {
            System.out.print(dis[i]+"  ");
        }
        char[] data=new char[]{'A','B','C','D','E','F','G'};
        int count=0;
        System.out.println();
        for (int i = 0; i < dis.length; i++) {
            if(i!=65535){
                System.out.println("出发节点到"+data[count]+"的距离为("+dis[i]+")");
            }
            count++;
        }
    }
}
