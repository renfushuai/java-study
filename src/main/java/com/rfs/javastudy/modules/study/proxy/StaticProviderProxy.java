package com.rfs.javastudy.modules.study.proxy;

/**
* @author: rfs
* @create: 2021/4/28
* @description: 静态代理
 * 1.代理类只代理一个委托类（其实可以代理多个，但不符合单一职责原则），也就意味着如果要代理多个委托类，就要写多个代理（别忘了静态代理在编译前必须确定）
 * 2.第一点还不是致命的，再考虑这样一种场景：如果每个委托类的每个方法都要被织入同样的逻辑，比如说我要计算前文提到的每个委托类每个方法的耗时，就要在方法开始前，开始后分别织入计算时间的代码，那就算用代理类，它的方法也有无数这种重复的计算时间的代码
**/
public class StaticProviderProxy implements IProvider{
    IProvider provider;
    public StaticProviderProxy(IProvider provider){
        this.provider=provider;
    }
    @Override
    public Object getData(String json) {
        System.out.println("代理类被执行");
        return provider.getData(json);
    }

    public static void main(String[] args) {
        IProvider provider=new StaticProviderProxy(new SimpleProvider());
        provider.getData("{\"data\":{}}");
    }
}
