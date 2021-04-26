package com.rfs.javastudy.modules.study.proxy;

public class ProviderProxy implements IProvider{
    IProvider provider;
    public ProviderProxy(IProvider provider){
        this.provider=provider;
    }
    @Override
    public Object getData(String json) {
        System.out.println("代理类被执行");
        return provider.getData(json);
    }

    public static void main(String[] args) {
        IProvider provider=new ProviderProxy(new SimpleProvider());
        provider.getData("{\"data\":{}}");
    }
}
