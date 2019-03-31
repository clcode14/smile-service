package org.flightythought.smile.appserver.security.sms;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.flightythought.smile.appserver.bean.SmsCodeData;
import org.flightythought.smile.appserver.common.redis.RedisUtil;
import org.flightythought.smile.appserver.common.utils.AesUtils;
import org.flightythought.smile.appserver.common.utils.IpUtil;
import org.flightythought.smile.appserver.common.utils.JwtTokenUtil;
import org.flightythought.smile.appserver.config.properties.AppProperties;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Copyright 2017 Flighty-Thought All rights reserved.
 * <p>
 * 短信登录鉴权 Provider，要求实现 AuthenticationProvider 接口
 *
 * @Author: LiLei
 * @ClassName SmsCodeAuthenticationProvider
 * @CreateTime 2019/3/17 18:45
 * @Description: TODO
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(SmsCodeAuthenticationProvider.class);

    private UserDetailsService userDetailsService;

    private UserRepository userRepository;

    private AppProperties appProperties;

    private RedisUtil redisUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        // 获取请求的电话
        String phone = (String) authentication.getPrincipal();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        checkSmsCode(phone, request);
        // 根据手机号获取当前登录用户
        UserEntity userEntity = userRepository.findByMobile(phone);
        String ip = IpUtil.getIp(request);
        if (userEntity == null) {
            // 首次登录用户，创建用户
            userEntity = new UserEntity();
            // 电话
            userEntity.setMobile(phone);
            // 用户名(默认手机号)
            userEntity.setUsername(phone);
            // 登录时间
            userEntity.setLoginTime(LocalDateTime.now());
            // 登录次数
            userEntity.setLoginCount(1);
            // IP地址
            userEntity.setIp(ip);
            // 设置Token（Token为用户名加密设置）
            // 七天过期时间
//            String jwtToken = JwtTokenUtil.createToken(userEntity.getUsername(), true);
            // 90天过期时间
            String jwtToken = JwtTokenUtil.createToken(userEntity.getUsername() + "_" + System.currentTimeMillis(), appProperties.getTokenExpirationTime() * 24 * 60 * 60 * 1000L);
            userEntity.setToken(jwtToken);
            userEntity.setCreateUserName("system");
            userRepository.save(userEntity);
        } else {
            String jwtToken = JwtTokenUtil.createToken(userEntity.getUsername() + "_" + System.currentTimeMillis(), appProperties.getTokenExpirationTime() * 24 * 60 * 60 * 1000L);
            userEntity.setToken(jwtToken);
            userEntity.setLoginCount(userEntity.getLoginCount() + 1);
        }
        userEntity.setIp(ip);
        userEntity.setLoginTime(LocalDateTime.now());
        userRepository.save(userEntity);
        // 缓存token
        redisUtil.set(userEntity.getToken(), userEntity.getUsername(), appProperties.getTokenExpirationTime() * 24 * 60 * 60 * 1000L);

        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(userEntity.getAuthorities(), userEntity);

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    private void checkSmsCode(String phone, HttpServletRequest request) {
        // 获取短信验证码
        String vCode = request.getParameter("vCode");
        // 获取短信验证码Token
        String token = request.getParameter("token");
        if (StringUtils.isBlank(phone)) {
            throw new BadCredentialsException("请输入手机号");
        }
        if (StringUtils.isBlank(vCode)) {
            throw new BadCredentialsException("请输入短信验证码");
        }
        if (StringUtils.isBlank(token)) {
            throw new BadCredentialsException("请输入短信加密Token");
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

    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppProperties getAppProperties() {
        return appProperties;
    }

    public void setAppProperties(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public RedisUtil getRedisUtil() {
        return redisUtil;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}
