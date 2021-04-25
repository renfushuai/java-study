package com.rfs.javastudy.modules.study.design.abstractfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
