package com.chen.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.param.ProductCategoryIdsParams;
import com.chen.param.ProductHotParam;
import com.chen.param.ProductIdParam;
import com.chen.param.ProductPromoParam;
import com.chen.pojo.Product;
import com.chen.untils.R;

/**
 * 产品服务
 *
 * @author CHEN
 * @date 2022/11/11
 */
public interface ProductService extends IService<Product> {
    /**
     * 根据类别名获得该类别的热销商品最多7条
     *
     * @param productPromoParam 产品促销参数
     * @return {@link R}
     */
    R promo(ProductPromoParam productPromoParam);

    /**
     * 多类别热门商品查询 根据类别名称集合 最多7条
     *
     * @param productHotParam 产品热参数
     * @return {@link R}
     */
    R hots(ProductHotParam productHotParam);

    /**
     * 类别列表
     *
     * @return {@link R}
     */
    R categoryList();

    /**
     * 按类别id查询商品
     * 如果未传入类别id 查询全部商品
     *
     * @param params 参数个数
     * @return {@link R}
     */
    R byCategoryIds(ProductCategoryIdsParams params);

    /**
     * 根据id查询商品详细信息
     *
     * @param param 参数
     * @return {@link R}
     */
    R detail(ProductIdParam param);

    /**
     * 根据id查询商品图片
     *
     * @param param 参数
     * @return {@link R}
     */
    R picture(ProductIdParam param);
}