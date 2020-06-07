package com.ykh.recursion;

public class MiGong {

    /**
     * 因为策略太多 所以没有每个都去写 记录以下思路
     * 最短路径思路：将不同的迷宫策略 上下左右 上左下右 上右下左等所有策略列出
     * 然后执行不同的策略方法 将每个方法的2的数字拿出来 2最少的则是最短路径策略 则可以拿到最短路径
     * @param args
     */
    public static void main(String[] args) {
        //先创建一个二维数组 模拟迷宫
        int [][] map=new int[8][7];//八行七列
        //使用1表示墙
        //先将迷宫地图的上下全部置为1
        for(int i=0;i<7;i++){
            map[0][i]=1;//列
            map[7][i]=1;
        }
     //再将迷宫地图的左右全部置为1
        for(int i=0;i<8;i++){
            map[i][0]=1;//行
            map[i][6]=1;
        }
        //设置挡板
        map[3][1]=1;
        map[3][2]=1;
        //map[1][2]=1;//死路
        //map[2][2]=1;//死路
        //输出地图
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                System.out.print(map[i][j]+" ");//加空格为了好看
            }
            System.out.println();//输出完一行换行
        }
        System.out.println("走过的地图");
        //使用递归回溯 找路
        //searchWay(map,1,1);//[1][1]为起点
        searchWayTop(map,1,1);//修改策略
        //输出走过和标识过得地图
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                System.out.print(map[i][j]+" ");//加空格为了好看
            }
            System.out.println();//输出完一行换行
        }
    }

    /**
     *  map表示地图  i j表示从哪里出发开始找
     *  如果小球能到终点 这里设为map[6][5]
     *  当map[i][j]=0时 表示没走过 1表示墙 表示不能走的地方 为2表示是通路 可以走 为3表示这个位置点已经走过 但是走不通 表示此路不通
     *  在走迷宫时 需要确定一个方法 先走下还是先走左或者走右走上 走不通就尝试新方向 回溯
     * @param map 地图二维表
     * @param i 从哪个位置开始找
     * @param j
     * @return  如果找到路返回true 否则false
     */
    public static boolean searchWay(int[][] map,int i,int j){
        if(map[6][5]==2){//说明已经找到终点
            return true;
        }else{
            //回溯 递归开始
            if(map[i][j]==0){//如果这个点还没有走过
                //按照策略 下-》右-》上-》左
                map[i][j]=2;//先修改值 假定该点可以走通
             if(searchWay(map,i+1,j)){//向下走 行往下 则i+1 后面同理
                 return true;
             }else if(searchWay(map,i,j+1)){//向右走
                 return true;
             }else if(searchWay(map,i-1,j)){//向上走
                 return true;
             }else if(searchWay(map,i,j-1)){//向左走
                 return true;
             }else{
                 //说明该点不能走通 修改为3 因为上下左右都走不通
                 map[i][j]=3;
                 return false;
             }
            }else{//如果map[i][j]不等于0 表示走过 可能是 1(墙),2(可以走通),3（死路）
                return false;
            }
        }

    }

    /**
     * 修改找路策略为 上右下左
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean searchWayTop(int[][] map,int i,int j){
        if(map[6][5]==2){//说明已经找到终点
            return true;
        }else{
            //回溯 递归开始
            if(map[i][j]==0){//如果这个点还没有走过
                //按照策略 下-》右-》上-》左
                map[i][j]=2;//先修改值 假定该点可以走通
                if(searchWayTop(map,i-1,j)){//向上走
                    return true;
                }else if(searchWayTop(map,i,j+1)){//向右走
                    return true;
                }else if(searchWayTop(map,i+1,j)){//向下走
                    return true;
                }else if(searchWayTop(map,i,j-1)){//向左走
                    return true;
                }else{
                    //说明该点不能走通 修改为3 因为上下左右都走不通
                    map[i][j]=3;
                    return false;
                }
            }else{//如果map[i][j]不等于0 表示走过 可能是 1(墙),2(可以走通),3（死路）
                return false;
            }
        }

    }
}
