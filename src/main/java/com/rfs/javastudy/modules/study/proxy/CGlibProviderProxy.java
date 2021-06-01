package com.rfs.javastudy.modules.study.proxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
* @author: rfs
* @create: 2021/6/1
* @description: CGLib动态代理
 * 底层依靠 ASM（开源的 java 字节码编辑类库）操作字节码实现的，性能比 JDK 强，所以 Spring AOP 最终使用了 CGlib 来生成动态代理
 * 只能代理委托类中任意的非 final 的方法，另外它是通过继承自委托类来生成代理的，所以如果委托类是 final 的，就无法被代理了（final 类不能被继承）
 * JDK 动态代理的拦截对象是通过反射的机制来调用被拦截方法的，CGlib 采用了FastClass 的机制来实现对被拦截方法的调用。FastClass 机制就是对一个类的方法建立索引，通过索引来直接调用相应的方法
**/
public class CGlibProviderProxy {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // 设置目标类的字节码文件
        enhancer.setSuperclass(SimpleProvider.class);
        // 设置回调函数
        enhancer.setCallback(new MyMethodInterceptor());
        // 正式创建代理类
        SimpleProvider simpleProvider=(SimpleProvider)enhancer.create();
        simpleProvider.getData("{\"data\":{}}");
    }

}
class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("目标类增强前！！！");
        //注意这里的方法调用，不是用反射哦！！！
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("目标类增强后！！！");
        return object;
    }
}