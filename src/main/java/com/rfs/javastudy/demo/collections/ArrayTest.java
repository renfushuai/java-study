package com.rfs.javastudy.demo.collections;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Strings;

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
