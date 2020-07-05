/*
 * Copyright (c) 游克海创建于 2020 -7 -4 4:37 :53
 */

package com.ykh.HashTable;

public class HashTableDemo {
    public static void main(String[] args) {
        //创建hashtable
        HashTable hashTable = new HashTable(7);
        //放值
        for(int i=0;i<30;i++){
            hashTable.add(new Employee(i,"小明"+i,"广州"+i));
        }
        //输出
       //hashTable.list();
        //通过ID查找员工
//        Employee employee = hashTable.get(1);
//        System.out.println("找到的员工信息"+employee);
        hashTable.remove(22);
        hashTable.list();
    }
}

/**
 * hash表
 */
class HashTable{
    /**
     * 链表集合
     */
    private EmployeeLinkedList[] employeeLinkedLists;
    private int size;
    //构造器 初始化大小
    public HashTable(int size){
        this.size=size;
        employeeLinkedLists=new EmployeeLinkedList[size];
        //初始化数组中的每个对象
        for(int i=0;i<size;i++){
            employeeLinkedLists[i]=new EmployeeLinkedList();
        }
    }

    //添加

    /**
     * 添加 根据员工ID得到该员工应该放在哪个链表里面
     * @param employee
     */
    public void add(Employee employee){
        //通过散列函数拿到该放到哪个链表中
        int employeeLinkedListNo=hashFun(employee.id);
         //放入
        employeeLinkedLists[employeeLinkedListNo].add(employee);
    }

    /**
     * 遍历所有链表 遍历整个hashtable的所有数据
     */
    public void list(){
        for(int i=0;i<size;i++){
            employeeLinkedLists[i].list(i+1);
        }
    }

    /**
     * 通过ID查找
     * @param id
     * @return
     */
    public Employee get(int id){
        int employeeLinkedListNo=hashFun(id);
        return employeeLinkedLists[employeeLinkedListNo].get(id);
    }
    /**
     * 通过ID删除
     * @param id
     * @return
     */
    public void remove(int id){
        int employeeLinkedListNo=hashFun(id);
        employeeLinkedLists[employeeLinkedListNo].remove(id);
    }

    /**
     * 散列函数 通过一个简单地取模 去决定放入哪个链表
     * @param id
     * @return
     */
    public int hashFun(int id){
        return id%size;
    }
}

/**
 * 员工类
 */
class Employee{
    public int id;
    public String name;
    public String address;
    public Employee next;

    /**
     * 构造器
     * @param id
     * @param name
     * @param address
     */
    public Employee(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

class EmployeeLinkedList{
    //头指针 指向链表的第一个emp 因此此链表的head 直接指向第一个employee
    private Employee  head;//默认Null

    /**
     * 往链表添加数据
     * 1.假设添加时id自增 即ID是从小到大进行排序的 每次直接将该员工直接加到链表最后
     * @param emp
     */
    public void add(Employee emp){
        //如果是添加第一个
        if(head==null){
            head=emp;
            return;
        }
        //如果不是第一个 则使用一个辅助变量指针 定位到链表最后
        Employee temp=head;
        while (true){
            if(temp.next==null){//说明到了最后
                break;
            }
            temp=temp.next;//后移
        }
        //退出while循环时表示已经到了最后 直接将emp加到最后即可
        temp.next=emp;
    }

    /**
     * 遍历链表
     */
    public void list(int i){
        if(head==null){//空链表
            System.out.println("第"+i+"个链表为空链表");
            return;
        }
        System.out.println("第"+i+"个链表信息");
        Employee temp=head;
        while (true){
            System.out.println(temp);
            if(temp.next==null){//说明到了最后
                break;
            }
            temp=temp.next;//后移
        }
    }

    /**
     * 通过id查找员工
     * @param id
     * @return
     */
    public Employee get(int id){
        //判断链表是否为空
        if(head==null){
            System.out.println("空链表");
            return null;
        }
        Employee temp=head;
        while (true){
            if(temp.id==id){//说明找到了
                break;
            }
            if(temp.next==null){//说明到了最后
                temp=null;
                break;
            }
            temp=temp.next;//后移
        }
        return temp;
    }

    /**
     * 通过id删除员工
     * @param id
     * @return
     */
    public void remove(int id){
        //判断链表是否为空
        if(head==null){
            System.out.println("空链表");
            return;
        }
        Employee temp=head;
        //再定义一个值 用于存放不需要删掉的数据
        Employee notDeleteData=null;
        boolean flag=false;
        int i=0;//定义一个值
        while(true){
            if(temp==null){
                System.out.println("没有找到需要删除的编号");
                break;
            }
            if(temp.id==id){//这里不需要用之前的单链表的那种方式去删除 直接用当前temp的ID去对比就可以找到
//                if(i==0){ //如果是第一次就直接找到了要删除的数据 那么直接在将自己后移一位即可
//                    notDeleteData=temp.next;
//                }else{
//                    //如果找到了需要删除的数据 那么将之前定义的notDeleteData.next本来是指向temp的 现在变为指向temp.next
//                    notDeleteData.next=temp.next;
//                }
                flag=true;
                break;
            }
//            Employee next=temp.next;
//            if(i==0){
//                temp.next=null;
//                notDeleteData=temp;
//            }else{
//                while (true){
//                    if(notDeleteData.next==null){//说明到了最后
//                        break;
//                    }
//                    notDeleteData=notDeleteData.next;//后移
//                }
//                temp.next=null;
//                notDeleteData.next=temp;
//            }
            temp=temp.next;
           // i++;
        }
        if(flag){//删除  指向被删除的后一个
            temp=temp.next;
        }else{
            System.out.println("没有找到需要删除的编号:"+id);
        }
    }
}
