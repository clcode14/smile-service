package org.flightythought.smile.admin.controller;

import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName LoginController.java
 * @CreateTime 2019/3/27 16:57
 * @Description: 登录处理控制器
 */
@RestController
public class LoginController {

    @ApiOperation(value = "未登录返回", notes = "未登录返回", position = 1)
    @RequestMapping(value = "/login_p", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean login() {
        return ResponseBean.error("尚未登录，请登录!");
    }

    @ApiOperation(value = "退出登录返回", notes = "退出登录返回", position = 2)
    @RequestMapping(value = "/logout_p", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean logout() {
        return ResponseBean.ok("退出登录");
    }

    @ApiOperation(value = "Session过期返回", notes = "Session过期返回", position = 3)
    @RequestMapping(value = "/session/invalid", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean sessionInvalid() {
        return ResponseBean.sessionInvalid("session已过期，请重新登录!");
    }
}
