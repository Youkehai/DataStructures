/*
 * Copyright (c) 游克海创建于 2020 -10 -9 8:9 :14
 */

package com.ykh.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 骑士周游算法
 * 步骤:1.先将当前位置标记为已访问
 * 2.根据当前位置计算哪些位置可走 哪些位置不可走 并放入一个集合 每走一步 就用一个变量统计
 * 3.遍历可走通路线的集合 可以走通 就继续走，如果走不通 就需要回溯
 * 4.判断是否走完 根据第二步的变量进行判断 每次走通一步进行+1 如果棋盘的位置和第二步的变量一样 表示走完 如果不一样 则继续走 如果走不通 则回溯
 * 根据配置规则的不同 会得到不同的结果和效率
 *
 * 使用贪心算法优化
 * 1.拿到该点可走通路线的集合 ps
 * 2.对ps这个集合再去拿到它所有能走的下一步的集合 比如ps里面有五个选择 那么就再拿到ps[0]的下一步的集合,再拿ps[1],ps[2],[ps3],ps[4]的下一步集合 进行非递减排序
 * 3. 对ps进行排序之后，先测试下一步可走路线更多的的先走 减少回溯 提高效率
 */
public class HorseChessboard {

    private static int X;//棋盘的列数
    private static int Y;//棋盘的行数
    //标记棋盘各个位置是否被访问过
    private static boolean visited[];
    //标记整个棋盘是否已经被走完
    private static boolean finished;

    public static void main(String[] args) {
        X=8;
        Y=8;
        int row=1;//初始位置 行
        int colum=1; //初始位置列
        int[][] cheesboard=new int[X][Y];//棋盘
        visited=new boolean[X*Y];//每个位置是否被访问过
        long starttime= System.currentTimeMillis();
        traversalChessboard(cheesboard,row-1,colum-1,1);
        long needTime=+System.currentTimeMillis()-starttime;
        System.out.println("所需时间"+needTime+"ms");//使用贪心算法只用了23-50ms 没用贪心算法21387-30000ms
        //输出棋盘情况
        for (int[] ints : cheesboard) {
            for (int anInt : ints) {
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 骑士周游算法
     * @param chessboard 棋盘
     * @param row 棋子当前位置的行 从0开始
     * @param column 棋子当前位置的列 从0开始
     * @param step 棋子走的是第几步 初始值为1
     */
    public static void traversalChessboard(int[][] chessboard,int row,int column,int step){
        //标记棋盘的值
        chessboard[row][column]=step;
        //row*X+column的值为当前行列的位置 即第row*X+column个格子已经访问过 标记为true
        visited[row*X+column]=true;//标记当前已经被访问
        //获取当前位置可走位置的集合
        ArrayList<Point> pointArrayList = next(new Point(column,row ));
        //对pointArrayList进行排序 通过贪心算法的本质 贪心 拿到下一步可走路线更多的先走 减少回溯 提高效率
        sort(pointArrayList);
        while (!pointArrayList.isEmpty()){
            //取出一个可以走的点
            Point remove = pointArrayList.remove(0);
            //判断该点是否已经被访问
            if(!visited[remove.y*X+remove.x]){//说明还没有被访问过
                traversalChessboard(chessboard,remove.y,remove.x,step+1);
            }
        }
        //判断棋盘是否被走完
        if(step<X*Y && !finished){//没有走完
            //棋盘置0
            chessboard[row][column]=0;
            visited[row*X+column]=false;//标记当前未被访问
        }else{
            finished=true;
        }
    }

    /**
     * 根据当前位置 统计出来哪些位置可以走 并放入arrayList中
     * @param curPoint 当前位置
     * @return
     */
    public  static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> points = new ArrayList<>();
        //创建一个Point
        Point p1 = new Point();
        //表示这个位置可以走 需要靠图去画一个一个去验证
        if((p1.x=curPoint.x-2)>=0 && (p1.y=curPoint.y-1)>=0){
            points.add(new Point(p1));
        }
        if((p1.x=curPoint.x-1)>=0 && (p1.y=curPoint.y-2)>=0){
            points.add(new Point(p1));
        }
        if((p1.x=curPoint.x+1)<X && (p1.y=curPoint.y-2)>=0){
            points.add(new Point(p1));
        }
        if((p1.x=curPoint.x+2)<X && (p1.y=curPoint.y-1)>=0){
            points.add(new Point(p1));
        }
        if((p1.x=curPoint.x+2)<X && (p1.y=curPoint.y+1)<Y){
            points.add(new Point(p1));
        }
        if((p1.x=curPoint.x+1)<X && (p1.y=curPoint.y+2)<Y){
            points.add(new Point(p1));
        }
        if((p1.x=curPoint.x-1)>=0 && (p1.y=curPoint.y+2)<Y){
            points.add(new Point(p1));
        }
        if((p1.x=curPoint.x-2)>=0 && (p1.y=curPoint.y+1)<Y){
            points.add(new Point(p1));
        }
        return points;
    }

    /**
     * 根据当前可走集合 拿到可走集合的下一步可走集合，进行非递减排序
     * @param points
     */
    public static void sort(ArrayList<Point> points){
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(next(o1).size()<next(o2).size()){
                    return -1;
                }else if(next(o1).size()==next(o2).size()){
                    return 0;
                }else{
                    return 1;
                }
            }
        });
    }
}
