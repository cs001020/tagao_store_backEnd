package com.chen.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 产品类别id参数
 *
 * @author CHEN
 * @date 2022/11/11
 */
@Data
public class ProductCategoryIdsParams {
    @NotNull
    private List<Integer> categoryID;
    private Integer currentPage=1;
    private Integer pageSize=15;
}
