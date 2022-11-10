package com.chen.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 地址列表参数
 *
 * @author 10065
 * @date 2022/11/10
 */
@Data
public class AddressListParam {
    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
}
