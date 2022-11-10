package com.chen.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 地址删除参数
 *
 * @author 10065
 * @date 2022/11/10
 */
@Data
public class AddressRemoveParam {

    @NotNull
    private Integer id;
}
