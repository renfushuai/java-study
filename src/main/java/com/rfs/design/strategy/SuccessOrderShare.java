package com.rfs.design.strategy;

import org.springframework.stereotype.Service;

/**
* @author: rfs
* @create: 2021/4/28
* @description: 完成订单分享
**/
@Service
public class SuccessOrderShare implements Share{
    @Override
    public EnumShareType getShareFunctionType() {
        return EnumShareType.SUCCESS_ORDER;
    }

    @Override
    public String mainProcess(String shareName) {
        // 分享的业务逻辑
        System.out.println("订单分享:"+shareName);
        return shareName;
    }
}
