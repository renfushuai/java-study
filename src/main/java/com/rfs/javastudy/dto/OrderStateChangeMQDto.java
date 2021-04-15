package com.rfs.javastudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStateChangeMQDto {
    /**
     * 系统代码
     */
    private Integer sysCode;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 订单当前状态
     */
    private Integer state;

}
