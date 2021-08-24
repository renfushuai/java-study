package com.rfs.redundantcode.templatemethod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: rfs
 * @create: 2021/8/17
 * @description: 购物车抽象类
 **/
public abstract class AbstractCart {
    public Cart process(long userId, Map<Long, Integer> items) {
        Cart cart = new Cart();
        List<Item> itemList = new ArrayList<>();
        items.entrySet().stream().forEach(entry -> {
            Item item = new Item();
            item.setId(entry.getKey());
            item.setPrice(Db.getItemPrice(entry.getKey()));
            item.setQuantity(entry.getValue());
            itemList.add(item);
        });
        itemList.stream().forEach(item -> {
            processCouponPrice(userId, item);
            processDeliveryPrice(userId, item);
        });
        cart.setItems(itemList);
        cart.setTotalItemPrice(itemList.stream().map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add));
        cart.setTotalDeliveryPrice(itemList.stream().map(Item::getDeliveryPrice).reduce(BigDecimal.ZERO,BigDecimal::add));
        cart.setTotalDiscount(itemList.stream().map(Item::getCouponPrice).reduce(BigDecimal.ZERO,BigDecimal::add));
        cart.setPayPrice(cart.getTotalItemPrice().add(cart.getTotalDeliveryPrice()).subtract(cart.getTotalDiscount()));
        return cart;
    }

    protected abstract void processCouponPrice(long userId, Item item);

    protected abstract void processDeliveryPrice(long userId, Item item);
}
