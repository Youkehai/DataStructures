/*
 * Copyright (c) 游克海创建于 2020 -7 -14 8:37 :38
 */

package com.ykh.tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6};
        //创建一个arrtree
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
    }
}

/**
 * 顺序存储二叉树
 */
class ArrayBinaryTree{
    //存储数据的数组
    private int[] arr;

    public ArrayBinaryTree(int[] arr){
        this.arr=arr;
    }

    /**
     * 重载 默认调用第一个
     */
    public void preOrder(){
        preOrder(0);
    }
    /**
     * 前序遍历 先遍历自己 在遍历左边 然后右边 因为顺序存储二叉树 他的第n个节点的左边肯定是n*2+1
     * 右边是n*2+2
     * 数组的下标
     * @param index
     */
    public void preOrder(int index){
        //如果数组为空 或arr。length==0
        if(arr==null || arr.length==0){
            System.out.println("empity Tree");
            return ;
        }
        //输出当前元素
        System.out.println(this.arr[index]);
        //向左遍历
        if((index*2+1)<arr.length){
            preOrder(index*2+1);
        }
        //向右遍历
        if((index*2+2)<arr.length){
            preOrder(index*2+2);
        }
    }
}
