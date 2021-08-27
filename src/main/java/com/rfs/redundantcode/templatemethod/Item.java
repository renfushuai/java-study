package com.rfs.redundantcode.templatemethod;

import lombok.Data;

import java.math.BigDecimal;

/**
* @author: rfs
* @create: 2021/8/17
* @description: 商品信息
**/
@Data
public class Item {
    private long id;
    private int quantity;
    private BigDecimal price;
    private BigDecimal couponPrice;
    private BigDecimal deliveryPrice;
}
