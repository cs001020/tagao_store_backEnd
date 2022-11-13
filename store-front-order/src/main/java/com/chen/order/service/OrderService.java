package com.chen.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.param.CartListParam;
import com.chen.param.OrderParam;
import com.chen.param.PageParam;
import com.chen.pojo.Order;
import com.chen.untils.R;

/**
 * 订单服务
 *
 * @author CHEN
 * @date 2022/11/13
 */
public interface OrderService extends IService<Order>  {
    /**
     * 保存订单
     *
     * @param orderParam 命令参数
     * @return {@link R}
     */
    R saveOrder(OrderParam orderParam);

    /**
     * 订单列表
     *
     * @param cartListParam 购物车列表参数
     * @return {@link R}
     */
    R orderList(CartListParam cartListParam);

    /**
     * 删除检查
     *
     * @param productId 产品id
     * @return {@link R}
     */
    R removeCheck(Integer productId);

    /**
     * 管理列表
     *
     * @param pageParam 页面参数
     * @return {@link R}
     */
    R adminList(PageParam pageParam);
}
