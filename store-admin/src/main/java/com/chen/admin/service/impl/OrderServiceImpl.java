package com.chen.admin.service.impl;

import com.chen.admin.service.OrderService;
import com.chen.client.OrderClient;
import com.chen.param.PageParam;
import com.chen.untils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单服务impl
 *
 * @author CHEN
 * @date 2022/11/14
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderClient orderClient;

    @Override
    public R list(PageParam pageParam) {
        R r = orderClient.adminList(pageParam);
        log.info("OrderServiceImpl.list业务结束，结果:{}",r);
        return r;
    }
}
