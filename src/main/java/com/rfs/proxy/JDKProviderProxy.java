package com.rfs.proxy;

import cn.hutool.core.util.StrUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
* @author: rfs
* @create: 2021/4/28
* @description: JDK动态代理
**/
public class JDKProviderProxy implements InvocationHandler {
    Object target;
    public Object bind(Object target){
        this.target=target;
        // 如果要用 JDK 代理，委托类必须实现接口
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(StrUtil.join(",",args));
        Object obj = method.invoke(target, args);
        System.out.println("动态代理被执行");
        return obj;
    }

    public static void main(String[] args) {
        JDKProviderProxy JDKProviderProxy =new JDKProviderProxy();
        SimpleProvider simpleProvider = new SimpleProvider();
        System.out.println(simpleProvider.getClass());
        IProvider provider =(IProvider) JDKProviderProxy.bind(simpleProvider);
        System.out.println(provider.getClass());
        provider.getData("{\"data\":{}}");
    }
}
