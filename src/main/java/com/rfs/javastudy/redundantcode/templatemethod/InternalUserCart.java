package com.rfs.javastudy.redundantcode.templatemethod;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author: rfs
 * @create: 2021/8/17
 * @description: 内部用户
 **/
@Service("InternalUserCart")
public class InternalUserCart extends AbstractCart {
    @Override
    protected void processCouponPrice(long userId, Item item) {
        item.setCouponPrice(BigDecimal.ZERO);
    }

    @Override
    protected void processDeliveryPrice(long userId, Item item) {
        item.setDeliveryPrice(BigDecimal.ZERO);
    }
}
