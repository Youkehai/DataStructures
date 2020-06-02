package com.ykh.linkedList.SingleLinkedList;

/**
 * 单向链表
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        Node<String> node=new Node<>(1,"小明");
        Node<String> node1=new Node<>(2,"小红");
        Node<String> node2=new Node<>(3,"小张");

        SingleLinkedList<String> linkedList=new SingleLinkedList<>();
//        linkedList.add(node);
//        linkedList.add(node1);
//        linkedList.add(node2);
//        linkedList.sout();

        linkedList.addByOrder(node);
        linkedList.addByOrder(node2);
        linkedList.addByOrder(node1);
        linkedList.sout();
//        Node<String> node3=new Node<>(3,"小张修改");
//        linkedList.update(node3);
//        System.out.println("修改之后");
//        linkedList.sout();
//        System.out.println("删除前长度"+linkedList.size());
//        System.out.println("删除一个");
//        linkedList.remove(1);
//        linkedList.sout();
//        System.out.println("删除后长度"+linkedList.size());
//        System.out.println("获取指定元素 倒数第二个"+linkedList.getLastNode(2));
//        System.out.println("获取指定元素 正数第一个"+linkedList.getNode(1));
//        linkedList.reserve();
//        System.out.println("反转链表");
//        linkedList.sout();
        SingleLinkedList<String> linkedList1=new SingleLinkedList<>();
        Node<String> node4=new Node<>(4,"小明");
        Node<String> node5=new Node<>(5,"小红");
        Node<String> node6=new Node<>(6,"小张");
        linkedList1.addByOrder(node5);
        linkedList1.addByOrder(node4);
        linkedList1.addByOrder(node6);
        System.out.println("合并之前的新链表");
        linkedList1.sout();
        linkedList.addAll(linkedList1);
        System.out.println("合并之后的第一个链表");
        linkedList.sout();
    }
}

/**
 * 单向链表类  --有头节点的
 */
class SingleLinkedList<E>{
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
    }

    /**
     * 合并列表 合并列表
     * @param list
     */
    public void addAll(SingleLinkedList<E> list){
        Node<E> node=list.getHeadNode();//拿到传进来的链表的头
        /**
         * 取出新链表的next进行while循环
         */
        Node<E> next=null;//定义一个值存放next
        while (node!=null){
            //非常重要！！！
            //将node.next取出来
            next=node.next;
            //将node.next赋值为null
            node.next=null;
            //然后再去调用addByOrder这个方法
            //因为在addByOrder这个方法中 会调用node.next去进行对比 如果不把next赋值为null 那么会将整个长条链表全部一次性传进去 会造成no编号重复
            add(node);
            node=next;
        }
    }

    /**
     * 合并列表 合并两个有序的 合并完之后任然有序
     * @param list
     * @param order 是否有序 true为有序 false为直接按本来的链表方式直接存储
     */
    public void addAll(SingleLinkedList<E> list,boolean order){
        Node<E> node=list.getHeadNode();//拿到传进来的链表的头
        /**
         * 取出新链表的next进行while循环
         */
        Node<E> next=null;//定义一个值存放next
        while (node!=null){
            //非常重要！！！
            //将node.next取出来
            next=node.next;
            //将node.next赋值为null
            node.next=null;
            //然后再去调用addByOrder这个方法
            //因为在addByOrder这个方法中 会调用node.next去进行对比 如果不把next赋值为null 那么会将整个长条链表全部一次性传进去 会造成no编号重复
            if(order){
                addByOrder(node);
            }else{
                add(node);
            }
            node=next;
        }
    }

    /**
     * 反转链表
     * @return
     */
    public void reserve(){
        if(headNode.next==null || headNode.next.next==null){
            return ;
        }
        Node<E> cur=headNode.next;//拿到第一个节点
        Node reserveNode=new Node<>(0,"");//定义一个新的头结点 用于临时建立一个新单列表 后面会清理掉 因为没有指针指向他
        //定义一个空的节点类 用于保存cur的next  因为需要一个临时变量存储cur.next 存储它是为了遍历后移cur 因为原本的cur.next会在前面被赋值改变掉
        //所以需要定义一个临时的用来暂时存储
        Node<E> next=null;
        //遍历原来的链表   遍历一个就取出 放在最后面
        while(cur!=null){
            next=cur.next;//暂时保存当前节点的下一个  因为后面需要把这个节点赋值给cur再进行循环
            cur.next=reserveNode.next;//将cur.next 指向新链表的最前端 第一次肯定cur.next=null 第二次进来就是cur.next=第一次进来的cur 然后链表就可以串起来了
            //相当于在上面先把cur.next指向头结点 然后在下一步将新的头节点的next指向我上面新赋值的cur 这样就能把整个链条串起来
            reserveNode.next=cur;//将cur连接到新的链表  这两步加起来才是将每一次进来的cur节点放入新的头节点的最前端
            cur=next;//让cur后移
        }
        //将本来的头部指向新的节点的头个节点
        headNode.next=reserveNode.next;
        //return headNode.next;
    }

    /**
     * 获取链表的正数index个
     * @return
     */
    public Node<E> getNode(int index){
        if(headNode.next==null){
            System.out.println("没有节点");
            return null;
        }
        int size=size();
        if(size<index || index<0){
            System.out.println("数据长度不够或指定错误");
            return null;
        }
        Node<E> node=headNode.next;
        //通过for循环一直往后移动
        // 例如找第三个元素 共有五个元素
        // 那么node=node.next就要5-3=2次 node=node.next执行两次则正好指向第三个元素
        for(int i=0;i<index;i++){//这是从下标开始算
            node=node.next;
        }
        return node;
    }

    /**
     * 获取链表的倒数index个
     * @return
     */
    public Node<E> getLastNode(int index){
        if(headNode.next==null){
            System.out.println("没有节点");
            return null;
        }
        int size=size();
        if(size<index || index<=0){
            System.out.println("数据长度不够或指定错误");
            return null;
        }
        Node<E> node=headNode.next;
        //通过for循环一直往后移动
        // 例如找第三个元素 共有五个元素
        // 那么node=node.next就要5-3=2次 node=node.next执行两次则正好指向第三个元素
        for(int i=0,len=size-index;i<len;i++){//这是从下标开始算
                node=node.next;
        }
        return node;
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
        //找到待删节点的前一个
        Node<E> temp=headNode;
        boolean flag=false;//标识是否找到待删除的前一个
        while(true){
            if(temp.next==null){
                System.out.println("没有找到需要删除的编号");
                break;
            }
            if(temp.next.no==no){
                // temp.value=newNode.value;
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){//删除  指向被删除的后一个
            temp.next=temp.next.next;
        }else{
            System.out.println("没有找到需要删除的编号:"+no);
        }
    }
    /**
     * 通过node中的no去判断添加到哪 ，通过no大小来添加
     * @param e
     */
    public void addByOrder(Node<E> e){
        //如果头部为空，直接加到头部
        if(headNode.next==null){
            headNode.next=e;
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
        }else{
            e.next=temp.next;//一定要先给新的节点赋值next 不然会陷入死循环
            temp.next=e;
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
    public Node<E> next; //单向链表的指向

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