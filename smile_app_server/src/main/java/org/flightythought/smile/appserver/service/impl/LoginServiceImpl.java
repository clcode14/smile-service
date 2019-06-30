package org.flightythought.smile.appserver.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.bean.SmsCodeData;
import org.flightythought.smile.appserver.common.LocalCache;
import org.flightythought.smile.appserver.common.utils.AesUtils;
import org.flightythought.smile.appserver.common.utils.HttpUtils;
import org.flightythought.smile.appserver.common.utils.PhoneUtil;
import org.flightythought.smile.appserver.common.utils.SmsUtils;
import org.flightythought.smile.appserver.config.properties.AppProperties;
import org.flightythought.smile.appserver.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName LoginServiceImpl
 * @CreateTime 2019/3/28 23:24
 * @Description: 登录处理业务层
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final String APP_CODE = "4e84bebc30684ce681c98547087784db";

    private static final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    private AppProperties appProperties;

    @Override
    public ResponseBean getSMSVerificationCode(String phone, HttpSession session) {
        // 创建短信验证码加密对象
        SmsCodeData smsCodeData = new SmsCodeData();
        LOG.info("请求发送验证码的手机号为：{}", phone);

        if (StringUtils.isBlank(phone)) {
            return ResponseBean.error("请输入正确的手机号");
        }

        if (!PhoneUtil.isMobileNO(phone)) {
            return ResponseBean.error("请输入正确的手机号");
        }

        if (LocalCache.hasRegisterRequest(phone)) {
            return ResponseBean.error("一分钟只能只能请求一次短信验证码，请一分钟之后再次尝试");
        }

        //        int code = 123456;
        // 创建4位数验证码
        int code = (int) Math.ceil(Math.random() * 9000 + 1000);
        try {
            SmsUtils.sendVerifyCode(phone, String.valueOf(code));
        } catch (Exception e) {
            LOG.error("发送短信验证码失败", e);
            return ResponseBean.error("发送短信验证码失败", e.getMessage());
        }
        smsCodeData.setPhone(phone);
        smsCodeData.setVCode(code + "");
        smsCodeData.setTime(System.currentTimeMillis());

        try {
            String codeResult = AesUtils.aesEncryptHexString(JSON.toJSONString(smsCodeData), appProperties.getTokenKey());
            return ResponseBean.ok("发送短信成功", codeResult);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("发送短信失败", e.getMessage());
        }
    }

    @Override
    public ResponseBean getSMSVerificationCodeByALI(String phone, HttpSession session) {
        return null;
    }
}
