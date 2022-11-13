package com.chen.client;

import com.chen.param.CartListParam;
import com.chen.param.PageParam;
import com.chen.pojo.User;
import com.chen.untils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户客户端
 *
 * @author CHEN
 * @date 2022/11/13
 */
@FeignClient("user-service")
public interface UserClient {

    @PostMapping("/user/admin/list")
    R listPage(@RequestBody PageParam param);

    @PostMapping("/user/admin/remove")
    R adminRemove(@RequestBody CartListParam cartListParam);

    @PostMapping("/user/admin/update")
    R adminUpdate(@RequestBody User user);

    @PostMapping("/user/admin/sava")
    R adminSave(@RequestBody User user);
}
