package com.chen.category.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.param.PageParam;
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

    /**
     * 列表页面
     *
     * @param param 参数
     * @return {@link R}
     */
    R listPage(PageParam param);

    /**
     * 管理保存
     *
     * @param category 类别
     * @return {@link R}
     */
    R adminSave(Category category);

    /**
     * 管理员删除
     *
     * @param categoryId 类别id
     * @return {@link R}
     */
    R adminRemove(Integer categoryId);

    /**
     * 更新
     *
     * @param category 类别
     * @return {@link R}
     */
    R update(Category category);
}
