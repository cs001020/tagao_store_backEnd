package com.chen.admin.service;

import com.chen.param.PageParam;
import com.chen.untils.R;

public interface OrderService {
    /**
     * 列表
     *
     * @param pageParam 页面参数
     * @return {@link Object}
     */
    R list(PageParam pageParam);
}
