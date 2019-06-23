package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.ResponseBean;

import javax.servlet.http.HttpSession;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName LoginService
 * @CreateTime 2019/3/28 23:24
 * @Description: 登录业务层接口
 */
public interface LoginService {
    ResponseBean getSMSVerificationCode(String phone, HttpSession session);

    ResponseBean getSMSVerificationCodeByALI(String phone, HttpSession session);
}
