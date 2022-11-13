package com.chen.admin.controller;

import com.wf.captcha.utils.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码控制器
 *
 * @author CHEN
 * @date 2022/11/13
 */
@Controller
@Slf4j
@RequestMapping
public class CaptchaController {

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaUtil.out(request,response);
    }
}
