package org.flightythought.smile.admin.common;

import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 *
 * @author LiLei
 * @date 2019/1/15 17:06
 */
public class UserUtils {

    /**
     * 获取当前登录用户
     */
    public static SysUserEntity getCurrentUser() {
        return (SysUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
