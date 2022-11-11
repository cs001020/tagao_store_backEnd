package com.chen.search.service;

import com.chen.param.ProductSearchParam;
import com.chen.untils.R;

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
}
