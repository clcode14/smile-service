package org.flightythought.smile.appserver.config;

import org.flightythought.smile.appserver.filter.JwtAuthenticationTokenFilter;
import org.flightythought.smile.appserver.security.CustomerAuthenticationFailureHandler;
import org.flightythought.smile.appserver.security.CustomerAuthenticationSuccessHandler;
import org.flightythought.smile.appserver.security.MyAuthenticationEntryPoint;
import org.flightythought.smile.appserver.security.MyLogoutSuccessHandler;
import org.flightythought.smile.appserver.security.sms.SmsCodeAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Copyright 2017 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName WebSecurityConfig
 * @CreateTime 2019/3/17 18:19
 * @Description:
 */
@Configurable
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired
    private MyLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private CustomerAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private CustomerAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().authenticationEntryPoint(myAuthenticationEntryPoint).and()
                .apply(smsCodeAuthenticationSecurityConfig).and().authorizeRequests()
                // 如果有允许匿名的url，填在下面
                .antMatchers("/sms/**").permitAll()
                .anyRequest().authenticated()
                .and()
                // 设置登陆页
                .formLogin()
                .successHandler(authenticationSuccessHandler) // 登录成功
                .failureHandler(authenticationFailureHandler) // 登录失败
                .permitAll()
                .and()
                .logout()//默认注销行为为logout
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();

        // 关闭CSRF跨域
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler); // 无权访问 JSON 格式的数据
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // JWT Filter
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**");
    }
}
