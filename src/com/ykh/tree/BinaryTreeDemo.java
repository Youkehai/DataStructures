/*
 * Copyright (c) 游克海创建于 2020 -7 -8 8:43 :7
 */

package com.ykh.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTreee binaryTree=new BinaryTreee();
        //创建需要的节点
        Node root=new Node(1,"aa");
        Node node2=new Node(2,"bb");
        Node node3=new Node(3,"cc");
        Node node4=new Node(4,"dd");
        Node node5=new Node(5,"ee");
        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//        System.out.println("中序遍历");
//        binaryTree.middleOrder();
//        System.out.println("后序遍历");
//        binaryTree.lastOrder();
        System.out.println("前序遍历查找");
        Node rNode = binaryTree.preOrderSearch(1);
        System.out.println(rNode);

        System.out.println("中序遍历查找");
        Node rNode1 = binaryTree.middleOrderSearch(1);
        System.out.println(rNode1);

        System.out.println("后序遍历查找");
        Node rNode2 = binaryTree.lastOrderSearch(1);
        System.out.println(rNode2);
    /*    System.out.println("删除1号节点");
        binaryTree.remove(1);
        Node rNode3 = binaryTree.lastOrderSearch(1);
        System.out.println("查找一号节点"+rNode3);*/
        binaryTree.remove(3);//删除3 前序遍历 应改为1254
        System.out.println("删除3之后的前序遍历");
        binaryTree.preOrder();
    }
}

/**
 * 二叉树类
 */
class BinaryTreee{
    private Node root;//根节点

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        if(this.root!=null){
            this.root.preOrder();
        }else{
            System.out.println("binaryTree is empity");
        }
    }

    /**
     * 中序遍历
     */
    public void middleOrder(){
        if(this.root!=null){
            this.root.middleOrder();
        }else{
            System.out.println("binaryTree is empity");
        }
    }

    /**
     * 后序遍历
     */
    public void lastOrder(){
        if(this.root!=null){
            this.root.lastOrder();
        }else{
            System.out.println("binaryTree is empity");
        }
    }

    /***
     * 前序遍历查找
     * @param no
     * @return
     */
    public Node preOrderSearch(int no){
        if(root!=null){
            return root.preOrderSearch(no);
        }
        return null;
    }
    /***
     * 中序遍历查找
     * @param no
     * @return
     */
    public Node middleOrderSearch(int no){
        if(root!=null){
            return root.middleOrderSearch(no);
        }
        return null;
    }
    /***
     * 后序遍历查找
     * @param no
     * @return
     */
    public Node lastOrderSearch(int no){
        if(root!=null){
            return root.lastOrderSearch(no);
        }
        return null;
    }

    /**
     * 删除二叉树中的某一个节点
     * @param no
     */
    public void remove(int no){
        if(this.root==null){
            System.out.println("the tree is empity");
            return;
        }
        if(this.root.getNo()==no){//如果要删除的就是根节点 那么直接将root赋值为null;
            this.root=null;
        }else{
            this.root.remove(no);
        }
    }
}

class Node{
    private int no;
    private String name;
    private Node left;
    private Node right;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     * 边聊顺序 :根节点-》左边节点遍历完 -》开始右节点，右节点也是先遍历左边的 在遍历右边的
     */
    public void preOrder(){
        System.out.println(this);//先输出当前节点(第一次为根节点)
        //递归输出左子树
        if(this.left!=null){
            this.left.preOrder();
        }
        //递归向右子树遍历
        if(this.right!=null){
            this.right.preOrder();
        }

    }

    /**
     * 中序遍历
     * 遍历顺序:左边节点遍历完->根节点->遍历右子节点
     */
    public void middleOrder(){
        //先遍历左边
        if(this.left!=null){
            this.left.middleOrder();
        }
        //输出父节点
        System.out.println(this);
        if(this.right!=null){
            this.right.middleOrder();
        }
    }

