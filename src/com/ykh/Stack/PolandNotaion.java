package com.ykh.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 后缀表达式 逆波兰表达式
 */
public class PolandNotaion {
    public static void main(String[] args) {
        //将中缀表达式转为后缀表达式
        //1.1+（（2+3）*4）-5 --》 1 2 3 + 4 * +  5 -
        //2 直接操作字符串不方便 因此 先将"1+（（2+3）*4）-5"转成list 一个一个按顺序放进去 放入arrayList中【1，+，（，（，。。。】这样存放
        String expression="1+((2+3)*41)-55";
        List<String> strings = toInfixExpression(expression);
        List<String> list = toSuffixExpressionList(strings);
        System.out.println("中缀表达式转完List是："+strings);
        System.out.println("后缀表达式是："+list);
        //3 将得到的中缀表达式的list 转成后缀表达式 即转换成 2 3 + 4 * +  5 -这样的ArrayList
        System.out.println("结果是："+calculate(list));


        //逆波兰表达式
        //为了方便 逆波兰表达式数字和符号使用空格隔开
//        String suffixExpression="3 4 + 5 * 6 -";
//        //1.先将表达式放入到list中
//        List<String> rpnList=getLiStString(suffixExpression);
//
//        //2.将List 遍历List 配合栈完成计算
//        int calculate = calculate(rpnList);
//        System.out.println(calculate);
    }

    /**
     * 通过中缀表达式的list转换成后缀表达式的list
     * @return
     */
    public static List<String> toSuffixExpressionList(List<String> list){
        //定义两个栈
        Stack<String> operStarck=new Stack<>();//符号栈
        //这个栈 在整个转换过程中，没有取出的pop操作 后面还需逆序输出
        //可以直接用List
       // Stack<String> resStack=new Stack<>();
        List<String> resList=new ArrayList<>();
        //遍历传进来的list
        for (String s : list) {
            //如果是数字 就直接入栈  入resList
            if(s.matches("\\d+")){
                resList.add(s);
            }else if(s.equals("(")){//如果是左括号 也直接入栈
                operStarck.push(s);
            }else if(s.equals(")")){
                //如果是右括号 则依次弹出operStarck栈顶的运算符 并放入resList 直到遇到左括号为止 此时将这一对括号丢弃
                while (!operStarck.peek().equals("(")){//如果栈顶没有看到左括号
                    resList.add(operStarck.pop());//依次弹出operStarck栈顶的运算符 并放入resList 直到遇到左括号为止
                }
                operStarck.pop();//此时将这一对括号丢弃 将（ 丢掉 消除括号
            }else{
                //当s的优先级小于等于operStarck栈顶的运算符优先级
                //将operStarck栈顶的运算符弹出 加入到resList中 一直重复比较
                while (operStarck.size()!=0 && (Operation.getValue(operStarck.peek())>=Operation.getValue(s))){
                        resList.add(operStarck.pop());
                }
                //还需将s压入栈
                operStarck.push(s);
            }
        }
        //将operStarck剩余的运算符加入到resList中
        while (!operStarck.isEmpty()){
            resList.add(operStarck.pop());
        }
        return resList;//在list中 直接按顺序输出 就是逆波兰表达式 不用像栈一样还要逆转
    }

    /**
     *将中缀表达式转为对应的list
     * @return
     */
    public static List<String> toInfixExpression(String s){
            //定义一个List
        List<String> list=new ArrayList<>();
        int i=0;//扫描字符串使用的遍历指针
        String str;//用于多位数拼接
        char c;//每次拿到一个字符 就放入c
        do {
            //如果c是非数字，直接加入到list中
            //acsii码
            if((c=s.charAt(i))<48 || (c=s.charAt(i))>57){
                list.add(String.valueOf(c));
                i++;
            }else{//如果是一个数字 则考虑多位数
                str="";//先设置为空字符串
                while(i<s.length()&& ((c=s.charAt(i))>=48 && (c=s.charAt(i))<=57)){
                    str+=c;
                    i++;
                }
                list.add(str);
            }
        }while (i<s.length());
        return list;
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
                    //throw new RuntimeException("运算符有误");
                    System.out.println("运算符不存在");
                    break;
                }
                //将res入栈
                stack.push(String.valueOf(res));
            }
        }
        //最后在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}

/**
 * 直接拿到运算符对应的优先级
 */
class Operation{

    //加
    private static int ADD=1;
    /**
     * 减
     */
    private static int SUB=1;
    /**
     * 乘
     */
    private static int MUL=2;
    /**
     * 除
     */
    private static int DIV=2;

    /**
     * 根据传进来的运算符拿到优先等级
     * @param opertation
     * @return
     */
    public static int getValue(String opertation){
        int res=0;
        switch (opertation){
            case "+":
                res=ADD;
                break;
            case "-":
                res=SUB;
                break;
            case "*":
                res=MUL;
                break;
            case "/":
                res=DIV;
                break;
            default:
                System.out.println("运算符不存在");
                break;
        }
        return res;
    }
}
