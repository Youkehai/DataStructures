package com.ykh.linkedList.josephu;

import javax.sound.midi.Soundbank;

/**
 * 约瑟夫问题 （丢手帕） 环形单链表
 * 小朋友围一圈 从第n个小朋友开始报数 报k个数 报到k的小朋友出圈 得到出圈顺序
 */
public class josephuDemo {
    public static void main(String[] args) {
        //先创建五个boy
        CircleSingleLinkedList list=new CircleSingleLinkedList();
        list.add(25);
        list.showList();
        //测试出圈问题
        list.outOrder(15,15);
    }
}

/**
 * 环形单向链表类
 */
class CircleSingleLinkedList{
    private Boy first=null;//fitrst节点
    private int size=0;
    /**
     *
     * @param nums
     */
    public void add(int nums){
        if(nums<1){
            System.out.println("num最小大于1");
            return;
        }
        size=nums;
        Boy boy=null;//for循环中使用
        Boy curBoy=null;//辅助变量
        for(int i=1;i<=nums;i++){
            //根据编号创建boy节点
            //如果是第一个
            boy=new Boy(i);
            if(i==1){
                first=boy;
                first.setNext(first);//构成环状
                curBoy=first;//指向第一个
            }else{
                curBoy.setNext(boy);//第二次进来  将first的next指向新建的boy节点 形成单链表
                //形成环装 将最后一个的下一个指向第一个
                boy.setNext(first);
                curBoy=boy;//后移 继续形成单链表
            }
        }
    }

    /**
     * 遍历当前链表
     */
    public void showList(){
        if(first==null){
            System.out.println("没有元素");
            return;
        }
        //first变量的值不能改变 使用辅助变量完成
        Boy curBoy=first;
        while (true){
            System.out.println("小孩编号"+curBoy.getNo());
            if(curBoy.getNext()==first){//已经遍历到环形表的最后一个
               // System.out.println("到最后了");
                break;
            }
            curBoy=curBoy.getNext();
        }
    }

    /**
     * 拿到出圈顺序
     * @param startNo 表示从第几个开始数
     * @param countNum 表示数几下
     */
    public void outOrder(int startNo,int countNum){
        //校验数据
        if(first==null || startNo<1||startNo>size){
            System.out.println("参数错误");
            return;
        }

        //辅助变量
        Boy helper=first;
        //先让helper指向链表最后
        while(true){
            if(helper.getNext()==first){ //说明已经是最后一个
                break;
            }
            helper=helper.getNext();
        }
        //报数前 让first和helper 移动startNo-1次 因为是从startNo开始报数的 所以需要后移
        for(int j=0;j<startNo-1;j++){
            first=first.getNext();
            helper=helper.getNext();
        }
        while(true){
            if(helper==first){//表示只有一个节点 直接结束
                break;
            }
            //让first和helper同时移动 countNum-1次 就是从第一个人开始报数 报countum次 因为第一个人也要报数 所以移动countNum-1次
            for(int j=0,len=countNum-1;j<len;j++){
                first=first.getNext();
                helper=helper.getNext();
            }
            //这时候first指向的节点 就是要出圈的节点
            System.out.println("出圈小孩编号:"+first.getNo());
            //移除first  让他出圈 后移一位 因为上面的for循环中 每一次都让first后移了 所以此时的first应该就是需要出圈得小孩 那么要移除first 就将它直接改为first.next
            first=first.getNext();
            helper.setNext(first); //helper的位置一直是最后一位 此时将first改变后 重新将helper的next改为新的first
        }
        System.out.println("最后留在圈中的编号:"+first.getNo());
    }
}
/**
 * 节点类
 */
class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}