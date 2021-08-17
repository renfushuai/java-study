package com.rfs.javastudy.redundantcode.templatemethod;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
* @author: rfs
* @create: 2021/8/17
* @description: vip用户
**/
@Service("VipUserCart")
public class VipUserCart extends NormalUserCart {
    // 第三件商品打7折
    @Override
    protected void processCouponPrice(long userId, Item item) {
        if (item.getQuantity() > 2) {
            item.setCouponPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity() - 2)).multiply(BigDecimal.valueOf(100 - Db.getUserCouponPercent(userId)).divide(new BigDecimal("100"))));
        }else {
            item.setCouponPrice(BigDecimal.ZERO);
        }
    }
}
