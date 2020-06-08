package com.ykh.recursion;

/**
 * 八皇后问题 即八个皇后不能在同一行 同一列 和同一斜线 求出共有多少种解法
 */
public class Queue8 {
    //定义一个值  表示共有多少个皇后
    int max=8;
    static int count=0;//总解法
    static int judgeCount=0;//总共执行判断冲突次数
    //定义一个数组 保存皇后放置位置的结果 比如arr={0,4,7,5,2,6,1,3}
    int[] array=new int[max];

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.set(0);
        System.out.println("共有"+count+"种解法");
        System.out.println("判断冲突"+judgeCount+"次");
    }

    //放置第n个皇后
    /**
     * 放置第n个皇后
     * @param n 第n个
     */
    private void set(int n){
        if(n==max){//表示在放第九个皇后 则可以直接返回 因为只有八皇后
            print();//直接打印
            return;
        }
        //如果没有到最后一个 那么依次放入 并判断是否冲突
        for (int i=0;i<max;i++){
            //先把当前的第n个皇后 放到当前行的第1列
            array[n]=i;
            //判断是否冲突
            if(judge(n)){
                //不冲突 直接放入第n+1个皇后
                set(n+1);//for 循环递归 会回溯 每一次进来这个方法 会有一次新的for循环 则会一直遍历直到结果完成
            }
            //如果位置冲突 那么继续向下循环
        }
    }

    //查看当我们放置第n个皇后时 就去检该皇后的位置是否和前面已经摆放的皇后的位置是否冲突
    /**
     * 查看当我们放置第n个皇后时 就去检该皇后的位置是否和前面已经摆放的皇后的位置是否冲突
     * @param n 第n个
     * @return
     */
    private boolean judge(int n){
        judgeCount++;
        for(int i=0;i<n;i++){
            //因为使用的是数组 而不是二维数组 所以数组的下标则表示行 只需要用i下标取出列的值就可以 n则表示行
           //  array[i]==array[n]说明是在同一列 判断第n个皇后时否和前面的某一个皇后在同一列
            // Math.abs(n-i)==Math.abs(array[n]-array[i]) 表示是否在同一斜线
            //例如 n=1 则是第二个皇后 则是第二列 则array[0]=0
            //Math.abs(n-i)=Math.abs(1-0)=1  Math.abs(array[n]-array[i])=Math.abs(1-0)=1
            if(array[i]==array[n] || (Math.abs(n-i)==Math.abs(array[n]-array[i]))){
                return false;
            }
        }
        return true;
    }

    //定义一个可以将皇后摆放的位置打印出来
    /**
     * 打印结果
     */
    private void print(){
        for (int i=0,len=array.length;i<len;i++){
            System.out.print(array[i]+" ");
        }
        count++;//每次打印完一种解法 count++一次
        System.out.println();
    }
}
