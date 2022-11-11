package com.chen.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 产品搜索参数
 *
 * @author CHEN
 * @date 2022/11/11
 */
@Data
public class ProductSearchParam extends PageParam{

    private String search;
}
