package com.chen.user.controller;

import com.chen.param.UserCheckParam;
import com.chen.param.UserLoginParam;
import com.chen.pojo.User;
import com.chen.untils.R;
import com.chen.user.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户控制器
 *
 * @author 10065
 * @date 2022/11/10
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 检查账号是否可用
     *
     * @param userCheckParam 用户检查参数
     * @param bindingResult  绑定结果
     * @return {@link R}
     */
    @PostMapping("/check")
    public R check(@RequestBody @Validated UserCheckParam userCheckParam, BindingResult bindingResult){
        //未通过数据校验
        if (bindingResult.hasErrors()){
            return R.fail("账号为null，不可使用！");
        }
        //通过数据校验，进行检查
        return userService.check(userCheckParam);
    }

    @PostMapping("/register")
    public R register(@RequestBody @Validated User user,BindingResult bindingResult){
        //未通过数据校验
        if (bindingResult.hasErrors()){
            return R.fail("参数异常，不可注册！");
        }
        //通过数据校验，进行检查
        return userService.register(user);
    }
    @PostMapping("/login")
    public R login(@RequestBody @Validated UserLoginParam userLoginParam,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("参数异常，不可登陆");
        }
        return userService.login(userLoginParam);
    }
}
