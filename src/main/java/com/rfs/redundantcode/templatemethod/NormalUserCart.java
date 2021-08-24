package com.rfs.redundantcode.templatemethod;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author: rfs
 * @create: 2021/8/17
 * @description: 普通用户购物车
 **/
@Service("NormalUserCart")
public class NormalUserCart extends AbstractCart {
    @Override
    protected void processCouponPrice(long userId, Item item) {
        item.setCouponPrice(BigDecimal.ZERO);
    }

    @Override
    protected void processDeliveryPrice(long userId, Item item) {
        item.setDeliveryPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())).multiply(new BigDecimal("0.1")));
    }
}
