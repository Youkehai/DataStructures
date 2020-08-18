/*
 * Copyright (c) 游克海创建于 2020 -8 -17 8:10 :27
 */

package com.ykh.binarySortTree;

/**
* 二叉排序树
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr={7,3,10,12,5,1,6};
        BinarySortTree tree=new BinarySortTree();
        //循环添加节点
        for (int i : arr) {
            tree.add(i);
        }
        //中序遍历
        tree.infixOrder();
        //删除12
      //  tree.remove(12);
        tree.remove(10);
        System.out.println("删除后");
        tree.infixOrder();
    }
}

class BinarySortTree{
    private Node root; //根节点

    /**
     * 添加节点
     * @param value
     */
    public void add(int value){
        Node node = new Node(value);
        if(root==null){
            root=node;//直接将node作为根节点
        }else{
            root.add(node);
        }

    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if(root!=null){
            root.infixOrder();
        }else{
            System.out.println("the tree is empity");
        }
    }
    /**
     * 查找要删除掉的节点
     * @return
     */
    public Node get(int value){
        if(this.root==null){
            return null;
        }
        return root.get(value);
    }

    /**
    * 查找要删除节点的父节点
     * @param value
     */
    public Node getParent(int value){
        if(this.root==null){
            return null;
        }
        return root.getParent(value);
    }

    /**
     *查找到Node下面最小的值 并删除 返回最小值
     * @param node 传入的节点 （二叉树根节点）
     * @return 返回以Node根节点 找到node下面的子树中的最小的值
     */
    public int delRightTreeMin(Node node){
        Node temp=node;//临时保存
        //循环查找左节点 找到node下面的最小值
        //找到最左边
        while(temp.left!=null){
            temp=temp.left;
        }
        //删除掉最小节点
        remove(temp.value);
        return temp.value;
    }

    /**
     *查找到Node下面最大的值 并删除 返回最大值
     * @param node 传入的节点 （二叉树根节点）
     * @return 返回以Node根节点 找到node下面的子树中的最大的值
     */
    public int delLeftTreeMax(Node node){
        Node temp=node;//临时保存
        //循环查找左节点 找到node下面的最小值
        //找到最左边
        while(temp.right!=null){
            temp=temp.right;
        }
        //删除掉最大节点
        remove(temp.value);
        return temp.value;
    }

    /**
     *
     * @param value
     */
    public void remove(int value){
        if(root==null){
            return;
        }else{
            //查找到需要删除的节点
            Node targetNode=get(value);
            if(targetNode==null){//没有找到需要删除的节点
                return;
            }
            //说明只有一个节点 并且要删除的节点为根节点
            if(root.left==null && root.right==null){
                root=null;
                return;
            }
            //查找需要删除的节点的父节点
            Node parentNode=getParent(value);
            //如果要删除的为叶子节点
            if(targetNode.left==null && targetNode.right==null){
                //判断要删除的节点是父节点的左子节点还是右子节点
                if(targetNode.value<parentNode.value){//说明是左子节点
                    parentNode.left=null;
                }else{
                    parentNode.right=null;
                }
            }else if(targetNode.left!=null && targetNode.right!=null){//说明要删除的节点是一个拥有两颗子树的节点
                //从需要删除的节点中 去从右侧找到右侧最小的节点(或者从左侧找到值最大的节点)
                int minValue=delRightTreeMin(targetNode.right);
                //从需要删除的节点中左侧找到值最大的节点
                int maxValue=delLeftTreeMax(targetNode.left);

                //将最小值赋值给要删除的节点 即将二叉树继续按照排序二叉树的排序方式进行调整 即左侧比父节点小 右侧比父节点值大
                //因为从右侧找到最小的值 肯定比左侧大 比右侧小 可以继续形成排序二叉树
              //  targetNode.value=minValue;
                targetNode.value=maxValue;
            }else{//要删除的节点只有一颗子树
                //如果要删除的节点有左子节点
                if(targetNode.left!=null){
                    //如果要删除的节点是父节点的左子节点
                    if(targetNode.value < parentNode.value){//说明是左子节点
                        parentNode.left=targetNode.left;
                    }else{
                        parentNode.right=targetNode.left;
                    }
                }else{//说明要删除的节点有右子树节点
                    //如果要删除的节点是父节点的左子节点
                    if(targetNode.value < parentNode.value){//说明是左子节点
                        parentNode.left=targetNode.right;
                    }else{
                        parentNode.right=targetNode.right;
                    }
                }
            }
        }
    }
}


/**
 * 二叉树节点
 */
class Node{
    int value;
    Node left;
    Node right;

    /**
     * 查找要删除掉的节点
     * @return
     */
    public Node get(int value){
        if(value==this.value){//说明是该节点 表示找到了
            return this;
        }else if(value<this.value){
            if(this.left==null){
                return null;//说明没找到
            }
            //查找的值小于当前值 向左边进行递归 根据二叉排序树的规则
            return this.left.get(value);
        }else{
            if(this.right==null){
                return null;//说明没找到
            }
            //查找的值小于当前值 向左边进行递归 根据二叉排序树的规则
            return this.right.get(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     * @param value
     */
    public Node getParent(int value){
        //如果当前节点就是要查找的这个节点的父节点
        if((this.left!=null && this.left.value==value) || (this.right!=null && this.right.value==value)){
            return this;
        }else{
            //如果查找的值小于当前this的值 并且节点的左子节点!=null
            if(value<this.value && this.left!=null){
                //向左递归查找
                return this.left.getParent(value);
            }else if(value>=this.value && this.right!=null){
                //递归向右查找
                return this.right.getParent(value);
            }else{
                return null;//没有父节点
            }
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 递归添加节点
     * 满足二叉排序树标准
     * 左边比根节点小 右边比根节点大
     * @param node
     */
    public void add(Node node){
        if(node ==null ){
            return;
        }
        //判断传入节点的值和当前二叉树的值的关系
        if(node.value<this.value) {//this为当前的二叉树
            //当前左子节点为空 直接放左边
            if (this.left == null) {
                this.left = node;
            } else {
                //递归向左判断
                this.left.add(node);
            }
        }else{
            //当前右子节点为空 直接放左边
            if (this.right == null) {
                this.right = node;
            } else {
                //递归向右判断
                this.right.add(node);
            }
        }

    }

    /**
    中序遍历 先左再中 再右
     */
    //中序遍历
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }
    }
}
