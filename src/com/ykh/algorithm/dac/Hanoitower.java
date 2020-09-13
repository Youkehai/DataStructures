/*
 * Copyright (c) 游克海创建于 2020 -9 -13 7:27 :30
 */

package com.ykh.algorithm.dac;

public class Hanoitower {

    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');
    }

    /**
     * 使用分治算法 递归求解
     * @param num 盘子的数量
     * @param a a塔
     * @param b b塔
     * @param c c塔
     */
    public static void hanoiTower(int num,char a,char b,char c){
        if(num==1){//如果只有一个盘
            System.out.println("第1个盘从"+a+"->"+c);
        }else{
            //如果盘大于或等于2  我们可以将所有的盘看成两个盘  1.最下面的一个盘 2.上面的所有盘 将这些盘分成两个盘去看待
            //1.先把最上面的所有盘A->B
            hanoiTower(num-1,a,c,b);
            //2.将最下面的盘A->C
            System.out.println("第"+num+"个盘从"+a+"->"+c);
            //3.将B塔所有的盘从B->C
            hanoiTower(num-1,b,a,c);
        }
    }
}
