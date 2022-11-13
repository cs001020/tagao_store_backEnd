package com.chen.admin.controller;

import com.chen.admin.service.UserService;
import com.chen.param.CartListParam;
import com.chen.param.PageParam;
import com.chen.pojo.User;
import com.chen.untils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户控制器
 *
 * @author CHEN
 * @date 2022/11/13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public R userList(PageParam param){
        return userService.userList(param);
    }

    @PostMapping("/remove")
    public R remove(CartListParam cartListParam){
        return userService.remove(cartListParam);
    }

    @PostMapping("/update")
    public R update(User user){
        return userService.update(user);
    }

    @PostMapping("/save")
    public R save(User user){
        return userService.save(user);
    }

}
