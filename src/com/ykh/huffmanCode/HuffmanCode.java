/*
 * Copyright (c) 游克海创建于 2020 -8 -5 8:28 :0
 */

package com.ykh.huffmanCode;

import java.util.*;

/***
 * huffmanCode进行压缩
 * 通过生成huffmanTree 对每一个字符进行编码 然后拼接 然后按每个字符八位去取byte 重新构建一个byte数组
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String str="i like like like java do you like a java";
//        byte[] bytes = str.getBytes();
//        System.out.println(bytes.length);
//        List<Node> nodes = getNode(bytes);
//       // System.out.println(nodes);
//        Node node = CreateHuffmanTree(nodes);
////        preOrder(node);
//        //查看是否生成对应huffmanCode
//        Map<Byte, String> codes = getCodes(node);
////        System.out.println(codes);
//        byte[] zip = zip(bytes, codes);
//        //拿到压缩之后的数据
//        System.out.println(Arrays.toString(zip));
        byte[] bytes = huffmanZip(str);
        System.out.println(Arrays.toString(bytes));
        byte[] decode = decode(huffManCodes, bytes);
        System.out.println("解压完成后："+new String(decode));
    }


    /**
     * 对压缩后的数据进行解压 还原
     * @param huffManCodes 编码表
     * @param huffmanBytes 需要还原的数据
     * @return 还原之后的数据
     */
    private static byte[] decode(Map<Byte,String> huffManCodes,byte[] huffmanBytes){
        //得到huffmanBytes对应的二进制字符串 如101010111
        StringBuilder sb=new StringBuilder();
        //将bytes[]转成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            if(i==huffmanBytes.length-1){//判断是否为最后一个 是最后一个则不需要补高位
                sb.append(byteToBitString(false,huffmanBytes[i]));
            }else{
                sb.append(byteToBitString(true,huffmanBytes[i]));
            }
        }
        System.out.println("压缩后："+sb);//解码后的字符串
        //按编码表进行解码
        //先将编码表的map key value进行调换
        Map<String,Byte> map=new HashMap<>();
        for(Map.Entry<Byte,String> count :huffManCodes.entrySet()){
            map.put(count.getValue(),count.getKey());
        }
        //创建一个集合 存放byte
        List<Byte> byteList=new ArrayList<>();
        for (int i=0;i<sb.length();){//不需要i++ 因为在下面每次都i+=count了
            //定义几个变量 拼接字符串 作为上面map的key去取value
            int count=1;
            boolean flag=true;
            Byte b=null;
            while(flag){
                String key=sb.substring(i,i+count);
                //一直从map中取get 一直到拿到value
                 b = map.get(key);
                if(b!=null){
                    flag=false;
                }else{
                    count++;
                }
            }
            byteList.add(b);
            i+=count;//直接跳转到count这个位置 继续向后
        }
        //循环结束后 list中存放了所有的字符
        //取出list的数据 放入bytes中
        byte[] resultList=new byte[byteList.size()];
        for (int i = 0; i < resultList.length; i++) {
            resultList[i]=byteList.get(i);
        }
        return resultList;
    }


    /**
     * 将一个byte转成二进制字符串
     * @param flag 是否需要按位与 如果是true 表示需要补高位
     * @param b 需要转换的byte
     * @return b对应的二进制字符串 按补码返回
     */
    private static String byteToBitString(boolean flag,byte b){
        //保存byte-
        int temp=b;//将byte转成int
        //如果是正数 需要补高位
        //如果temp=1 ，按位与 256二进制为=》1 0000 0000 | 0000 0001=》1 0000 0001
        if(flag){
            temp |=256;
        }
        String s = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
        if(flag){
            return s.substring(s.length()-8);//只取后面八位
        }else{
            return s;
        }
    }

    /**
     * 封装上述方法 统一一个方法全部搞定
     * @param str 需要压缩的字符串
     * @return
     */
    private static byte[] huffmanZip(String str){
        byte[] bytes = str.getBytes();
        List<Node> nodes = getNode(bytes);
        Node node = CreateHuffmanTree(nodes);
        //查看是否生成对应huffmanCode
        Map<Byte, String> codes = getCodes(node);
        byte[] zip = zip(bytes, codes);
        //拿到压缩之后的数据
        return zip;
    }


    /**
     * 通过传进来的字符串对应的bytes数组和map的huffmanCode返回一个经过HuffmanCode处理后的byte[]
     * @param bytes 原始数据
     * @param huffManCodes huffmanCode表 里面存放每个字符对应的huffmanCode
     * @return 返回处理完成之后的bytes[]
     *
     */
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffManCodes){
        StringBuilder sb=new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(huffManCodes.get(aByte));
        }
        System.out.println("压缩前："+sb);//压缩前的字符串
        //统计字节长度
        int len;
        if(sb.length()%8 == 0){
            len=sb.length()/8;
        }else{
            len=sb.length()/8+1;
        }
        //创建一个压缩后的byte数组
        byte[] result=new byte[len];
        String strByte;
        for (int i=0;i<sb.length();i+=8){//每八位对应一个byte 所以每次循环+8
            if(i+8>sb.length()){//最后一个不够八位数
                strByte=sb.substring(i);
            }else{
                strByte=sb.substring(i,i+8);
            }
            //将strByte转成byte,放入result
            result[i/8]= (byte) Integer.parseInt(strByte,2);
        }
