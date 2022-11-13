package com.chen.client;

import com.chen.param.PageParam;
import com.chen.untils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 订单客户
 *
 * @author CHEN
 * @date 2022/11/14
 */
@FeignClient("order-service")
public interface OrderClient {
    @PostMapping("/order/remove/check")
    R removeCheck(@RequestBody Integer productId);

    @PostMapping("/order/admin/list")
    R adminList(@RequestBody PageParam pageParam);
}
