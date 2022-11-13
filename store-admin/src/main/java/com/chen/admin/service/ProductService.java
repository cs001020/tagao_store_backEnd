package com.chen.admin.service;

import com.chen.param.ProductSaveParam;
import com.chen.param.ProductSearchParam;
import com.chen.pojo.Product;
import com.chen.untils.R;

/**
 * 产品服务
 *
 * @author CHEN
 * @date 2022/11/13
 */
public interface ProductService {
    /**
     * 管理列表
     *
     * @param param 参数
     * @return {@link R}
     */
    R search(ProductSearchParam param);

    /**
     * 保存
     *
     * @param param 参数
     * @return {@link R}
     */
    R save(ProductSaveParam param);

    /**
     * 更新
     *
     * @param param 参数
     * @return {@link R}
     */
    R update(Product param);

    /**
     * 删除
     *
     * @param productId 产品id
     * @return {@link R}
     */
    R remove(Integer productId);
}
