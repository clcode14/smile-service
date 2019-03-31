package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpResponse;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.common.redis.RedisUtil;
import org.flightythought.smile.appserver.common.utils.HttpUtils;
import org.flightythought.smile.appserver.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName LoginController
 * @CreateTime 2019/3/17 19:37
 * @Description: 登录模块控制层
 */
@Controller
@Api(tags = "登录模块控制层", description = "登录模块")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/")
    @ResponseBody
    @ApiOperation(value = "验证用户登陆", notes = "验证用户登陆返回手机号")
    public String showHome(@ApiIgnore HttpServletRequest request) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登陆用户：" + name);
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String authToken = authHeader.substring("Bearer ".length());
            String username = (String) redisUtil.get(authToken);
            System.out.println(username);
        }
        return name;
    }

    @GetMapping("/sms/vCode/{phone}")
    @ResponseBody
    @ApiOperation(value = "获取短信验证码", notes = "发送短信验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号")
    })
    public ResponseBean getSMSVerificationCode(@PathVariable(value = "phone") String phone, @ApiIgnore HttpSession session) {
        ResponseBean result = loginService.getSMSVerificationCode(phone, session);
        if (result == null) {
            return ResponseBean.error("短信验证码发送失败");
        }
        return result;
    }

}
