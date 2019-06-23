package org.flightythought.smile.appserver.security.third;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright 2019 Flighty-Thought Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName ThirdAuthenticationFilter.java
 * @CreateTime 2019/4/30 15:02
 * @Description: 第三方登陆鉴权过滤器
 */
public class ThirdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * AUTH ID 登陆请求参数
     */
    public static final String AUTH_ID = "authId";

    /**
     * TYPE 登陆请求参数
     */
    public static final String TYPE = "type";

    /**
     * 用户昵称
     */
    public static final String NICKNAME = "nickname";

    /**
     * 用户头像
     */
    public static final String AVATER = "avater";

    /**
     * 是否仅 POST 方式
     */
    private boolean postOnly = true;

    public ThirdAuthenticationFilter() {
        super(new AntPathRequestMatcher("/third/login", "POST"));
    }

    protected ThirdAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String method = "POST";
        if (!method.equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String authId = request.getParameter(AUTH_ID);
        String type = request.getParameter(TYPE);
        String nickname = request.getParameter(NICKNAME);
        String avater = request.getParameter(AVATER);
        if (authId == null) {
            authId = "";
        }
        authId = authId.trim();
        if (type == null) {
            type = "";
        }
        if (nickname == null) {
            nickname = "";
        }
        if (avater == null) {
            authId = "";
        }
        type = type.trim();
        ThirdAuthenticationToken authenticationToken = new ThirdAuthenticationToken(authId, type, nickname, avater);
        // Allow subclasses to set the "details" property
        authenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));

        return this.getAuthenticationManager().authenticate(authenticationToken);
    }
}
