package com.ykh.Stack;

/**
 * 数组实现栈
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack stack=new ArrayStack(10);
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
    }
}

/**
 *
 * 数组栈类
 */
class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//存放数据的数组
    private int top=-1;//栈顶

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack=new int[this.maxSize];
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
    public void push(int value){
        if(isFull()){
            System.out.println("栈已满");
            return;
        }
        top++;
        stack[top]=value;
    }

    /**
     * 出栈
     */
    public int pop(){
        //判断栈是否为空
        if(isEmpity()){
            throw new RuntimeException("栈空 无数据");
        }
        int value=stack[top];
        top--;
        return value;
    }

    public void showList(){
        if(isEmpity()){
            System.out.println("null stack");
            return;
        }
        for(int i=top;i>=0;i--){
            System.out.println("第"+i+"个数据"+stack[i]);
        }
    }
}
