package com.rfs.design.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* @author: rfs
* @create: 2021/4/28
* @description: 策略模式
**/
@Component
public class ShareFactory {
    @Autowired
    private List<Share> shareFunctionList;
    public Share getShareFunction(EnumShareType type){
        for (Share share : shareFunctionList) {
            if (share.getShareFunctionType().equals(type)){
                return share;
            }
        }
        return null;
    }
}
