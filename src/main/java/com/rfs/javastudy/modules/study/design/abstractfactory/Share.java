package com.rfs.javastudy.modules.study.design.abstractfactory;

public interface Share {
    /**
    * @author: rfs
    * @create: 2021/4/20
    * @description: 获取分享类型
    **/
    EnumShareType getShareFunctionType();
    String mainProcess(String shareName);
}
