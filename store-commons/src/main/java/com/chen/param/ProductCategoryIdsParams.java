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
public class ProductCategoryIdsParams extends PageParam{
    @NotNull
    private List<Integer> categoryID;

}
