package org.flightythought.smile.appserver.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flightythought.smile.appserver.common.redis.RedisUtil;
import org.flightythought.smile.appserver.common.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * 登录成功
 * @Author: LiLei
 * @ClassName CustomerAuthenticationSuccessHandler
 * @CreateTime 2019/3/17 19:30
 * @Description: TODO
 */
@Component
public class CustomerAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RedisUtil redisUtil;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = JwtTokenUtil.createToken(userDetails.getUsername(), true);
        // 缓存token
        redisUtil.set(jwtToken, userDetails.getUsername(), 5 * 60 * 1000);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(jwtToken));
    }
}
