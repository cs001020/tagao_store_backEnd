package com.chen.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户检查参数
 *
 * @author 10065
 * @date 2022/11/10
 */
@Data
public class UserCheckParam {
    @NotBlank
    private String userName;
}
