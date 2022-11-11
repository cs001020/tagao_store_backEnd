package com.chen.category.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.param.ProductHotParam;
import com.chen.pojo.Category;
import com.chen.untils.R;

/**
 * 目录服务
 *
 * @author CHEN
 * @date 2022/11/11
 */
public interface CategoryService extends IService<Category> {
    /**
     * 根据种类名获得种类
     *
     * @param categoryName 类别名称
     * @return {@link R}
     */
    R getCategoryByName(String categoryName);

    /**
     * 根据集合返回类别id集合
     *
     * @param productHotParam 产品热参数
     * @return {@link R}
     */
    R hotsCategory(ProductHotParam productHotParam);
}
