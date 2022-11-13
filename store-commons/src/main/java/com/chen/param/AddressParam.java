package com.chen.param;

import com.chen.pojo.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 地址参数
 *
 * @author CHEN
 * @date 2022/11/13
 */
@Data
public class AddressParam {
    @NotNull
    @JsonProperty("user_id")
    private Integer userId;

    private Address add;
}
