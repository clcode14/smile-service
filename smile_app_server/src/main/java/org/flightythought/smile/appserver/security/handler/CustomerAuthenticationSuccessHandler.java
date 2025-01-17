package org.flightythought.smile.appserver.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 * <p>
 * 登录成功
 *
 * @Author: LiLei
 * @ClassName CustomerAuthenticationSuccessHandler
 * @CreateTime 2019/3/17 19:30
 * @Description: TODO
 */
@Component
public class CustomerAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;
    private static final Logger LOG = LoggerFactory.getLogger(CustomerAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        LOG.info("用户：{}，登录成功", userEntity.getUsername());
        ResponseBean responseBean = ResponseBean.ok("登录成功", userEntity);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(responseBean));
    }
}
