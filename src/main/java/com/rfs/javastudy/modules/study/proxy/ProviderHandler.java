package com.rfs.javastudy.modules.study.proxy;

import cn.hutool.core.util.StrUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProviderHandler implements InvocationHandler {
    Object target;
    public Object bind(Object target){
        this.target=target;
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
        ProviderHandler providerHandler=new ProviderHandler();
        IProvider provider =(IProvider) providerHandler.bind(new SimpleProvider());
        provider.getData("{\"data\":{}}");
    }
}
