/*
 * Copyright (c) 游克海创建于 2020 -7 -22 8:20 :9
 */

package com.ykh.tree;

public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadNode root = new ThreadNode(1, "a");
        ThreadNode node1 = new ThreadNode(3, "b");
        ThreadNode node2= new ThreadNode(6, "c");
        ThreadNode node3 = new ThreadNode(8, "d");
        ThreadNode node4 = new ThreadNode(10, "e");
        ThreadNode node5 = new ThreadNode(14, "f");
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        //测试线索化
        ThreadBinaryTreee threadBinaryTreee = new ThreadBinaryTreee();
        threadBinaryTreee.setRoot(root);
        ThreadNode left = node4.getLeft();
        System.out.println("线索化之前"+left);
        threadBinaryTreee.threadNodes();
        ThreadNode threadLeft = node4.getLeft();
        System.out.println("线索化之后"+threadLeft);
        threadBinaryTreee.threadList();
    }
}

/**
        * 二叉树类
        */
class ThreadBinaryTreee{
    private ThreadNode root;//根节点

    //指向当前节点的前驱节点的指针
    //pre保留的是当前节点的前一个节点 用于遍历完后一个能找到回来的路
    private ThreadNode pre=null;

    public void setRoot(ThreadNode root) {
        this.root = root;
    }

    //遍历线索化二叉树

    /***
     * 遍历出来的结果和之前线索化二叉树使用的方式有关
     * 如果之前线索化二叉树是中序 那么遍历出来的就是中序
     */
    public void threadList(){
        ThreadNode node=root;
        while (node!=null){
            //查找leftType==1的节点
            //先往左一直找 找到有前驱节点的 则表示已经找到了最左边
            while (node.getLeftType()==0){
                node=node.getLeft();
            }
            //结束上述while后 输出最左边节点
            //打印当前节点
            System.out.println(node);
            //如果当前节点右指针是后继节点 就往后一直输出
            //因为线索化二叉树之后会排好顺序 只需往后一直拿后继节点即可
            while (node.getRightType()==1){
                //去拿当前节点的后继节点
                node=node.getRight();
                System.out.println(node);
            }
            //如果没有后继节点了 把当前节点往右移 继续找
            node=node.getRight();
        }
    }

    public void threadNodes(){
        this.threadNodes(this.root);
    }

    //二叉树中序线索化

    /**
     * 对二叉树进行中序遍历线索化
     * @param node  需要线索化的节点
     */
    public void threadNodes(ThreadNode node){
        if(node==null){
            System.out.println("error:empity tree");
            return ;
        }
        //因为是中序遍历则
        //先线索化左子树 递归处理
        threadNodes(node.getLeft());
        //线索化当前节点(当前节点) 最重要的逻辑都在处理当前节点
        //先处理当前节点的前驱节点
        if(node.getLeft()==null){
            //让当前节点的左子节点指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左子节点的类型 指向的是前驱节点 而不是左子树
            node.setLeftType(1);
        }
        //处理当前节点的后继节点
        if(pre!=null && pre.getRight()==null){
            pre.setRight(node);//让前驱节点的右指针指向当前节点
            pre.setRightType(1);//修改类型 表示指向后继节点
        }
        //处理完一个节点后 给pre赋值为当前节点 pre存储当前节点的上一个节点 用于建立后继节点的关系很有用
        pre=node;
        //线索化右子树
        threadNodes(node.getRight());
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
    public ThreadNode preOrderSearch(int no){
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
    public ThreadNode middleOrderSearch(int no){
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
    public ThreadNode lastOrderSearch(int no){
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

class ThreadNode{
    private int no;
    private String name;
    private ThreadNode left;
    private ThreadNode right;

    /**
     * leftType==0 表示指向的是左子树 如果为1 则表示指向的是前驱节点
     * rightType==0 表示指向的是右子树 如果为1 则表示指向的是后继节点
     */
    private int leftType;
    private int rightType;

    public ThreadNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    public ThreadNode getLeft() {
        return left;
    }

    public void setLeft(ThreadNode left) {
        this.left = left;
    }

    public ThreadNode getRight() {
        return right;
    }

    public void setRight(ThreadNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "ThreadNode{" +
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
    public ThreadNode preOrderSearch(int no){
        //先比较当前节点
        if(this.no==no){
            return this;
        }
        //定义一个结果 每次重新查找前赋值
        ThreadNode reNode=null;
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
    public ThreadNode middleOrderSearch(int no){
        //先比左子节点
        ThreadNode reNode=null;
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
    public ThreadNode lastOrderSearch(int no){
        //先比左子节点
        ThreadNode reNode=null;
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