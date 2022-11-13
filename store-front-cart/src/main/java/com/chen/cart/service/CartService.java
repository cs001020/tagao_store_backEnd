package com.chen.cart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.param.CartListParam;
import com.chen.param.CartParam;
import com.chen.pojo.Cart;
import com.chen.untils.R;

/**
 * 车服务
 *
 * @author CHEN
 * @date 2022/11/12
 */
public interface CartService extends IService<Cart> {
    /**
     * 保存购物车
     *
     * @param cartParam 车参数
     * @return {@link R}
     */
    R saveCart(CartParam cartParam);

    /**
     * 购物车列表
     *
     * @param cartListParam 购物车列表参数
     * @return {@link R}
     */
    R cartList(CartListParam cartListParam);

    /**
     * 更新购物车
     *
     * @param cart 车
     * @return {@link R}
     */
    R updateCart(Cart cart);

    /**
     * 删除购物车
     *
     * @param cart 车
     * @return {@link R}
     */
    R removeCart(Cart cart);

    /**
     * 删除检查
     *
     * @param productId 产品id
     * @return {@link R}
     */
    R removeCheck(Integer productId);
}
