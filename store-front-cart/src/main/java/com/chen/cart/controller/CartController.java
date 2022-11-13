package com.chen.cart.controller;

import com.chen.cart.service.CartService;
import com.chen.param.CartListParam;
import com.chen.param.CartParam;
import com.chen.pojo.Cart;
import com.chen.untils.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 车控制器
 *
 * @author CHEN
 * @date 2022/11/12
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @PostMapping("/save")
    public R saveCart(@RequestBody @Validated CartParam cartParam, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("参数异常，查询失败");
        }
        return cartService.saveCart(cartParam);
    }

    @PostMapping("/list")
    public R cartList(@RequestBody @Validated CartListParam cartListParam,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("参数异常，查询失败！");
        }
        return cartService.cartList(cartListParam);
    }

    @PostMapping("/update")
    public R updateCart(@RequestBody Cart cart){
        return cartService.updateCart(cart);
    }

    @PostMapping("/remove")
    public R removeCart(@RequestBody Cart cart){
        return cartService.removeCart(cart);
    }

    @PostMapping("/remove/check")
    public R removeCheck(@RequestBody Integer productId) {
        return cartService.removeCheck(productId);
    }
}
