package org.flightythought.smile.appserver.config;

import org.flightythought.smile.appserver.filter.JwtAuthenticationTokenFilter;
import org.flightythought.smile.appserver.security.MyAuthenticationEntryPoint;
import org.flightythought.smile.appserver.security.MyPasswordEncoder;
import org.flightythought.smile.appserver.security.handler.CustomerAuthenticationFailureHandler;
import org.flightythought.smile.appserver.security.handler.CustomerAuthenticationSuccessHandler;
import org.flightythought.smile.appserver.security.handler.MyLogoutSuccessHandler;
import org.flightythought.smile.appserver.security.sms.SmsCodeAuthenticationSecurityConfig;
import org.flightythought.smile.appserver.security.third.ThirdAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
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
    private ThirdAuthenticationSecurityConfig thirdAuthenticationSecurityConfig;
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
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().authenticationEntryPoint(myAuthenticationEntryPoint).and()
                .apply(smsCodeAuthenticationSecurityConfig).and()
                .apply(thirdAuthenticationSecurityConfig).and()
                .authorizeRequests()
                // 如果有允许匿名的url，填在下面
                .antMatchers("/sms/**").permitAll()
                .anyRequest().authenticated()
                .and()
                // 设置登陆页
                .formLogin()
                // 登录成功
                .successHandler(authenticationSuccessHandler)
                // 登录失败
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .and()
                .logout()//默认注销行为为logout
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();

        // 关闭CSRF跨域
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 无权访问 JSON 格式的数据
//        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        // JWT Filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/image/**", "/static/**", "/statics/**", "/pages/**");
        web.ignoring().antMatchers("/swagger-ui.html").antMatchers("/webjars/**").antMatchers("/v2/**").antMatchers("/swagger-resources/**");
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        MyPasswordEncoder myPasswordEncoder = new MyPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(myPasswordEncoder);
    }
}
