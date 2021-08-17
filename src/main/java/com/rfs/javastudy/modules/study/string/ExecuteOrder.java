package com.rfs.javastudy.modules.study.string;

public class ExecuteOrder {
    public static void main(String[] args) {
        //B b=new B();
        new ExecuteOrder();
        System.gc();
        System.runFinalization();
    }
    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        System.out.println("重写了 finalize()");
    }
}

class A {
    public A(String text) {
        System.out.println(text);
    }
}
class B{
    static A a1=new A("a1");
    A a2=new A("a2");
    static{
        a1=new A("a3");
    }
    public B(){
        a2=new A("a4");
    }
}