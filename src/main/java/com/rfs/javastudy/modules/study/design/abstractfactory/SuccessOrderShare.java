package com.rfs.javastudy.modules.study.design.abstractfactory;

import org.springframework.stereotype.Service;

@Service
public class SuccessOrderShare implements Share{
    @Override
    public EnumShareType getShareFunctionType() {
        return EnumShareType.SUCCESS_ORDER;
    }

    @Override
    public String mainProcess(String shareName) {
        // 分享的业务逻辑
        return shareName;
    }
}
