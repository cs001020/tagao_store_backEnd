package com.chen.admin.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 登录拦截器
 *
 * @author CHEN
 * @date 2022/11/13
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        if (Objects.isNull(userInfo)){
            //重定向
            response.sendRedirect(request.getContextPath()+"/index.html");
            return false;
        }
        return true;
    }
}
