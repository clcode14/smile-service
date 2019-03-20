package org.flightythought.smile.admin.framework.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.bean.UserInfo;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.common.UserUtils;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
 * @date 2019/1/15 17:00
 */
@Component
public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        SysUserEntity sysUserEntity = UserUtils.getCurrentUser();
        request.getSession().setAttribute(GlobalConstant.USER_SESSION, sysUserEntity);
        ResponseBean respBean = ResponseBean.ok("登录成功!", new UserInfo(sysUserEntity));
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}
