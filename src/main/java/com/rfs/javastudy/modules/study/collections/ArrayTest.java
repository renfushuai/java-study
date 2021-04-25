package com.rfs.javastudy.modules.study.collections;

import cn.hutool.core.util.ArrayUtil;

public class ArrayTest {
    public static void main(String[] args) {
        int[] arr=new int[5];
        arr[0]=100;
        String join = ArrayUtil.join(arr, ",");
        System.out.println(join);
        int[] clone = arr.clone();
        System.out.println(ArrayUtil.join(clone, ","));
    }
}
