package com.rfs.javastudy.modules.study.java8;

import com.google.common.collect.ArrayListMultimap;

import java.util.*;

/**
 * @author: rfs
 * @create: 2021/4/28
 * @description: java8 map新特性
 **/
public class MapTest {
    public static void main(String[] args) {
        /**********************getOrDefault**************************/
        Map<String, String> map = new HashMap();
        map.put("典韦", "站撸王");
        map.put("小乔", "一起玩耍吧");
        String value = map.get("安琪拉");
        // 判断为空，老款写法
        if (!Objects.isNull(value)) {
            System.out.println(value.toLowerCase());
        }
        // 新款写法
        // ps: 这里的前提，空字符串对于业务没有特殊意义。如果存在特殊意义，那就不能使用这种方式了。
        String valueNew = map.getOrDefault("安琪拉", "");
        /*******************computeIfAbsent*****************************/
        Map<String, List<String>> map2 = new HashMap();
        // 之前写法
        List<String> classify = map2.get("java框架");
        if (Objects.isNull(classify)) {
            classify = new ArrayList<>();
            classify.add("Spring");
            map2.put("java框架", classify);
        } else {
            classify.add("Spring");
        }
        // 新写法
        map2.computeIfAbsent("java框架", key -> new ArrayList<>()).add("spring");
        // ERROR:会有 NPE 问题 那其实这是错误的，当 Map 中 key 对应 value 不存在的时候，putIfAbsent将会直接返回 null。
        map2.putIfAbsent("java框架", new ArrayList<>()).add("Spring");
        // 快速完成一个键需要映射到多个值的场景。
        ArrayListMultimap<Object, Object> multiset = ArrayListMultimap.create();
        multiset.put("java框架", "Spring");
        multiset.put("java框架", "Mybatis");
        // java框架--->Spring,Mybatis
        /*******************单词统计*****************************/
        HashMap<String, Integer> countMap = new HashMap<>();
        // 第一种写法
        countMap.put("java",countMap.getOrDefault("java",0)+1);
        // 第二种写法 如果 java这个值在 countMap中不存在，那么将会其对应的 value 设置为 1。
        //存在，则会调用第三个参数 remappingFunction 函数方法进行计算。
        countMap.merge("java",1,Integer::sum);
        countMap.merge("java",1,(v1,v2)->v1+v2);
    }
}
