package com.chen.client;

import com.chen.untils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 收集客户
 *
 * @author CHEN
 * @date 2022/11/14
 */
@FeignClient("collect-service")
public interface CollectClient {
    @PostMapping("/collect/remove/product")
    R remove(@RequestBody Integer productId);
}
