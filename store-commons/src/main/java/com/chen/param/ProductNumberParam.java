package com.chen.param;

import lombok.Data;

/**
 * 产品数量参数
 *
 * @author CHEN
 * @date 2022/11/13
 */
@Data
public class ProductNumberParam {

    //商品id
    private Integer productId;
    //购买数量
    private Integer productNum;
}
