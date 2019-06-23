package org.flightythought.smile.appserver.security.third;

import org.apache.commons.lang.StringUtils;
import org.flightythought.smile.appserver.common.redis.RedisUtil;
import org.flightythought.smile.appserver.common.utils.IpUtil;
import org.flightythought.smile.appserver.common.utils.JwtTokenUtil;
import org.flightythought.smile.appserver.common.utils.MD5Util;
import org.flightythought.smile.appserver.config.properties.AppProperties;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName ThirdAuthenticationProvider.java
 * @CreateTime 2019/4/30 15:42
 * @Description: TODO
 */
public class ThirdAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(ThirdAuthenticationProvider.class);

    private UserDetailsService userDetailsService;

    private UserRepository userRepository;

    private AppProperties appProperties;

    private RedisUtil redisUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ThirdAuthenticationToken authenticationToken = (ThirdAuthenticationToken) authentication;
        // 获取请求的authId
        String authId = (String) authenticationToken.getPrincipal();
        // 获取请求的type
        String type = authenticationToken.getType();
        // 获取请求的nickname;
        String nickname = authenticationToken.getNickname();
        // 获取请求的avater
        String avater = authenticationToken.getAvater();
        // 检查数据
        checkData(authId, type, nickname, avater);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 根据登陆登陆类型和authId获取用户
        UserEntity userEntity = userRepository.findByAuthIdAndThirdType(authId, type);
        // 获取IP地址
        String ip = IpUtil.getIp(request);
        if (userEntity == null) {
            // 首次登陆用户，创建用户
            userEntity = new UserEntity();
            // 用户名（authId+type MD5加密）
            userEntity.setUsername(MD5Util.md5(authId + type));
            // authId
            userEntity.setAuthId(authId);
            // type
            userEntity.setThirdType(type);
            // 昵称（用户 + 5位随机数）
//            int code = (int) Math.ceil(Math.random() * 90000 + 10000);
//            userEntity.setNickName("用户" + code);
            userEntity.setNickName(nickname);
            // 头像
            userEntity.setAvater(avater);
            // 登录时间
            userEntity.setLoginTime(LocalDateTime.now());
            // 登录次数
            userEntity.setLoginCount(1);
            // IP地址
            userEntity.setIp(ip);
            // 过期时间
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
        ThirdAuthenticationToken authenticationResult = new ThirdAuthenticationToken(userEntity.getAuthorities(), userEntity, type, nickname, avater);
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    private void checkData(String authId, String type, String nickname, String avater) {
        if (StringUtils.isBlank(authId)) {
            throw new BadCredentialsException("请传递authId");
        }
        if (StringUtils.isBlank(type)) {
            throw new BadCredentialsException("请传递type");
        }
        switch (type) {
            case "wechat":
                return;
            case "qq":
                return;
            case "alipay":
                return;
            default:
                throw new BadCredentialsException("type传参有误!");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        // 判断authentication 是不是 ThirdAuthenticationToken 的子类或子接口
        return ThirdAuthenticationToken.class.isAssignableFrom(aClass);
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
