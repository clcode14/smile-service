package org.flightythought.smile.appserver.security.sms;

import org.flightythought.smile.appserver.database.repository.UserRepository;
import org.flightythought.smile.appserver.security.CustomerAuthenticationFailureHandler;
import org.flightythought.smile.appserver.security.CustomerAuthenticationSuccessHandler;
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
 * @Description: TODO
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

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
        smsCodeAuthenticationProvider.setUserRepository(userRepository);

        builder.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
