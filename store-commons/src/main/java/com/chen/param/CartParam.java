package com.chen.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 购物车参数
 *
 * @author CHEN
 * @date 2022/11/12
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartParam {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;

    @NotNull
    @JsonProperty("product_id")
    private Integer productId;
    private Integer num;
}

