package com.chen.admin.controller;

import com.chen.admin.param.AdminUserParam;
import com.chen.admin.pojo.AdminUser;
import com.chen.admin.service.AdminUserService;
import com.chen.untils.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 管理用户控制器
 *
 * @author CHEN
 * @date 2022/11/13
 */
@RestController
public class AdminUserController {
    @Resource
    private AdminUserService adminUserService;

    @PostMapping("/user/login")
    public R login(@Validated AdminUserParam adminUserParam, BindingResult bindingResult, HttpSession session){
        if (bindingResult.hasErrors()) {
            return R.fail("参数异常");
        }
        String captcha = (String) session.getAttribute("captcha");
        if (captcha==null||!captcha.equalsIgnoreCase(adminUserParam.getVerCode())) {
            return R.fail("验证码错误！");
        }
        AdminUser adminUser=adminUserService.login(adminUserParam);
        if (Objects.isNull(adminUser)) {
            return R.fail("账号或密码错误，登陆失败");
        }
        session.setAttribute("userInfo",adminUser);
        return R.ok("登陆成功");
    }

    @GetMapping("/user/logout")
    public R logout(HttpSession session){
        //清空session
        session.invalidate();
        //返回
        return R.ok("登出成功！");
    }
}
