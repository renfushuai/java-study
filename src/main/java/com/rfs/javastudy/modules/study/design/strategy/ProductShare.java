package com.rfs.javastudy.modules.study.design.strategy;

import org.springframework.stereotype.Service;

/**
* @author: rfs
* @create: 2021/4/28
* @description: 商品分享
**/
@Service
public class ProductShare implements Share{
    @Override
    public EnumShareType getShareFunctionType() {
        return EnumShareType.PRODUCT;
    }

    @Override
    public String mainProcess(String shareName) {
        // 分享的业务逻辑
        System.out.println("商品分享:"+shareName);
        return shareName;
    }
}
