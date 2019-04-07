package org.flightythought.smile.appserver.security;

import org.flightythought.smile.appserver.common.utils.MD5Util;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 *
 * @author LiLei
 * @date 2018/5/23 17:17
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.md5(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Util.md5(rawPassword.toString()));
    }
}
