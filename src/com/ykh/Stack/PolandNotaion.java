package com.ykh.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 后缀表达式 逆波兰表达式
 */
public class PolandNotaion {
    public static void main(String[] args) {
        //逆波兰表达式
        //为了方便 逆波兰表达式数字和符号使用空格隔开
        String suffixExpression="3 4 + 5 * 6 -";
        //1.先将表达式放入到list中
        List<String> rpnList=getLiStString(suffixExpression);

        //2.将List 遍历List 配合栈完成计算
        int calculate = calculate(rpnList);
        System.out.println(calculate);
    }

    //将一个逆波兰表达式(后缀表达式) 依次将数据和运算符放入ArrayList中
    public static List<String> getLiStString(String express){
        //将传入进来的表达式通过空格分隔
        String[] s = express.split(" ");
        List<String> list=new ArrayList<>();
        for (String s1 : s) {
            list.add(s1);
        }
        return list;
    }
    //完成运算
    /**
     * 从左至右扫描 遍历list
     */

    public static  int calculate(List<String> list){
        //创建一个栈
        Stack<String> stack=new Stack<>();
        //遍历list
        for (String s : list) {
            //使用正则表达式取出是不是数
            if(s.matches("\\d+")){//是数字
                stack.push(s);
            }else{//如果是运算符
                //从栈中pop出两个数字
                int num1=Integer.parseInt(stack.pop());
                int num2=Integer.parseInt(stack.pop());
                int res=0;
                //运算
                if(s.equals("+")){
                    res=num1+num2;
                }else if(s.equals("-")){
                    res=num2-num1;//用后弹出来的数减去先弹出来的数
                }else if(s.equals("*")){
                    res=num1*num2;
                }else if(s.equals("/")){
                    res=num2/num1;//用后弹出来的数除以先弹出来的数
                }else{
                    throw new RuntimeException("运算符有误");
                }
                //将res入栈
                stack.push(String.valueOf(res));
            }
        }
        //最后在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}
