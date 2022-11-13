package com.chen.user.controller;

import com.chen.param.CartListParam;
import com.chen.param.PageParam;
import com.chen.pojo.User;
import com.chen.untils.R;
import com.chen.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户管理控制器
 *
 * @author CHEN
 * @date 2022/11/13
 */
@RestController
@RequestMapping("/user")
public class UserAdminController {
    @Resource
    private UserService userService;

    @PostMapping("/admin/list")
    public R listPage(@RequestBody PageParam param){
        return userService.listPage(param);
    }

    @PostMapping("/admin/remove")
    public R remove(@RequestBody CartListParam cartListParam){
        return userService.remove(cartListParam);
    }

    @PostMapping("/admin/update")
    public R update(@RequestBody User user){
        return userService.update(user);
    }

    @PostMapping("/admin/sava")
    public R sava(@RequestBody User user){
        return userService.sava(user);
    }
}
