package com.rfs.javastudy.modules.study.proxy;

import com.alibaba.fastjson.JSON;

public class SimpleProvider implements IProvider{
    @Override
    public Object getData(String json) {
        return JSON.parseObject(json);
    }
}
