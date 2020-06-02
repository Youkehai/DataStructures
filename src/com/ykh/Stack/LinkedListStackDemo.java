package com.ykh.Stack;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack stack=new LinkedListStack(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9 );
        stack.push(10);
        stack.showList();
       System.out.println(stack.pop());
        stack.showList();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.showList();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.showList();
        System.out.println(stack.pop());
        stack.showList();
    }

}
/**
 *
 * 链表栈类
 */
class LinkedListStack<E>{
    private int maxSize;//栈的大小
    private Node<E> stack;//存放数据的数组
    private int top=-1;//栈顶

    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
    }

    //判断数组满 栈满

    /**
     * 栈是否满
     * @return
     */
    public boolean isFull(){
        return top==maxSize-1;
    }

    //栈空

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpity(){
        return top==-1;
    }

    /**
     * 加数据
     * @param value
     */
    //入栈
    public void push(E value){
        if(isFull()){
            System.out.println("栈已满");
            return;
        }

        top++;
        if(stack==null){//表示是第一个节点
            stack=new Node<E>(value);
            return;
        }
        Node<E> cur=stack;//拿到第一个节点
        Node reserveNode=new Node<>(value);//定义一个新的头结点 用于临时建立一个新单列表 后面会清理掉 因为没有指针指向他
        reserveNode.next=stack;//添加的时候将这个新节点直接放到最前面
        stack=reserveNode;//然后再将stack重新赋值为这个新的节点
//        if(stack==null){//表示是第一个节点
//            stack=new Node<E>(value);
//            return;
//        }else{
//            Node<E> node=stack;
//            while (node.next!=null){
//                node=node.next;
//            }
//            node.next=new Node<E>(value);
//        }

    }

    /**
     * 出栈
     */
    public E pop(){
        //判断栈是否为空
        if(isEmpity()){
            throw new RuntimeException("栈空 无数据");
        }
        top--;
        Node<E> head=stack;
        E value= (E) "";
//        if(head.next!=null){//如果没到最后一个
//            while (head.next.next!=null){
//                head=head.next;
//            }
//            value=head.next.value;
//            head.next=null;
//        }else{//如果取值取到了最后一个 就直接拿这个节点的值返回
//            value=head.value;
//            head=null;
//        }
        if (head!=null){
                value=head.value;
        }
        stack=head.next;//移除掉第一个
//        value=head.value;
//        head=null;
        return value;
    }

    public void showList(){
        if(isEmpity()){
            System.out.println("null stack");
            return;
        }
        Node<E> temp=stack;
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
 * 链表节点类
 * @param <E>
 */
class Node<E>{
    public E value;
    public Node<E> next;

    public Node(E value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}