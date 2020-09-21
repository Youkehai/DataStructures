/*
 * Copyright (c) 游克海创建于 2020 -9 -21 8:0 :37
 */

package com.ykh.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 贪心算法
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //放入数据
        HashSet<String> strings1 = new HashSet<>();
        strings1.add("北京");
        strings1.add("上海");
        strings1.add("天津");
        broadcasts.put("K1",strings1);
        HashSet<String> strings2 = new HashSet<>();
        strings2.add("广州");
        strings2.add("北京");
        strings2.add("深圳");
        broadcasts.put("K2",strings2);
        HashSet<String> strings3 = new HashSet<>();
        strings3.add("上海");
        strings3.add("成都");
        strings3.add("杭州");
        broadcasts.put("K3",strings3);
        HashSet<String> strings4 = new HashSet<>();
        strings4.add("上海");
        strings4.add("天津");
        broadcasts.put("K4",strings4);
        HashSet<String> strings5 = new HashSet<>();
        strings5.add("大连");
        strings5.add("杭州");
        broadcasts.put("K5",strings5);
        //存放所有地区 使用set String类型会自动去重
        HashSet<String> allArea = new HashSet<>();
        for (Map.Entry<String, HashSet<String>> stringHashSetEntry : broadcasts.entrySet()) {
            for (String s : stringHashSetEntry.getValue()) {
                allArea.add(s);
            }
        }
        //存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();
        //定义一个临时集合 保存遍历过程中覆盖地区和当前所有集合数据中的交集
        HashSet<String> tempSet = new HashSet<>();
        String maxKey=null;//保存交集最大的那个map的key
        while (allArea.size()>0){
            maxKey=null;//置为null 避免错误
            for (Map.Entry<String, HashSet<String>> stringHashSetEntry : broadcasts.entrySet()) {
                tempSet=new HashSet<>();
                tempSet=stringHashSetEntry.getValue();
                //求出两个集合的交集 并将交集赋值给tempSet 例如tempSet=[1,2,3] allArea=[1,2,4] 那么tempSet将=[1,2]
                tempSet.retainAll(allArea);
                //如果tempSet.size>0 表示两个集合有交集
                // tempSet.size()>stringHashSetEntry.getValue().size() 这个判断的意思是 拿每一个map里面的value和之前拿到的交集去对比 如果大于之前的交集 那么直接取这个 贪心算法就是取最大的
                //如果两个集合的交集 比maxKey指向的集合数量还要多 那么需要重置maxKey 即贪心算法的体现 如果你现在的交集比我大 那么我直接选取你作为交集 抛弃掉之前较少的那一个
                if(tempSet.size()>0 && (maxKey==null || tempSet.size()>stringHashSetEntry.getValue().size())){
                    maxKey=stringHashSetEntry.getKey();
                }
            }
            //应该将maxKey放入selects中 拿到最终结果
            if(maxKey!=null){
                selects.add(maxKey);
                //将maxKey中覆盖的地区 从allArea中去掉
                allArea.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("最优结果集"+selects);

    }
}
