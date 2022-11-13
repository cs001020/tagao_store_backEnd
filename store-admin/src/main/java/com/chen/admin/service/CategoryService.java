package com.chen.admin.service;

import com.chen.param.PageParam;
import com.chen.pojo.Category;
import com.chen.untils.R;

/**
 * 目录服务
 *
 * @author CHEN
 * @date 2022/11/13
 */
public interface CategoryService {
    /**
     * 列表页面
     *
     * @param param 参数
     * @return {@link R}
     */
    R listPage(PageParam param);

    /**
     * 保存
     *
     * @param category 类别
     * @return {@link R}
     */
    R save(Category category);

    /**
     * 删除
     *
     * @param categoryId 类别id
     * @return {@link R}
     */
    R remove(Integer categoryId);

    /**
     * 更新
     *
     * @param category 类别
     * @return {@link R}
     */
    R update(Category category);
}
