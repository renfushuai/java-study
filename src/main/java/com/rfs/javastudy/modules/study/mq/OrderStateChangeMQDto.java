package com.rfs.javastudy.modules.study.mq;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
