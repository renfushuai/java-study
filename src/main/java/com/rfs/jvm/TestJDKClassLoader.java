package com.rfs.jvm;

public class TestJDKClassLoader {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(com.sun.crypto.provider.AESKeyGenerator.class.getClassLoader());
        System.out.println(TestJDKClassLoader.class.getClassLoader());
    }
}
