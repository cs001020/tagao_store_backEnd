package com.chen.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录参数
 *
 * @author 10065
 * @date 2022/11/10
 */
@Data
public class UserLoginParam {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
