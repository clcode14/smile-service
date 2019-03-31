package org.flightythought.smile.appserver.security.sms;

import org.flightythought.smile.appserver.common.redis.RedisUtil;
import org.flightythought.smile.appserver.config.properties.AppProperties;
import org.flightythought.smile.appserver.database.repository.UserRepository;
import org.flightythought.smile.appserver.security.handler.CustomerAuthenticationFailureHandler;
import org.flightythought.smile.appserver.security.handler.CustomerAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * Copyright 2017 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SmsCodeAuthenticationSecurityConfig
 * @CreateTime 2019/3/17 18:24
 * @Description: 短信代码验证安全配置
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private CustomerAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomerAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void configure(HttpSecurity builder) {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
        smsCodeAuthenticationProvider.setUserRepository(userRepository);
        smsCodeAuthenticationProvider.setAppProperties(appProperties);
        smsCodeAuthenticationProvider.setRedisUtil(redisUtil);
        builder.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
