package com.rfs.javastudy.modules.study;

import sun.misc.Launcher;

import java.net.URL;

public class ClassInitTest {
    private  static  int num=1;
    private static int number=20;
    static {
        num=2;
        number=10;
    }

    public static void main(String[] args) {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());
        }
        System.out.println(ClassInitTest.num);
        System.out.println(ClassInitTest.number);
    }
}
