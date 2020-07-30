/*
 * Copyright (c) 游克海创建于 2020 -7 -30 8:16 :31
 */

package com.ykh.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * hhuffmanTree是什么? 也叫最优二叉树
 * huffmanTree是一颗带权路径长度（wpl）最小的树 即每一个节点的权值*它到根节点的长度的值最小，则表示这棵树的带权路径长度最小
 * 权值为节点的值
 * 则是一课huffmanTree
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr={13,7,8,3,29,6,1};
        Node root=createHuffmanTree(arr);
        preOrder(root);
    }

    /**
     * 前序遍历 测试代码是否正确
     * @param root 根节点
     */
    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else{
            System.out.println(" tree is empity");
        }
    }
    /**
     * 创建一课huffmanTree
     * @param arr
     * @return huffmanTree的根节点
     */
    public  static Node createHuffmanTree(int[] arr){
        //为了方便操作，先将arr中的所有数据取出，构建一个Node,并放入arrayList中
        //放入arrayList是为了使用工具类Collections
        List<Node> nodes=new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }
        //循环nodes 一直重复下面的步骤
        //直到剩下只有一个节点 则是最后的最大节点 即树的根节点
        while (nodes.size()>1){
            //排序，从小到大开始排序
            //使用工具类 排序算法会根据node类中重写过的compareTo方法进行排序
            Collections.sort(nodes);
            //取出根节点权值最小的两个 可把节点看出二叉树
            Node left=nodes.get(0);
            //取出第二小的
            Node right=nodes.get(1);
            //构建一个新的二叉树
            //左子树+右子树的值作为父类的值 然后将左子节点右子节点放入parent的左右
            Node parent=new Node(left.value+right.value);
            //得到一个二叉树
            parent.left=left;
            parent.right=right;
            //从nodes中删除已经取出的值
            nodes.remove(left);
            nodes.remove(right);
            //将新的parent节点加入到nodes中 因为下一次构建二叉树会继续使用到 下次使用过之后再把该节点删除
            nodes.add(parent);
        }
        //上面循环完成只会留下一个节点
        //循环完成后 直接返回root节点 即树的根节点
        return nodes.get(0);
    }
}

/**
 * 节点类
 * 实现comparable接口实现排序
 */
class Node implements  Comparable<Node>{
    int value;//节点的权值
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 前序遍历
     * 用于查看huffmanTree是否成功
     */
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


    /**
     * 重写Comparable方法
     * @param o
     * @return
     */
    @Override
    public int compareTo(Node o) {
        return this.value-o.value;//表示从小到大排序
    }
}