//        System.out.println(sb);
        return result;
    }

    /**存放编码 存放每个字符对应的huffmanCode
     * 形式如:32->01 97->100
     * */
    static Map<Byte,String> huffManCodes=new HashMap<>();
    /**
     * 拼接叶子结点路径 即huffmanCode
     * */
    static StringBuilder stringBuilder=new StringBuilder();

    private static Map<Byte,String> getCodes(Node node){
        if(node==null){
            return null;
        }
        getCodes(node,"",stringBuilder);
        return huffManCodes;
    }
    /**
     * 将传入的node节点的所有叶子结点的huffmanCode得到，并放入到huffManCodes这个map集合中
     * @param node 传入节点
     * @param code  代表路径值 左边为0 右边为1
     * @param stringBuilder 用于拼接路径 huffmanCode
     */
    private static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //将传入的code加入到新的中
        stringBuilder1.append(code);
        if(node!=null){
            //判断当前节点node是否是叶子结点
            if(node.data==null){//非叶子结点
                //向左递归处理
                getCodes(node.left,"0",stringBuilder1);
                //向右递归处理
                getCodes(node.right,"1",stringBuilder1);
            }else{//叶子结点
                //停止
                //说明找到了某一条路的最后
                huffManCodes.put(node.data,stringBuilder1.toString());
            }
        }
    }

    private static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else{
            System.out.println("empity tree");
            return ;
        }
    }
    /**
     *通过字符串的bytes拿到每个字符已经空格出现的次数
     * @param bytes
     * @return
     */
    private static List<Node> getNode( byte[] bytes ){
        List<Node> nodeList=new ArrayList<>();
        //存储每个字符出现的次数
        Map<Byte,Integer> countMap=new HashMap<>();
        for (byte aByte : bytes) {
            if(countMap.get(aByte)==null){
                countMap.put(aByte,1);
            }else{
                countMap.put(aByte,countMap.get(aByte)+1);
            }
        }
        //将每个字符对应的权重放入map中
        for(Map.Entry<Byte,Integer> count :countMap.entrySet()){
            nodeList.add(new Node(count.getKey(),count.getValue()));
        }
        return nodeList;
    }

    /**
     * 创建huffmanTree
     * @param nodes
     * @return
     */
    private static Node CreateHuffmanTree(List<Node> nodes){
        while (nodes.size()>1){
            //从小到大排
            Collections.sort(nodes);
            //取出一颗最小的二叉树
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            //创建一颗新的二叉树 没有data 只有权值weight
            //只有叶子结点有data其他的节点只有weight
            Node parent = new Node(null, left.weight + right.weight);
            parent.left=left;
            parent.right=right;
            //将已经处理过得节点删除
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);//将新的节点放进去进行重新排序
        }
        //nodes最后的这个节点为huffManTree根节点
        return nodes.get(0);
    }
}

class Node implements Comparable<Node>{
    Byte data;//存放数据本来的值 比如a->97 通过ascii码
    int weight;//权值 指数据（字符本身出现的次数）
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight-o.weight;//从小到大排序
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
}
