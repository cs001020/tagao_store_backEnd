package com.chen.to;

import lombok.Data;

import java.io.Serializable;

/**
 * 订单发送商品服务的实体
 *
 * @author CHEN
 * @date 2022/11/13
 */
@Data
public class OrderToProduct implements Serializable {
    public static final Long serialVersionUID=1L;

    private Integer productId;
    private Integer num;
}
