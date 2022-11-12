package com.chen.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.param.CartListParam;
import com.chen.param.OrderParam;
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
}
