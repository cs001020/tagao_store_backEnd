package com.chen.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 产品id参数
 *
 * @author CHEN
 * @date 2022/11/11
 */
@Data
public class ProductIdParam {

    @NotNull
    private Integer productID;
}
