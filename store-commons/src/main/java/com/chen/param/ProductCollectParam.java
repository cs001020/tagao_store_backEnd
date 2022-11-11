package com.chen.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 收集产品参数
 *
 * @author CHEN
 * @date 2022/11/12
 */
@Data
public class ProductCollectParam {
    @NotEmpty
    private List<Integer> productIds;
}
