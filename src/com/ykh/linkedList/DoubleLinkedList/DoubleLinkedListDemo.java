package com.ykh.linkedList.DoubleLinkedList;

/**
 * 单向链表
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        Node<String> node=new Node<>(1,"小明");
        Node<String> node1=new Node<>(2,"小红");
        Node<String> node2=new Node<>(3,"小张");

        DoubleLinkedList<String> linkedList=new DoubleLinkedList<>();
//        linkedList.add(node);
//        linkedList.add(node1);
//        linkedList.add(node2);
//        linkedList.sout();
//        Node<String> node3=new Node<>(3,"小张修改");
//        linkedList.update(node3);
//        System.out.println("修改后");
//        linkedList.sout();
//        linkedList.remove(1);
//        System.out.println("删除小明");
//        linkedList.sout();
        linkedList.addByOrder(node);
        linkedList.addByOrder(node2);
        linkedList.addByOrder(node1);
        linkedList.sout();
    }
}

/**
 * 双向链表类  --有头节点的
 */
class DoubleLinkedList<E>{
    //首先定义一个头结点
    Node headNode= new Node(0, "");

    public Node getHeadNode() {
        if(headNode.next==null){
            return null;
        }
        return headNode.next;
    }

    /**
     * 普通添加  直接加到链表的最后
     * @param e
     */
    public void add(Node<E> e){
        //如果头部为空，直接加到头部
        if(headNode.next==null){
            headNode.next=e;
            e.pre=headNode;
            return;
        }
        Node<E> temp=headNode;
        while(true){
            //找最后一个节点 ，最后一个节点next肯定是null
            if(temp.next==null){
                break;
            }
            temp=temp.next; //往后移动
        }
        temp.next=e;//将元素放到最后
        e.pre=temp;//将新元素的前置节点设置为temp
    }

    /**
     * 通过node中的no去判断添加到哪 ，通过no大小来添加
     * @param e
     */
    public void addByOrder(Node<E> e){
        //如果头部为空，直接加到头部
        if(headNode.next==null){
            headNode.next=e;
            e.pre=headNode;
            return;
        }
        Node<E> temp=headNode;
        boolean front=false;
        while(true){
            if(temp.next==null){//没有了 遍历完了 那就直接放到最后面去
                front=true;
                break;
            }
            //如果该节点的编号大于要加进来的节点的编号
            //那么说明我新加进来的节点的位置肯定是在temp.next的前面和temp的后面
            //即 e.next=temp.next temp.next=e
            if(temp.next.no>e.no){
                break;
            }else if(temp.next.no==e.no){
                System.out.println("重复编号"+e.no);
                break;
            }
            temp=temp.next; //往后移动
        }
        if(front){
            temp.next=e;
            e.pre=temp;
        }else{
            e.next=temp.next;//一定要先给新的节点赋值next 不然会陷入死循环
            temp.next=e;
            e.pre=temp;
        }

    }

    /**
     * 修改节点值 根据no
     * @param newNode
     */
    public void update(Node<E> newNode){
        if(headNode.next==null){
            System.out.println("空链表");
            return;
        }
        Node<E> temp=headNode.next;
        boolean flag=false;
        while(true){
            if(temp==null){
                System.out.println("没有找到需要修改的编号");
                break;
            }
            if(temp.no==newNode.no){
                // temp.value=newNode.value;
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.value=newNode.value;
        }else{
            System.out.println("没有找到需要修改的编号");
        }
    }

    /**
     * 被删除的节点不会有引用指向他 会被jvmGC回收
     * @param no
     */
    public void remove(int no){
        if(headNode.next==null){
            System.out.println("空链表");
            return;
        }
        //找到要删除的节点
        Node<E> temp=headNode.next;
        boolean flag=false;//标识是否找到待删除的节点
        while(true){
            if(temp==null){
                System.out.println("没有找到需要删除的编号");
                break;
            }
            if(temp.no==no){
                // temp.value=newNode.value;
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){//删除  将temp移除
            //将要删除的元素的next的pre设置成temp.pre 即1-2-3 删除2 改为1-3
            if(temp.next!=null){//为了保证不是最后一个节点 不是最后一个节点才进行.next换值
                temp.next.pre=temp.pre;
            }
            //将要删除的元素的pre的next设置成temp.next 即1-2-3 删除2 改为1-3
            temp.pre.next=temp.next;
        }else{
            System.out.println("没有找到需要删除的编号:"+no);
        }
    }
    /**
     * 获取长度
     * @return
     */
    public  int size(){
        int length=0;
        if(headNode.next==null){
            return  length;
        }
        Node<E> head=headNode.next;
        while (head!=null){
            length++;
            head=head.next;
        }
        return length;
    }

    /**
     * 遍历整个链表
     */
    public void sout(){
        if(headNode.next==null){//如果第一个链表的头部都没有指向下一级，那么表示链表为空
            System.out.println("the list is empity");
            return;
        }
        Node<E> temp=headNode.next;
        while(true){
            //找最后一个节点 ，最后一个节点next肯定是null
            System.out.println(temp);
            if(temp.next==null){
                break;
            }
            temp=temp.next; //往后移动
        }
    }
}

/**
 * 先定义一个节点类
 */
class Node<E> {
    public int no;//序号
    public E value;//泛型的value
    //双向链表两个核心属性
    public Node<E> next; //单向链表的指向
    public Node<E> pre;//指向链表的上一个

    public Node(int no, E value) {
        this.no = no;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", value=" + value +
                '}';
    }
}