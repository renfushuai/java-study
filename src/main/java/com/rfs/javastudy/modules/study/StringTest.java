package com.rfs.javastudy.modules.study;

import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

public class StringTest {
    public static void main(String[] args) {
        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));
        String s1 = new String("hello");
        String s2 = new String("hello");
        System.out.println(d == s2);


        MyClass myClass1 = new MyClass();
        MyClass myClass2 = new MyClass();
        System.out.println(myClass1.i);
        System.out.println(MyClass.j);
        System.out.println(myClass2.i);
        System.out.println(MyClass.j);

        MyClass myClass = new MyClass();
        StringBuffer buffer = new StringBuffer("hello");
        myClass.changeValue(buffer);
        System.out.println(buffer.toString());
        for (int i = 0; i < 10; i++) {
            System.out.println(ThreadLocalRandom.current().nextInt(100));;
        }
    }

    public static void changeClass(MyClass my) {
        my.setName("renfushuai");
    }
}

@Data
class MyClass {
    public final double i = Math.random();
    public static double j = Math.random();

    void changeValue(final StringBuffer buffer) {
        buffer.append("world");
    }

    void changeValueInt(int v) {
        v++;
    }

    public String name;
    public Integer age;

    void changeValueString(String v) {
        String s = v + "34";
    }
}
