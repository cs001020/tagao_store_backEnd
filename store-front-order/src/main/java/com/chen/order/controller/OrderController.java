package com.chen.order.controller;

import com.chen.order.service.OrderService;
import com.chen.param.CartListParam;
import com.chen.param.OrderParam;
import com.chen.param.ProductCollectParam;
import com.chen.untils.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 顺序控制器
 *
 * @author CHEN
 * @date 2022/11/13
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    public OrderService orderService;

    @PostMapping("/save")
    public R saveOrder(@RequestBody OrderParam orderParam){
        return orderService.saveOrder(orderParam);
    }

    @PostMapping("/list")
    public R orderList(@RequestBody @Validated CartListParam cartListParam, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("参数异常，查询失败！");
        }
        return orderService.orderList(cartListParam);
    }
}
