package org.flightythought.smile.appserver.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.flightythought.smile.appserver.bean.AppUpdateData;
import org.flightythought.smile.appserver.bean.SmsCodeData;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.AesUtils;
import org.flightythought.smile.appserver.common.utils.MD5Util;
import org.flightythought.smile.appserver.config.properties.AppProperties;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.repository.UserRepository;
import org.flightythought.smile.appserver.service.SystemService;
import org.flightythought.smile.appserver.service.ViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName ViewServiceImpl
 * @CreateTime 2019/6/21 1:12
 * @Description: TODO
 */
@Service
public class ViewServiceImpl implements ViewService {

    private static final Logger LOG = LoggerFactory.getLogger(ViewServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private SystemService systemService;


    @Override
    public AppUpdateData register(String phone, String vCode, String token, String recommender) {
        // 校验验证码
        checkSmsCode(phone, vCode, token);
        //
        UserEntity userEntity = userRepository.findByMobile(phone);
        if (userEntity != null) {
            throw new FlightyThoughtException("该手机号已注册");
        }
        // 首次登录用户，创建用户
        userEntity = new UserEntity();
        // 电话
        userEntity.setMobile(phone);
        // 用户名(默认手机号MD5加密)
        userEntity.setUsername(MD5Util.md5(phone));
        // 昵称（手机用户+5位随机数）
        int code = (int) Math.ceil(Math.random() * 90000 + 10000);
        userEntity.setNickName("手机用户" + code);
        // 登录时间
        userEntity.setLoginTime(LocalDateTime.now());
        // 登录次数
        userEntity.setLoginCount(0);
        userEntity.setCreateUserName("system");
        // 邀请人
        UserEntity reCommender = userRepository.findByUsername(recommender);
        if (reCommender != null) {
            userEntity.setReommenderId(reCommender.getId());
        }
        userRepository.save(userEntity);
        // 获取APP下载地址
        AppUpdateData appUpdateData = systemService.updateApp();
        return appUpdateData;
    }

    private void checkSmsCode(String phone, String vCode, String token) {
        // 获取短信验证码
        if (StringUtils.isBlank(phone)) {
            throw new BadCredentialsException("请输入手机号");
        }
        if (StringUtils.isBlank(vCode)) {
            throw new BadCredentialsException("请输入短信验证码");
        }
        if (StringUtils.isBlank(token)) {
            throw new BadCredentialsException("验证码过期，请重新获取");
        }
//        Map<String, Object> smsCode = (Map<String, Object>) request.getSession().getAttribute("smsCode");
//        String applyMobile = (String) smsCode.get("mobile");
//        int code = (int) smsCode.get("code");
        String params;
        try {
            //解密短信验证码
            params = AesUtils.aesDecryptHexString(token, appProperties.getCodeKey());
        } catch (Exception e) {
            throw new BadCredentialsException("短信加密Token输入有误");
        }
        SmsCodeData smsCodeData = JSON.parseObject(params, SmsCodeData.class);
        if (!vCode.equals(smsCodeData.getVCode())) {
            LOG.info(String.format("短信验证码不正确，请求的验证码为%s, 发送的验证码为：%s", vCode, smsCodeData.getVCode()));
            throw new BadCredentialsException("短信验证码不正确");
        }
        if (!phone.equals(smsCodeData.getPhone())) {
            LOG.info(String.format("登录和获取短信手机号不符,注册的手机号是:%s, 获取短信的手机号为:%s", phone, smsCodeData.getPhone()));
            throw new BadCredentialsException("注册登录号和获取短信手机号不符");
        }
        Long now = System.currentTimeMillis();
        //短信验证码大于10分钟失效
        if ((now - smsCodeData.getTime()) > 10 * 60 * 1000) {
            LOG.info(String.format("短信验证码失效，短信验证码生成时间为:%s, 当前时间为:%s", smsCodeData.getTime(), now));
            throw new BadCredentialsException("短信验证码失效，请重新获取");
        }
    }
}
