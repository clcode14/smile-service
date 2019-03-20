package org.flightythought.smile.admin.framework.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 *
 * @author LiLei
 * @date 2019/1/15 15:11
 */
@Component
public class CustomerMetaDataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 获取当前请求的URL
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        // 获取该请求所对应的URL
//        List<Menu> allMenu = menuService.getAllMenu();
//        for (Menu menu : allMenu) {
//            if (antPathMatcher.match(menu.getUrl(), requestUrl)
//                    &&menu.getRoles().size()>0) {
//                List<Role> roles = menu.getRoles();
//                int size = roles.size();
//                String[] values = new String[size];
//                for (int i = 0; i < size; i++) {
//                    values[i] = roles.get(i).getName();
//                }
//                return SecurityConfig.createList(values);
//            }
//        }
        // 没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
