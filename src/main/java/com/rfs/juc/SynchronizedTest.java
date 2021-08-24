package com.rfs.juc;

import lombok.Data;

import java.util.stream.IntStream;
/**
* @author: rfs
* @create: 2021/8/19
* @description: synchronized 成员变量锁的是对象本身，静态变量锁的是类本身，wrong方法是成员变量，new对象之后是不同的对象
**/
@Data
public class SynchronizedTest {
    private static int counter=0;
    public synchronized void wrong(){
        counter++;
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1,1000000).parallel().forEach(i->new SynchronizedTest().wrong());
        System.out.println(SynchronizedTest.counter);
    }
}
