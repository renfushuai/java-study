package com.rfs.string;

public class StringInternTest {
    public static void main(String[] args) {
        String s2="1";// 字符串常量池创建对象
        String s1=new String("1");//会创建两个对象，一个是堆上的，一个是常量池的
        System.out.println(s1==s2);
    }
}
