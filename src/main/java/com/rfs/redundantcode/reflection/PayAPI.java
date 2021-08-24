package com.rfs.redundantcode.reflection;

import lombok.Data;

import java.math.BigDecimal;

@Data
@BackAPI(url = "/bank/pay",desc = "支付接口")
public class PayAPI extends AbstractAPI {
    @BankAPIField(order = 1,type = BankAPIFieldType.N,length = 20)
    private long userId;
    @BankAPIField(order = 2,type = BankAPIFieldType.M,length = 10)
    private BigDecimal amount;
}
