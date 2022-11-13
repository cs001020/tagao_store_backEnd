package com.chen.admin.controller;

import com.chen.admin.service.OrderService;
import com.chen.param.PageParam;
import com.chen.untils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 顺序控制器
 *
 * @author CHEN
 * @date 2022/11/14
 */
@RestController
@RequestMapping("/order")
public class OrderController
{
    @Resource
    private OrderService orderService;

    @GetMapping("/list")
    public R list(PageParam pageParam){
        return orderService.list(pageParam);
    }

}
