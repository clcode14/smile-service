package org.flightythought.smile.appserver.security;

import com.alibaba.fastjson.JSON;
import org.flightythought.smile.appserver.common.ResultEnum;
import org.flightythought.smile.appserver.common.ResultVO;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 * <p>
 * 无权访问
 *
 * @Author: LiLei
 * @ClassName MyAccessDeniedHandler
 * @CreateTime 2019/3/17 21:03
 * @Description: TODO
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(ResultVO.result(ResultEnum.USER_NO_ACCESS, false)));
    }
}
