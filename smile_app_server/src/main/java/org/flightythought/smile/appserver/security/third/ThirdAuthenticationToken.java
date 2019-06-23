package org.flightythought.smile.appserver.security.third;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName ThirdAuthenticationToken.java
 * @CreateTime 2019/4/30 15:18
 * @Description: 第三方登陆 AuthenticationToken
 */
public class ThirdAuthenticationToken extends AbstractAuthenticationToken {

    /**
     * 在 UsernamePasswordAuthenticationToken 中该字段代表登录的用户名，
     * 在这里就代表登录的AuthId
     */
    private final Object principal;

    private final String type;

    private final String nickname;

    private final String avater;

    /**
     * 构建一个没有鉴权的 ThirdAuthenticationToken
     */
    public ThirdAuthenticationToken(Object principal, String type, String nickname, String avater) {
        super(null);
        this.principal = principal;
        this.type = type;
        this.nickname = nickname;
        this.avater = avater;
    }

    public ThirdAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal, String type, String nickname, String avater) {
        super(authorities);
        this.principal = principal;
        this.type = type;
        this.nickname = nickname;
        this.avater = avater;
        super.setAuthenticated(true);
        // must use super, as we override
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public String getType() {
        return type;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvater() {
        return avater;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }
}
