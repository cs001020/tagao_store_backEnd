package com.chen.param;

import com.chen.pojo.Product;
import lombok.Data;

/**
 * 产品保存参数
 *
 * @author CHEN
 * @date 2022/11/14
 */
@Data
public class ProductSaveParam extends Product {
    private String pictures;
}
