package org.flightythought.smile.appserver.security;

import com.alibaba.fastjson.JSON;
import org.flightythought.smile.appserver.common.ResultEnum;
import org.flightythought.smile.appserver.common.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * 用户未登录时返回给前端的数据
 * @Author: LiLei
 * @ClassName MyAuthenticationEntryPoint
 * @CreateTime 2019/3/17 20:56
 * @Description: TODO
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(ResultVO.result(ResultEnum.USER_NEED_AUTHORITIES, false)));
    }
}
