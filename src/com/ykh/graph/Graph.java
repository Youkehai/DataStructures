/*
 * Copyright (c) 游克海创建于 2020 -8 -31 7:52 :18
 */

package com.ykh.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList;//存储图的顶点list
    private int[][] edges;//图的内容矩阵
    //记录某个顶点是否已经被访问过
    private int numOfEdges;//边的数目

    private boolean[] isVisited;

    public static void main(String[] args) {
        int n=5;
        String[] vertexValue={"A","B","C","D","E"};
        Graph graph = new Graph(n);
        //循环添加顶点
        for (String s : vertexValue) {
            graph.addVettex(s);
        }
        //添加图形的边
        graph.addEdge(0,1,1); //代表A-B的连接
        graph.addEdge(0,2,1);
        graph.addEdge(1,2,1);
        graph.addEdge(1,3,1);
        graph.addEdge(1,4,1);

        graph.showGaraph();

//        System.out.println("深度遍历");
  //      graph.dfs();

        System.out.println("广度遍历");
        graph.bfs();
    }

    /**
     * 图的构造器
     * @param n 顶点个数
     */
    public Graph(int n){
       // 初始化矩阵和list
        edges=new int[n][n];
        vertexList=new ArrayList<>(n);
        numOfEdges=n;
        isVisited=new boolean[n];
    }

    /**
     * 返回图中结点的个数
     * @return
     */
    public int getNumOfVertex(){
        return this.vertexList.size();
    }

    /**
     * 展示图片对应的矩阵
     */
    public void showGaraph(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * 得到某个顶点的第一个邻接节点的下标w
     * @param index
     * @return
     */
    public int getFirstNeigbort(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if(edges[index][i]>0){//矩阵的值大于0 表示有边 有邻接点
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点的下标来获取下一个邻接结点
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeigbort(int v1,int v2){
        for(int j=v2+1;j<vertexList.size();j++){
            if(edges[v1][j]>0){
                //矩阵的值大于0 表示有边 有邻接点
                return j;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历算法
     * @param isVisited 标记每个顶点是否被访问过得数组
     * @param i
     */
    public void dfs(boolean[] isVisited,int i){
        System.out.print(getValueByIndex(i)+"->");
        //将这个节点设置为已访问
        isVisited[i]=true;
        //拿到以i为节点的第一个邻接节点
        int w=getFirstNeigbort(i);
        while (w!=-1){//说明有邻接节点
            if(!isVisited[w]){//如果这个节点没有被访问过
                dfs(isVisited,w);//递归开始以w为节点进行邻接递归
            }
            //w已经被访问过
            w=getNextNeigbort(i,w);
        }

    }

    /**
     * 遍历所有节点 进行dfs
     */
    public void dfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){//如果没被访问过 就进行dfs
                dfs(isVisited,i);
            }
        }
    }

    /***
     *对一个节点进行广度优先遍历算法
     * @param isVisited 标记节点是否被访问过
     * @param i 被访问的节点的下标
     */
    public void bfs(boolean[] isVisited,int i){
        int u;//表示队列的头结点u
        int w;//表示邻接节点
        //记录节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //访问当前节点
        System.out.print(getValueByIndex(i)+"->");
        //标记为已访问
        isVisited[i]=true;
        //将节点加入队列  队列特性 从尾部加 先进先出
        queue.addLast(i);
        while (queue.isEmpty()){
            //取出队列的头结点取出
            u=queue.removeFirst();
            //得到第一个邻接节点w
            w=getFirstNeigbort(u);
            while (w!=-1){//说明找到了
                //如果没有被访问
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    isVisited[w]=true;
                    //入队列
                    queue.addLast(w);
                }
                //已经访问过 以u为根节点 去查找w的下一个邻接节点 继续while循环判断
                w=getNextNeigbort(u,w);
            }
        }
    }

    public void bfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    /**
     * 得到边的数目
     * @return
     */
    public int getNumOfEdges(){
        return this.numOfEdges;
    }

    /**
     * 通过索引获取对应的顶点数据
     * @param i
     * @return
     */
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    /**
     * 返回v1和v2的权值
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1,int v2){
        return this.edges[v1][v2];
    }
    /**
     * 插入节点
     */
    public void addVettex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加图形的边
     * 给矩阵赋值
     * @param v1 表示点的下标 表示是第几个顶点
     * @param v2 第二个顶点 对应的下标
     * @param weight 权值
     */
    public void addEdge(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }
}
