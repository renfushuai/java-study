package com.rfs.suanfa;

import com.alibaba.fastjson.JSON;

/**
 * @author renfushuai
 * @date 2022/1/28
 */
public class 冒泡排序 {
    public static void main(String[] args) {
        int[] a = new int[]{20, 40, 30, 10, 60, 50};
        int[] b = bubbleSort(a);
        System.out.println(JSON.toJSONString(b));
    }

    public static int[] bubbleSort(int[] a) {
        int tmp;
        int n = a.length;
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    // 交换a[j] a[j+1]
                    tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }
        return a;
    }
}
