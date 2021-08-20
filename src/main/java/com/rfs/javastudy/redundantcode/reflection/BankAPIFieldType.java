package com.rfs.javastudy.redundantcode.reflection;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
* @author: rfs
* @create: 2021/8/20
* @description: 字段类型枚举
**/
public enum BankAPIFieldType {
    S {
        @Override
        public String format(Object value, BankAPIField bankAPIField) {
            return String.format("%-" + bankAPIField.length() + "s", value.toString()).replace(' ', '_');
        }
    },
    N {
        @Override
        public String format(Object value, BankAPIField bankAPIField) {
            return String.format("%" + bankAPIField.length() + "s", value.toString()).replace(' ', '0');
        }
    },
    M {
        @Override
        public String format(Object value, BankAPIField bankAPIField) {
            if (!(value instanceof BigDecimal)) {
                throw new RuntimeException(String.format("{} 的 {} 必须是BigDecimal"));
            }
            return String.format(String.format("%0" + bankAPIField.length() + "d", ((BigDecimal) value).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));
        }
    };

    public abstract String format(Object value, BankAPIField bankAPIField);
}
