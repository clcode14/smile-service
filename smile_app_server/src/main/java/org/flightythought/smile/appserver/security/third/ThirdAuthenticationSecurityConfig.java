package org.flightythought.smile.appserver.security.third;

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
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName ThirdAuthenticationSecurityConfig.java
 * @CreateTime 2019/4/30 16:42
 * @Description: 第三方登陆验证安全配置
 */

@Component
public class ThirdAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
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
        ThirdAuthenticationFilter thirdAuthenticationFilter = new ThirdAuthenticationFilter();
        thirdAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        thirdAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        thirdAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        ThirdAuthenticationProvider thirdAuthenticationProvider = new ThirdAuthenticationProvider();
        thirdAuthenticationProvider.setUserDetailsService(userDetailsService);
        thirdAuthenticationProvider.setUserRepository(userRepository);
        thirdAuthenticationProvider.setAppProperties(appProperties);
        thirdAuthenticationProvider.setRedisUtil(redisUtil);
        builder.authenticationProvider(thirdAuthenticationProvider).addFilterAfter(thirdAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
