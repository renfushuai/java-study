package com.rfs.redundantcode.templatemethod;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* @author: rfs
* @create: 2021/8/17
* @description: 购物车
**/
@Data
public class Cart {
    private List<Item> items=new ArrayList<>();
    private BigDecimal totalDiscount;
    private BigDecimal totalItemPrice;
    private BigDecimal totalDeliveryPrice;
    private BigDecimal payPrice;
}