    /**
     * 后序遍历
     * 遍历顺序:左边节点遍历完->遍历右子节点->根节点
     */
    public void lastOrder(){
        //先遍历左边
        if(this.left!=null){
            this.left.lastOrder();
        }
        if(this.right!=null){
            this.right.lastOrder();
        }
        //输出父节点
        System.out.println(this);

    }

    /***
     * 前序遍历查找
     * @param no
     * @return
     */
    public Node preOrderSearch(int no){
        //先比较当前节点
        if(this.no==no){
            return this;
        }
        //定义一个结果 每次重新查找前赋值
        Node reNode=null;
        if(this.left!=null){
            //向左递归查找
            reNode=this.left.preOrderSearch(no);
        }
        if(reNode!=null){
            //说明在左边节点中找到了no
            return reNode;
        }
        //继续判断 向右递归
        if(this.right!=null){
            reNode=this.right.preOrderSearch(no);
        }
        return reNode;
    }

    /**
     * 中序遍历查找
     * @param no
     * @return
     */
    public Node middleOrderSearch(int no){
        //先比左子节点
        Node reNode=null;
        if(this.left!=null){
            //向左递归查找
            reNode=this.left.middleOrderSearch(no);
        }
        if(reNode!=null){
            //说明在左边节点中找到了no
            return reNode;
        }
        //再比较当前节点
        if(this.no==no){
            return this;
        }
        //继续判断 向右递归
        if(this.right!=null){
            reNode=this.right.middleOrderSearch(no);
        }
        return reNode;
    }

    /***
     * 后序遍历查找
     * @param no
     * @return
     */
    public Node lastOrderSearch(int no){
        //先比左子节点
        Node reNode=null;
        if(this.left!=null){
            //向左递归查找
            reNode=this.left.lastOrderSearch(no);
        }
        if(reNode!=null){
            //说明在左边节点中找到了no
            return reNode;
        }
        //继续判断 向右递归
        if(this.right!=null){
            reNode=this.right.lastOrderSearch(no);
        }
        if(reNode!=null){
            //说明在左边节点中找到了no
            return reNode;
        }
        //最后比较当前节点
        if(this.no==no){
            return this;
        }
        return null;
    }

    /**
     * 删除节点
     * @param no
     */
    public void remove(int no){
        //如果当前左子节点不为空且左子节点就是要删除的数据
        if(this.left!=null && this.left.no==no){
            //如果我删除的节点不是叶子结点
            if(this.left.left!=null || this.left.right!=null){
                //如果左边有 则直接拿左边代替原来的this
                if(this.left.left!=null){
                    this.left.left.right=this.left.right;//将本来的this.left的右节点赋值给新的替代他的左节点的右边
                    this.left=this.left.left;//新的left=本来的left的left节点
                    return;
                }
                if(this.left.right!=null){//同理 因为如果到了这一步 就证明只有right这一个右节点 所以直接赋值即可 不需考虑左右节点
                    this.left=this.left.right;
                    return;
                }

            }
            this.left=null;//删除左子节点以及其下面所有节点
            return ;
        }
        //如果当前右子节点不为空且左右节点就是要删除的数据
        if(this.right!=null && this.right.no==no){
            //如果左边有 则直接拿左边代替原来的this
            if(this.right.left!=null){
                this.right.left.right=this.right.right;//将本来的this.left的右节点赋值给新的替代他的左节点的右边
                this.right=this.right.left;//新的left=本来的left的left节点
                return;
            }
            if(this.right.right!=null){//同理 因为如果到了这一步 就证明只有right这一个右节点 所以直接赋值即可 不需考虑左右节点
                this.right=this.right.right;
                return;
            }
            this.right=null;//删除右子节点以及其下面所有节点
            return ;
        }
        if(this.left!=null){//递归左子树删除
            this.left.remove(no);
        }
        if(this.right!=null){//递归右子树删除
            this.right.remove(no);
        }

    }

}
