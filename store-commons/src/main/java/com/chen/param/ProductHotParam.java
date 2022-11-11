package com.chen.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 产品热参数
 *
 * @author CHEN
 * @date 2022/11/11
 */
@Data
public class ProductHotParam {
    @NotEmpty
    private List<String> categoryName;
}
