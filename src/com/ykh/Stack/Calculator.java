package com.ykh.Stack;

public class Calculator {
    public static void main(String[] args) {
        String expression="7+3*2+1*2";
        ArrayStack1 numStack = new ArrayStack1(10);
        //符号栈
        ArrayStack1 operStack = new ArrayStack1(10);
        //用于扫描遍历表达式
        int index=0;
        int num1;
        int num2;
        int oper;
        int res;//结果
        char cha=' ';//每次扫描得到的操作符 存到cha
        String keepNum="";//用于拼接多位数
        //开始循环 得到每个字符
        while(true){
            //拿到每个字符串
            cha=expression.substring(index,index+1).charAt(0);
            //判断cha
            if(operStack.isOper(cha)){//如果是运算符
                //判断当前符号栈是否为空 如果是空 直接入栈
                if(operStack.isEmpity()){
                    operStack.push(cha);
                }else{
                    //不为空 比较操作优先级 和符号栈中的栈顶的数据进行比较
                    //如果当前的运算符小于等于栈中的运算符 就从栈中取出两个数，然后从操作符栈中取出一个操作符 进行计算
                    //拿到计算结果后 将当前结果加入到numStack数栈 然后再将这个操作符加入到符号栈
                    if(operStack.priority(cha)<=operStack.priority((operStack.peek()))){
                        num1=numStack.pop();//取出第一个数
                        num2=numStack.pop();//取出第二个数
                        oper=operStack.pop();//取出第一个操作符
                        res=numStack.cal(num1,num2,oper);//拿到计算结果
                        //把计算结果入栈到数栈
                        numStack.push(res);
                         //当前操作符入栈
                        operStack.push(cha);
                    }else{//如果当前操作符优先级大于栈顶优先级 直接将符号入栈
                        operStack.push(cha);
                    }
                }
            }else{//如果是数字 就直接放入数栈
                //处理多位数
                keepNum+=cha;
                //如果cha已经是最后一位 直接入栈
                if(index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断后一位是否是数字 如果是数字则继续扫描  如果不是数字就直接入栈
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){//如果是操作符
                        //入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //赋值为null
                        keepNum="";
                    }
                }

             //   numStack.push(cha-48);//因为将上面substring拿到的值是一个字符串的值 字符串的ASCII码和真正的数据相差正好是48 所以需要减去48
            }
            //index+1 并判断是否到最后
            index++;
            if(index>=expression.length()){
                break;
            }
        }
        //表达式扫描完毕 开始计算
        while (true){
            //如果符号栈为空 那么直接有结果 如果没有
            if(operStack.isEmpity()){
                break;
            }
            //就要开始计算
            num1=numStack.pop();//取出第一个数
            num2=numStack.pop();//取出第二个数
            oper=operStack.pop();//取出第一个操作符
            res=numStack.cal(num1,num2,oper);//拿到计算结果
            numStack.push(res);
        }
        System.out.println("表达式"+expression+"结果是:"+numStack.pop());
    }
}

/**
 *
 * 数组栈类
 */
class ArrayStack1{
    private int maxSize;//栈的大小
    private int[] stack;//存放数据的数组
    private int top=-1;//栈顶

    public ArrayStack1(int maxSize) {
        this.maxSize = maxSize;
        stack=new int[this.maxSize];
    }

    /**
     * 获取当前栈顶数据 但不出栈
     * @return
     */
    public int peek(){
        return stack[top];
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

    /**
     * 返回运算符的优先级 数字越大 优先级越高
     */
    public int priority(int oper){
        if(oper=='*' || oper=='/'){
            return 1;
        }else if(oper =='+'|| oper=='-'){
                return 0;
        }else{
            return -1;
        }
    }

    /**
     * 判断是否是操作符
     * @param val
     * @return
     */
    public boolean isOper(char val){
        return val=='+' || val=='-' ||val=='*' ||val=='/';
    }

    /**
     * 计算两个数的结果
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int cal(int num1,int num2,int oper){
        int res=0;//计算结果
        switch(oper){
            case '+':
                res=num1+num2;
                break;
            case '-':
                res=num2-num1;//num2-num1
                break;
            case '*':
                res=num1*num2;
                break;
            case '/':
                res=num2/num1;
                break;
            default:
                break;
        }
        return res;
    }
}
