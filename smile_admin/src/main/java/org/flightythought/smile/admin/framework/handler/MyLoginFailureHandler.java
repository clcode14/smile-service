package org.flightythought.smile.admin.framework.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 *
 * @author LiLei
 * @date 2019/1/15 15:40
 */
@Component
public class MyLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResponseBean responseBean;
        if (e instanceof BadCredentialsException ||
                e instanceof UsernameNotFoundException) {
            responseBean = ResponseBean.error("账户名或者密码输入错误!");
        } else if (e instanceof LockedException) {
            responseBean = ResponseBean.error("账户被锁定，请联系管理员!");
        } else if (e instanceof CredentialsExpiredException) {
            responseBean = ResponseBean.error("密码过期，请联系管理员!");
        } else if (e instanceof AccountExpiredException) {
            responseBean = ResponseBean.error("账户过期，请联系管理员!");
        } else if (e instanceof DisabledException) {
            responseBean = ResponseBean.error("账户被禁用，请联系管理员!");
        } else {
            responseBean = ResponseBean.error("登录失败!");
        }
        response.setStatus(401);
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(responseBean));
        out.flush();
        out.close();
    }
}
