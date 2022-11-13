package com.chen.search.service;

import com.chen.param.ProductSearchParam;
import com.chen.pojo.Product;
import com.chen.untils.R;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * 搜索服务
 *
 * @author CHEN
 * @date 2022/11/11
 */
public interface SearchService {
    /**
     * 搜索产品
     *
     * @param productSearchParam 产品搜索参数
     * @return {@link R}
     */
    R searchProduct(ProductSearchParam productSearchParam);

    /**
     * 保存
     *
     * @param product 产品
     * @return {@link R}
     */
    R save(Product product) throws IOException;

    /**
     * 删除
     *
     * @param productId 产品id
     * @return {@link R}
     */
    R remove(Integer productId) throws IOException;
}
