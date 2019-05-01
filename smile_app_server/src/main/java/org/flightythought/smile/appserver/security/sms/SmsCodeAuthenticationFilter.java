package org.flightythought.smile.appserver.security.sms;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 * <p>
 * 短信登录的鉴权过滤器，模仿 UsernamePasswordAuthenticationFilter实现
 *
 * @Author: LiLei
 * @ClassName SmsCodeAuthenticationFilter
 * @CreateTime 2019/3/17 18:26
 * @Description: TODO
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * form表单中手机号码的字段name
     */
    public static final String SPRING_SECURITY_FORM_MOBILE_KEY = "phone";

    public SmsCodeAuthenticationFilter() {
        // 短信登录的请求 post 方式的 /sms/login
        super(new AntPathRequestMatcher("/sms/login", "POST"));
    }

    protected SmsCodeAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    protected SmsCodeAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        /**
         * 是否仅 POST 方式
         */
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String phone = request.getParameter(SPRING_SECURITY_FORM_MOBILE_KEY);
        if (phone == null) {
            phone = "";
        }
        phone = phone.trim();

        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(phone);
        // Allow subclasses to set the "details" property
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
