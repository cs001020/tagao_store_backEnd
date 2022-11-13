package com.chen.client;

import com.chen.untils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 车客户
 *
 * @author CHEN
 * @date 2022/11/14
 */
@FeignClient("cart-service")
public interface CartClient {
    @PostMapping("/cart/remove/check")
    R removeCheck(@RequestBody Integer productId);
}
