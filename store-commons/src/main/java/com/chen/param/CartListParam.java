package com.chen.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 购物车列表参数
 *
 * @author CHEN
 * @date 2022/11/12
 */
@Data
public class CartListParam {
    @JsonProperty("user_id")
    @NotNull
    private Integer userId;
}
